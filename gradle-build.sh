function build {
    ./$1/gradlew assemble
}

build analytic-service
build config-server
build job-server
build rate-limiter-service
build rest-api
build scheduler-service
build telegram-service
build vacancy-notifier-service
build vacancy-scan-service
build vacancy-storage-service
build web-ui