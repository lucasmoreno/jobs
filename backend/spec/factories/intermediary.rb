FactoryGirl.define do
  factory :intermediary, class: ::Models::Intermediary do
    fee { 0.1 }
    flat { 2 }
    description { 'le_intermediary' }
    amount { 0 }
  end
end
