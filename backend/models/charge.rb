module Models
  class Charge < Base
    attribute :amount, Float
    attribute :card, Card
    attribute :card_id, String
    attribute :id, String
    attribute :intermediaries, Array[Intermediary]
    attribute :inserted_at, DateTime
    attribute :updated_at, DateTime

    validates :amount, numericality: { greater_than_or_equal_to: 0 }, presence: true

    # @return [Array<Intermediary>]
    def calculate_intermediaries_amounts!
      intermediaries.map do |intermediary|
        intermediary.calculate_amount!(charge_amount: amount)
      end
    end

    # @return [Hash]
    def to_h
      {
        id: id,
        card_id: card_id,
        amount: amount,
        inserted_at: inserted_at,
        updated_at: updated_at,
        intermediaries: intermediaries.map(&:to_h)
      }
    end
  end
end
