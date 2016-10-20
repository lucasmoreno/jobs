require 'sinatra'
require 'securerandom'
require 'require_all'
require_all 'app/classes'
require 'json'

#permit external access :)
set :bind, '0.0.0.0'

#author
get '/' do
  JSON.pretty_generate({author: 'Vitor Duque',
                        contact: 'vtrduque@gmail.com',
                        linkedin: 'www.linkedin.com/in/vitor-duque-538723124',
                        github: 'github.com/vitorduque'})
end

post '/charge' do
  request_json = JSON.parse(request.body.read)
  response = {}

  #create objects based on request
  intermediaries = request_json['intermediaries'].map {|i| Intermediary.new(i['fee'], i['flat'], i['description'])}
  card = CreditCard.new(request_json['card'])
  amount = Amount.new(request_json['amount'])

  #use active record validations present in active model to see if some param is missing
  if ((intermediaries.collect { |t| t.valid? }).all? && card.valid? && amount.valid?)
    response = {id: SecureRandom.uuid, amount: amount.amount, card_id: SecureRandom.uuid, intermediaries: intermediaries.map{|i| {fee: i.fee, flat: i.flat, description: i.description}}}.to_json
  else
    status 500
  end

  #json
  response
end

not_found do
  status 404
  {code: '404', message: 'page not found'}.to_json
end

error 500 do
  status 500
  {code: '500', message: 'wrong parameters'}.to_json
end
