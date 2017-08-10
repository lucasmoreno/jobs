FactoryGirl.define do
  factory :card, class: ::Models::Card do
    holder { 'Gandalf the Grey' }
    number { '120014557789957892' }
    expiration_month { '10' }
    expiration_year { '3018' }
    cvv { '042' }
  end
end
