# (Job Scanner)[http://job-scanner.ru]

The main aim of the project is to make it easier for applicants to get a job via labour market analysis and smart vacancy alerts

## System Design

![system-design](https://github.com/aojona/job-scanner/blob/main/img/system-design.svg)

## Components

1) `scheduler-service`

* consumes subscriptions from PostgreSQL database
* creates tasks to import vacancies by given queries
* creates tasks to analyze job data by given subscriptions
* publishes produced messages to related RabbitMQ queues

2) `vacancy-scan-service`

* recieves messages produced by `scheduler-service`
* uses `rate-limiter-service` to limit queries to HH API
* requests page of vacancies from HH API
* publishes retrieve vacancies to RabbitMQ queue

3) `rate-limiter-service`

* imposes limits on external APIs via token bucket approach

4) `vacancy-storage-service`

* receives massages produced by `vacancy-scan-service`
* sends new vacancies to RabbitMQ queue
* saves new vacancies to Redis database

5) `vacancy-notifier-service`

* consumes messages created by `vacancy-storage-service`
* creates job alerts for members who subscribed on new vacancies

6) `telegram-service`

* recieves messages produced by `vacancy-notifier-service`
* interacts with members via telegram bot
* sends users job alerts whenever new positions go live

7) `analytic-service`

* consumes tasks created by `scheduler-service`
* analyzes vacancies data by criteria: average salary, number of offers, etc.
* saves results to PostgreSQL database

8) `rest-api`

* gives access via JWT authentication using HTTP only cookie
* provides interface for members management in PostgreSQL database
* gives access to vacancy statistics

9) `web-ui`

* queries `rest-api` to display data
* allows to create account using registration form
* gives access to a personal account via login form
* allows to manage personal new vacancy subscriptions
* visualizes job vacancy statistics

10) `job-server`

* holds information about the client service applications
* allows client services to know each other ports and IP addresses

11) `config-server`

* provides distributed configuration across multiple microservices