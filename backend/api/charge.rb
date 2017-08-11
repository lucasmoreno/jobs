module API
  class Charge < ::Sinatra::Base
    CREATED_STATUS_CODE = 201
    BAD_REQUEST_STATUS_CODE = 400

    post '/charge' do
      payload = JSON.parse(request.body.read)
      charge = ::Models::Charge.new(payload)

      if charge.valid?
        charge.calculate_intermediaries_amounts!
        created_charge = ::Repositories::Charge.create(charge)
        status CREATED_STATUS_CODE
        json created_charge.to_h
      else
        status BAD_REQUEST_STATUS_CODE
        json invalid_attributes: charge.errors.full_messages
      end
    end
  end
end
