#!/bin/bash
cd /home/kavia/workspace/code-generation/habitflow-71081-45421440/android_frontend_workspace/android_frontend
./gradlew lint
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

