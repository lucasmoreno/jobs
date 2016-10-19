require 'active_model'

class Intermediary
  include ActiveModel::Model

  attr_accessor :fee, :flat, :description
end

