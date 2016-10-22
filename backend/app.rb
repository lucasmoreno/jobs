require 'securerandom'
require 'json'

require_relative 'schemas/charge_schema'

class App < Roda
  plugin :json          # render json
  plugin :json_parser   # parse request.body

  route do |r|
    r.post 'charge' do
      begin
        JSON::Validator.validate!(ChargeSchema::SCHEMA, r.params)
      rescue JSON::Schema::ValidationError => e
        r.halt [400, {}, [e.message]]
      end

      amount = r.params["amount"]

      intermediaries = r.params.fetch("intermediaries", []).map do |intermediary|
        intermediary['fee'] ||= 0
        intermediary['flat'] ||= 0
        intermediary["amount"] = amount * intermediary["fee"] + intermediary["flat"]
        intermediary
      end

      {
        id: SecureRandom.uuid,
        amount: amount,
        card_id: SecureRandom.uuid,
        intermediaries: intermediaries
      }
    end
  end
end
