package pl.pandait.panda;

import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;


@SpringBootTest(classes = {PandaApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PandaApplicationSeleniumTest {
    private static WebDriver driver;
    @LocalServerPort
    private int port;

    @BeforeEach
    public void startup() throws InterruptedException, MalformedURLException {
        FirefoxOptions capabilities = new FirefoxOptions();
        capabilities.setCapability("marionette", true);
        driver = new RemoteWebDriver(new URL("http://192.168.44.44:4444/wd/hub"), capabilities);
        driver.get(String.format("http://192.168.44.44:%d", port));
    }
}
