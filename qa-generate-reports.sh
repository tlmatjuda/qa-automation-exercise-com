#!/bin/bash

# ================================================================================================================
#
# @author: Thabo Lebogang Matjuda
# @since: 2022-01-16
#
# ================================================================================================================

if [ -z "${BASH_VERSINFO+x}" ]; then
  GENERATE_REPORTS_SCRIPT_PATH=${0:a:h}
else
  GENERATE_REPORTS_SCRIPT_PATH=$(cd "$(dirname ${BASH_SOURCE[0]})" && pwd)
fi


# IMPORTS
# ================================================================================================================


# Generate the Allure Reports:
mvn allure:report