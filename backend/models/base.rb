module Models
  class Base
    include ActiveModel::Model
    include ActiveModel::Validations
    include Virtus.model
    
    # @return [Boolean]
    def valid?(context = nil)
      super
      attributes.each do |key, value|
        if value.is_a? Array
          value.each { |element| add_errors(key: key, model: element) }
        else
          add_errors(key: key, model: value)
        end
      end

      errors.empty?
    end

    private

    # @param key [String]
    # @param model [Object]
    # @return [nil]
    def add_errors(key:, model:)
      if model.class.ancestors.include? ActiveModel::Validations
        errors.messages[key] = model.errors.full_messages if model.invalid?
      end
    end
  end
end
