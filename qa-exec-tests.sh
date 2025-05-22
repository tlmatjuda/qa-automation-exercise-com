#!/bin/bash

# ================================================================================================================
#
# @author: Thabo Lebogang Matjuda
# @since: 2025-05-22
#
# ================================================================================================================

if [ -z "${BASH_VERSINFO+x}" ]; then
  EXEC_TESTS_SCRIPT_PATH=${0:a:h}
else
  EXEC_TESTS_SCRIPT_PATH=$(cd "$(dirname ${BASH_SOURCE[0]})" && pwd)
fi


# IMPORTS
# ================================================================================================================


# This will:
#	 •	Run all your tests using maven-surefire-plugin
#	 •	Generate Allure result files in:
# ./target/allure-results
mvn clean test