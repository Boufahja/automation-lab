package for_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestZalenium {
    @Test
    public void myTestChrome() throws InterruptedException, MalformedURLException {
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
    @Test
    public void myTestFirefox() throws InterruptedException, MalformedURLException {
        URL url = new URL("http://localhost:4444/wd/hub");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.BROWSER_NAME, "firefox");
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
}
