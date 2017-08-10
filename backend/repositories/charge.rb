module Repositories
  class Charge
    #  Simulate the creation of a Charge in the database, returning the object
    # updated with id, card_id and amount for each intermediary.
    #
    # @param charge [::Models::Charge]
    # @return [::Models::Charge]
    def self.create(charge)
      charge.id = SecureRandom.uuid
      charge.card_id = SecureRandom.uuid

      charge.intermediaries.map! do |intermediary|
        intermediary.amount = charge.amount * intermediary.fee
        intermediary.amount += intermediary.flat
        intermediary
      end

      charge
    end
  end
end
