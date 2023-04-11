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
    ${chrome_options}=    Evaluate    sys.modules['selenium.webdriver'].ChromeOptions()    sys, selenium.webdriver
    Call Method    ${chrome_options}    add_argument    --no-sandbox
    Call Method    ${chrome_options}    add_argument    --disable-dev-shm-usage
    Call Method    ${chrome_options}    add_argument    --disable-extensions
    Call Method    ${chrome_options}    add_argument    --disable-gpu
    Call Method    ${chrome_options}    add_argument    ---allow-running-insecure-content
    Call Method    ${chrome_options}    add_argument    --disable-web-security
    # Call Method    ${chrome_options}    add_experimental_option    detach    ${True}    
    Create Webdriver    Chrome    options=${chrome_options}
    Go To    ${LOGIN URL}
    Title Should Be    Google