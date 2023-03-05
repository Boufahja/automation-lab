package for_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URI;

public class TestGridStandaloneDocker {
    @Test
    public void myTest() throws InterruptedException {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(URI.create("http://localhost:4444/").toURL(), firefoxOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.get("http://www.google.com");
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/span/div/div/div/div[3]/div[1]/button[2]")).click();
        Thread.sleep(2000);
        driver.quit();
    }
}
