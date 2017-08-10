# Gems
require 'active_model'
require 'json'
require 'sinatra'
require 'sinatra/json'
require 'virtus'

# APIs
require_relative './api/charge'

# Models
require_relative './models/base'
require_relative './models/intermediary'
require_relative './models/card'
require_relative './models/charge'

# Repositories
require_relative './repositories/charge'

run API::Charge
