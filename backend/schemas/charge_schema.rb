module ChargeSchema
  SCHEMA = {
    type: :object,
    require: %w[amount card],
    properties: {
      amount: { type: :number, minimum: 0 },
      card: {
        type: :object,
        required: %w[holder number expiration_month expiration_year cvv],
        properties: {
          holder: { type: :string },
          number: { type: :string, minLength: 16, maxLength: 16, pattern: /\d{16}/ },
          expiration_month: { type: :number, minimum: 1, maximum: 12 },
          expiration_year: { type: :number, minimum: Date.today.year },
        }
      },
      intermediaries: {
        type: :array,
        items: [
          {
            type: :object,
            properties: {
              fee: { type: :number, minimum: 0 },
              flat: { type: :number, minimum: 0 },
              description: { type: :string }
            }
          }
        ]
      }
    }
  }
end
