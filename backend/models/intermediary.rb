module Models
  class Intermediary < Base
    attribute :amount, Float, default: 0
    attribute :description, String
    attribute :fee, Float, default: 0
    attribute :flat, Float, default: 0

    validates :amount, :fee, :flat, numericality: { greater_than_or_equal_to: 0 }
    validates :fee, numericality: { less_than_or_equal_to: 1 }
    validates :description, presence: true

    # @param charge_amount [Float]
    # @return [Float]
    def calculate_amount!(charge_amount:)
      self.amount = charge_amount * fee + flat
    end
  end
end
