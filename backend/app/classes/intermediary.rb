require 'active_model'

class Intermediary
  include ActiveModel::Model

  attr_accessor :fee, :flat, :description

  validates_presence_of :fee, :flat, :description


  def initialize(fee, flat, description)
    @fee = fee
    @flat = flat
    @description = description
  end

end

