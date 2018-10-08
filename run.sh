#!/bin/bash

mvn compile jib:dockerBuild
docker-compose up
