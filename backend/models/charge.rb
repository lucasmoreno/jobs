module Models
  class Charge < Base
    attribute :amount, Float
    attribute :card, Card
    attribute :card_id, String
    attribute :id, String
    attribute :intermediaries, Array[Intermediary]

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
        intermediaries: intermediaries.map(&:to_h)
      }
    end
  end
end
