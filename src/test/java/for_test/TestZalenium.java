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
import java.net.URI;
import java.net.URL;

public class TestZalenium {
    @Test
    public void test1() throws InterruptedException, MalformedURLException {
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
    }
    @Test
    public void myTestChrome() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), chromeOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.get("http://www.youtube.com");
        Thread.sleep(3000);
        //driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/span/div/div/div/div[3]/div[1]/button[2]")).click();
        //Thread.sleep(2000);
        //driver.quit();
    }
    @Test
    public void myTestFirefox() throws InterruptedException {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), firefoxOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.get("http://www.google.com");
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/span/div/div/div/div[3]/div[1]/button[2]")).click();
        Thread.sleep(2000);
        //driver.quit();
    }
    @Test
    public void myTestEdge() throws InterruptedException {
        EdgeOptions edgeOptions = new EdgeOptions();
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), edgeOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.get("http://www.facebook.com");
        Thread.sleep(3000);
        //driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/span/div/div/div/div[3]/div[1]/button[2]")).click();
        //Thread.sleep(2000);
        //driver.quit();
    }
}
