module Models
  class Base
    include ActiveModel::Model
    include ActiveModel::Validations
    include Virtus.model
  end
end
