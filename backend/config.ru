require 'bundler/setup'
Bundler.require :default

Unreloader = Rack::Unreloader.new { App }

Unreloader.require './app.rb'

run ENV['RACK_ENV'] == 'production' ? App.freeze.app : Unreloader
