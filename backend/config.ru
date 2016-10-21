require 'bundler/setup'
Bundler.require :default

Unreloader = Rack::Unreloader.new { App }

Unreloader.require './app.rb'
Unreloader.require './models.rb'

run Unreloader
