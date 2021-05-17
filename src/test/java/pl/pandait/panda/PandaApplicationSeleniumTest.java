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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {PandaApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PandaApplicationSeleniumTest {
    private WebDriver driver;
    @LocalServerPort
    private int port;

    @BeforeEach
    public void startup() throws InterruptedException, MalformedURLException {
        FirefoxOptions capabilities = new FirefoxOptions();
        capabilities.setCapability("marionette", true);
        driver = new RemoteWebDriver(new URL("http://192.168.44.44:4444/wd/hub"), capabilities);
        driver.get(String.format("http://192.168.44.44:%d", port));
    }

    @Test
    public void test1() {
        System.out.println("Uruchamiam test 1: Sprawdzenie napisu na stronie głównej");
        WebElement greetingElement = driver.findElement(By.xpath("//p"));
        String greetingText = greetingElement.getText().trim();
        assertEquals("Get your greeting here", greetingText);
    }

    @Test
    public void test2() {
        System.out.println("Uruchamiam test 2: Sprawdzenie napisu na podstronie");
        WebElement greetingElement = driver.findElement(By.xpath("//p"));
        WebElement linkToGreetings = greetingElement.findElement(By.xpath("./a"));
        linkToGreetings.click();
        WebElement helloWorldString = driver.findElement(By.xpath("//p"));
        String newPageString = helloWorldString.getText().trim();
        assertEquals("Hello, World!", newPageString);
    }

    @AfterEach
    public void after() {
        driver.quit();
    }
}
