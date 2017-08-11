RSpec.describe ::API::Charge do
  describe 'POST /charge' do
    let(:payload) do
      {
        amount: amount,
        card: {
          holder: 'KiiK Company',
          number: '4200000000000000',
          expiration_month: '12',
          expiration_year: '2017',
          cvv: 123
        },
        intermediaries: [
          {
            fee: fee,
            flat: flat,
            description: 'Tax of KiiK'
          }
        ]
      }
    end
    let(:amount) { 10 }
    let(:fee) { 0 }
    let(:flat) { 5 }

    it 'returns 201' do
      post '/charge', payload.to_json

      expect(last_response).to be_created
    end

    it 'calculates the amout for the intermediaries' do
      post '/charge', payload.to_json

      response_hash = JSON.parse(last_response.body)

      expect(response_hash['intermediaries'].first['amount']).to eq amount * fee + flat
    end

    context 'when it receives an invalid payload' do
      let(:invalid_payload) { { amount: -1 } }

      it 'returns 400' do
        post '/charge', invalid_payload.to_json

        expect(last_response).to be_bad_request
      end

      it 'expose the errors in invalid_attributes' do
        post '/charge', invalid_payload.to_json

        response_hash = JSON.parse(last_response.body)

        expect(response_hash['invalid_attributes']).to_not be_empty
      end
    end
  end
end
