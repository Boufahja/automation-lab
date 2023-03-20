from selenium import webdriver
from selenium.webdriver.firefox.options import Options
from webdriver_manager.firefox import GeckoDriverManager
 
options = Options()
#options.add_argument('--headless')
options.add_argument('--no-sandbox')
options.add_argument('--disable-dev-shm-usage')
driver = webdriver.Firefox(executable_path=GeckoDriverManager().install(), options=options)
 
driver.get("https://python.org")
print(driver.title)
driver.close()