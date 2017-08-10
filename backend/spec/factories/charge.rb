FactoryGirl.define do
  factory :charge, class: ::Models::Charge do
    amount { 123 }
    intermediaries { build_list(:intermediary, 1) }
  end
end
