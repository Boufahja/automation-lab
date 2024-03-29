import time
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.chrome.service import Service as ChromeService
from webdriver_manager.chrome import ChromeDriverManager
 
options = Options()
#options.add_argument('--headless')
options.add_argument('--no-sandbox')
options.add_argument('--disable-dev-shm-usage')
options.add_argument('--disable-extensions')
options.add_argument('--disable-gpu')
options.add_experimental_option("detach", True)
driver = webdriver.Chrome(service=ChromeService(ChromeDriverManager().install()), options=options)
 
driver.get("https://python.org")
print(driver.title)
# time.sleep(5) 
# driver.close()