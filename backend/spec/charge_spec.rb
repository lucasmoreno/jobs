#frozen-string-literal: true
require 'json'

RSpec.describe '[POST] /charge' do
  include Rack::Test::Methods

  let(:app) { App.app }

  let(:json) do
    {
      "amount": 10,
      "card": {
        "holder": "KiiK Company",
        "number": "4200000000000000",
        "expiration_month": 12,
        "expiration_year": 2017,
        "cvv": "123"
      },
      "intermediaries": intermediaries
    }.to_json
  end

  let(:intermediaries) do
    [
      { "fee": 0, "flat": 5, "description": "Tax of KiiK" }
    ]
  end

  context 'with valid json request' do
    before { post_json json }

    context 'validating request' do
      it 'should have Content-Type: application/json' do
        expect(last_request.content_type).to eq 'application/json'
      end
    end

    context 'validating response' do
      it 'responds ok' do
        expect(last_response).to be_ok
      end

      it 'responds with json' do
        expect(last_response.content_type).to eq "application/json"
      end
    end

    context 'processing data' do
      let(:parsed_body) { JSON.parse last_response.body }

      context 'with common default response' do
        it 'responds with default structure' do
          expect(parsed_body.keys).to include 'id', 'amount', 'card_id', 'intermediaries'
          expect(parsed_body["intermediaries"]).to be_an_instance_of(Array)
        end

        it 'generates `id` key' do
          expect(parsed_body["id"]).not_to eq nil
        end

        it 'generates `card_id` key' do
          expect(parsed_body["card_id"]).not_to eq nil
        end

        it 'keeps `amount` value from request' do
          amount = JSON.parse(json)["amount"]

          expect(parsed_body["amount"]).to eq amount
        end
      end

      context 'calculating intermediaries' do
        context 'with only one intermediary' do
          subject(:response_intermediary) { parsed_body["intermediaries"][0] }

          it 'keeps values `fee`, `flat` and `description` from request' do
            request_intermediary = JSON.parse(json)["intermediaries"][0]

            expect(response_intermediary["fee"]).to eq request_intermediary["fee"]
            expect(response_intermediary["flat"]).to eq request_intermediary["flat"]
            expect(response_intermediary["description"]).to eq request_intermediary["description"]
          end

          it 'calculates intermediary\'s amount' do
            expect(response_intermediary["amount"]).to eq 5
          end

          context 'when intermediary has no fee' do
            let(:intermediaries) do
              [ { "flat": 1 }]
            end

            it 'calculates intermediary\'s amount' do
              expect(response_intermediary['amount']).to eq 1
            end
          end

          context 'when intermediary has no flat' do
            let(:intermediaries) do
              [ { "fee": 0.2 } ]
            end

            it 'calculates intermediary\'s amount' do
              expect(response_intermediary['amount']).to eq 2
            end
          end

          context 'when intermediary\'s has only description' do
            let(:intermediaries) do
              [ { "description": "Random Tax Free" }]
            end

            it 'calculates intermediary\'s amount' do
              expect(response_intermediary['amount']).to eq 0
            end
          end
        end

        context 'with more than one intermediary' do
          let(:intermediaries) do
            [
              { "fee": 0.1, "flat": 0.4, "description": "Tax Random1" },
              { "fee": 0.3, "flat": 1, "description": "Tax Random2" }
            ]
          end

          subject(:response_intermediaries) { parsed_body["intermediaries"] }

          it 'calculates intermediaries\' amount' do
            amounts = response_intermediaries.map { |h| h["amount"] }

            expect(amounts.count ).to eq 2
            expect(amounts).to match_array [1.4, 4]
          end
        end
      end
    end
  end

  context 'with invalid json request' do
    let(:json) do
      {
        "card": {
          "number": "4200000000000000",
          "expiration_month": "12",
          "expiration_year": "2017",
          "cvv": "123"
        },
        "intermediaries": intermediaries
      }.to_json
    end

    it 'responds with bad request' do
      post_json json

      expect(last_response.status).to eq 400
    end
  end

  def post_json(json)
    post '/charge', json, { "CONTENT_TYPE" => "application/json" }
  end
end
