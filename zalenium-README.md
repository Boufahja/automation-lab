# Zalenium

## What is it?
A flexible and scalable container based Selenium Grid with video recording, live preview, basic auth & dashboard.

## How to run it ?
### Prerequisites
Docker version >= 1.11.1 .
Make sure that docker info works without errors.

### Pull docker images
Pull docker-selenium : docker pull elgalu/selenium
Pull Zalenium        : docker pull dosel/zalenium

### Run Zalenium
docker run --rm -ti --name zalenium -p 4444:4444 `
      -v /var/run/docker.sock:/var/run/docker.sock `
      -v /tmp/videos:/home/seluser/videos `
      --privileged dosel/zalenium start

☝️ To stop Zalenium use : docker stop zalenium
☝️ To run multiple selenium containers use this option : --desiredContainers n

#### How to run tests ?
Define desired capabilities and point your WebDriver tests to http://localhost:4444/wd/hub

Example
@Test
    public void testChrome() throws InterruptedException, MalformedURLException {
        URL url = new URL("http://localhost:4444/wd/hub");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        caps.setCapability("zal:name", "myTestName");
        caps.setCapability("zal:build", "myTestBuild");
        caps.setCapability("zal:tz", "Europe/Berlin");
        caps.setCapability("zal:screenResolution", "1280x720");
        caps.setCapability("zal:idleTimeout", 180);
        caps.setCapability("zal:recordVideo", true);
        WebDriver driver = new RemoteWebDriver(url, caps);

        driver.get("http://www.youtube.com");
        Thread.sleep(5000);
        driver.quit();
    }

####  Zalenium tools
Console      : http://localhost:4444/grid/console
Live Preview : http://localhost:4444/grid/admin/live
Dashboard    : http://localhost:4444/dashboard/

## How to solve some known issues
1- After you run zalenium, if elgalu/selenium is not running and other containers are running instead, you must delete all images except elgalu/selenium and dosel/zalenium and re-run zalenium (i don't have explanation for that).
2- A simple way to check if Zalenium is ready, is to run a get request using http://localhost:4444/wd/hub/status and if the endpoint returns an HTTP code 200 and the value for the key ready is true, it means that Zalenium is ready to receive tests requests.
3- Another way to check if Zalenium is ready, is to look in terminal log 
and see that nodes are registered like the example below :

17:06:26.391 [Thread-13] INFO  d.z.e.z.registry.ZaleniumRegistry - Registered a node http://172.17.0.3:40000
17:06:27.838 [Thread-14] INFO  d.z.e.z.registry.ZaleniumRegistry - Registered a node http://172.17.0.4:40001
4- When you execute tests using Chrome as a browser and the error bellow appears, you must open elgalu/selenium containers in terminal and execute this command : 
$ sudo chmod 1777 /dev/shm 

Error : org.openqa.selenium.WebDriverException: unknown error: Chrome failed to start: crashed.
(unknown error: DevToolsActivePort file doesn't exist)
(The process started from chrome location /usr/bin/google-chrome is no longer running, so ChromeDriver is assuming that Chrome has crashed.) 
☝️ We can write a script that executes this command in all running elgalu/selenium containers