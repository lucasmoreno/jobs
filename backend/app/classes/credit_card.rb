require 'active_model'

class CreditCard
  include ActiveModel::Model

  attr_accessor :holder, :number, :expiration_month, :expiration_year, :cvv


end
