*** Settings ***
Documentation     Simple example using SeleniumLibrary.
Library           SeleniumLibrary

*** Variables ***
${LOGIN URL}      https://www.google.fr/
${BROWSER}        chrome

*** Test Cases ***
Valid Login
    Open Browser To Google Page
    [Teardown]    Close Browser

*** Keywords ***
Open Browser To Google Page
    Open Browser    ${LOGIN URL}    ${BROWSER}
    Title Should Be    Google