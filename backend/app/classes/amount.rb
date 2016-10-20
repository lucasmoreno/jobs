require 'active_model'

class Amount
  include ActiveModel::Model

  attr_accessor :amount

  validates_presence_of :amount

  def initialize(amount)
    @amount = amount
  end
end

