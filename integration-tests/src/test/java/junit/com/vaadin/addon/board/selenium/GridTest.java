package junit.com.vaadin.addon.board.selenium;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.vaadin.testbench.annotations.BrowserFactory;
import com.vaadin.testbench.annotations.RunOnHub;

/**
 * Created by svenruppert on 22.04.17.
 */


//@BrowserFactory(VaadinBrowserFactory.class)
//@RunOnHub("localhost:4444")
//@RunOnHub() //localhost
public class GridTest {


  public static final String EXTERNAL_IP = "10.64.12.132";
  static WebDriver driver;

  //Setup Driver
  @BeforeClass
  public static void setupTest() throws MalformedURLException {
    DesiredCapabilities caps = DesiredCapabilities.chrome();
    caps.setPlatform(Platform.LINUX);
    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
  }

  @Test
  public void test001() {
    //Navigate to facebook.com
    driver.navigate().to("http://" + EXTERNAL_IP + ":7081/rest/metrics/health");
    final String pageSource = driver.getPageSource();
    System.out.println("pageSource = " + pageSource);
    Assert.assertFalse(pageSource.isEmpty());
    Assert.assertTrue(pageSource.contains("\"deploymentName\": \"ROOT.war\","));
//    driver.manage().window().maximize();

    //Login Button
//    WebElement loginButton = driver.findElement(By.id("loginbutton"));

    //Actions example
  }

  //Close Driver
  @AfterClass
  public static void quitDriver() {
    driver.quit();
  }
}
