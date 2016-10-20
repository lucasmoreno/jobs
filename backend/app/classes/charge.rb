require 'active_model'

class Charge
  include ActiveModel::Model

  attr_accessor :amount, :card, :intermediaries

  validates_presence_of :amount, :card, :intermediaries
end
