require 'sinatra'
require 'faker'
require 'require_all'
require_all 'app/classes'
require 'json'

get '/' do
  JSON.pretty_generate({author: 'Vitor Duque',
                        contact: 'vtrduque@gmail.com',
                        linkedin: 'www.linkedin.com/in/vitor-duque-538723124',
                        github: 'github.com/vitorduque'})
end


not_found do
  status 404
  {code: '404', message: 'page not found'}.to_json
end


