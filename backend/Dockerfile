FROM ruby:2.3
MAINTAINER Vitor Duque "vtrduque@gmail.com"

ADD app /app

RUN gem install bundler

RUN cd app/ && bundle install

CMD ["ruby", "app/app.rb"]

