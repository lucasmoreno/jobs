module Repositories
  class Charge
    #  Simulate the creation of a Charge in the database, returning the object
    # updated with id and card_id.
    #
    # @param charge [::Models::Charge]
    # @return [::Models::Charge]
    def self.create(charge)
      charge.id = SecureRandom.uuid
      charge.card_id = SecureRandom.uuid
      charge
    end
  end
end
