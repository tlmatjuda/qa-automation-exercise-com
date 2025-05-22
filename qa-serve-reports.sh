#!/bin/bash

# ================================================================================================================
#
# @author: Thabo Lebogang Matjuda
# @since: 2022-01-16
#
# ================================================================================================================

if [ -z "${BASH_VERSINFO+x}" ]; then
  SERVE_REPORTS_SCRIPT_PATH=${0:a:h}
else
  SERVE_REPORTS_SCRIPT_PATH=$(cd "$(dirname ${BASH_SOURCE[0]})" && pwd)
fi


# IMPORTS
# ================================================================================================================


# Run the Maven Tests
allure open target/allure-report