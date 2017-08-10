module API
  class Charge < ::Sinatra::Base
    post '/charges' do
      payload = JSON.parse(request.body.read)
      charge = ::Models::Charge.new(payload)

      if charge.valid?
        charge.calculate_intermediaries_amounts!
        created_charge = ::Repositories::Charge.create(charge)
        json created_charge.to_h
      else
        status 400
        json invalid_attributes: charge.errors.full_messages
      end
    end
  end
end
