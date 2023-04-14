*** Settings ***
Library           SeleniumLibrary

*** Variables ***
${LOGIN URL}      https://www.google.fr/

*** Test Cases ***
Valid Login
    Open Browser To Google Page
    # [Teardown]    Close Browser
    
*** Keywords ***
Open Browser To Google Page
    ${edge_options}=    Evaluate    sys.modules['selenium.webdriver'].EdgeOptions()    sys, selenium.webdriver
    Call Method    ${edge_options}    add_argument    --no-sandbox
    Call Method    ${edge_options}    add_argument    --disable-dev-shm-usage
    Call Method    ${edge_options}    add_argument    --disable-extensions
    Call Method    ${edge_options}    add_argument    --disable-gpu
    Call Method    ${edge_options}    add_argument    ---allow-running-insecure-content
    Call Method    ${edge_options}    add_argument    --disable-web-security
    Call Method    ${edge_options}    add_experimental_option    detach    ${True}  
    Create Webdriver    Edge    options=${edge_options}
    Go To    ${LOGIN URL}
    Title Should Be    Google