# selenium 4
from selenium import webdriver
from selenium.webdriver.edge.options import Options
from selenium.webdriver.edge.service import Service as EdgeService
from webdriver_manager.microsoft import EdgeChromiumDriverManager

options = Options()
#options.add_argument('--headless')
options.add_argument('--no-sandbox')
options.add_argument('--disable-dev-shm-usage')
options.add_argument('--disable-extensions')
options.add_argument('--disable-gpu')
options.add_experimental_option("detach", True)
driver = webdriver.Edge(service=EdgeService(EdgeChromiumDriverManager().install()), options=options)
 
driver.get("https://python.org")
print(driver.title)
# driver.close()