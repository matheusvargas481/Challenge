#!/bin/bash

function build {
    ./gradlew build
}

function run {
    ./gradlew run &
}

case $1 in
    "build")
        build
        ;;
    "run")
        run
        ;;
    *)
esac