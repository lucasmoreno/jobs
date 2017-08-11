module Models
  class Card < Base
    attribute :cvv, String
    attribute :expiration_month, String
    attribute :expiration_year, String
    attribute :holder, String
    attribute :number, String

    validates :cvv, :expiration_month, :expiration_year, :holder, :number, presence: true
  end
end
