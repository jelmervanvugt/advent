#!/usr/bin/env sh

if [ $# -eq 0 ]; then
    echo "Please provide the day as an argument (e.g., day01)"
    exit 1
fi

echo "Running advent $1 ..."

tsc ../"$1"/index.ts
node ../"$1"/index.js