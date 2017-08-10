module Models
  class Intermediary < Base
    attribute :amount, Float, default: 0
    attribute :description, String
    attribute :fee, Float, default: 0
    attribute :flat, Float, default: 0

    validates :fee, numericality: { greater_than_or_equal_to: 0, less_than_or_equal_to: 1 }
    validates :flat, :amount, numericality: { greater_than_or_equal_to: 0 }
    validates_presence_of :description, :fee, :flat

    # @param charge_amount [Float]
    # @return [Float]
    def calculate_amount!(charge_amount:)
      self.amount = charge_amount * fee + flat
    end
  end
end
