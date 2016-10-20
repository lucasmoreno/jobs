require 'active_model'

class CreditCard
  include ActiveModel::Model

  attr_accessor :holder, :number, :expiration_month, :expiration_year, :cvv

  def initialize(json)
    @holder = json['holder']
    @number = json['number']
    @expiration_month = json['expiration_month']
    @expiration_year = json['expiration_year']
    @cvv = json['cvv']
  end
end
