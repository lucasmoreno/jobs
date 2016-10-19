require 'active_model'

class Charge
  include ActiveModel::Model

  attr_accessor :amount, :card, :intermediaries

end
