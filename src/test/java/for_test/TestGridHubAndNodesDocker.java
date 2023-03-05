package for_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URI;

public class TestGridHubAndNodesDocker {
    @Test
    public void myTestChrome() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(URI.create("http://localhost:4444/").toURL(), chromeOptions);
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
            driver = new RemoteWebDriver(URI.create("http://localhost:4444/").toURL(), firefoxOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.get("http://www.google.com");
        Thread.sleep(3000);
        //driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/span/div/div/div/div[3]/div[1]/button[2]")).click();
        //Thread.sleep(2000);
        //driver.quit();
    }
    @Test
    public void myTestEdge() throws InterruptedException {
        EdgeOptions edgeOptions = new EdgeOptions();
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(URI.create("http://localhost:4444/").toURL(), edgeOptions);
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
