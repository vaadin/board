package junit.com.vaadin.addon.board.selenium;

import java.net.URL;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.rapidpm.ddi.DI;
import org.rapidpm.microservice.Main;
import com.vaadin.testbench.By;
import com.vaadin.testbench.ElementQuery;
import com.vaadin.testbench.TestBench;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.VerticalLayoutElement;
import com.vaadin.ui.Label;

/**
 * Created by svenruppert on 23.04.17.
 */
//@RunOnHub() //localhost
public class GridTestbenchTest extends TestBenchTestCase {

  //public static final String EXTERNAL_IP = "10.64.12.132";
  public static final String EXTERNAL_IP = "192.168.2.47";


  private WebDriver driver;

  @Before
  public void setUp() throws Exception {
    DI.clearReflectionModel();
    DI.activatePackages("org.rapidpm");
    DI.activatePackages("com.vaadin.addon");
    DI.activatePackages(this.getClass().getPackage().getName());
    DI.activateDI(this);

    Main.deploy();

    // Require Firefox in the test node
    DesiredCapabilities capability = DesiredCapabilities.chrome();
    capability.setPlatform(Platform.LINUX);

    // Create a remote web driver that connects to a hub
    // running in the local host
    driver = TestBench
        .createDriver(
            new RemoteWebDriver(
                new URL(
                    "http://"+EXTERNAL_IP+":4444/wd/hub"), capability));
  }


  @After
  public void tearDown() throws Exception {
    driver.quit();
    Main.stop();
    DI.clearReflectionModel();
  }

  @Test
  public void test001() {

    // Then use it to run a test as you would use any web driver
    driver.navigate().to(
        "http://" + EXTERNAL_IP + ":7080/microservice/");
//    WebElement e = driver.findElement(By.xpath(
//        "//div[@class='v-tree-node-caption']" +
//            "/div[span='Desktops']"));
//    new Actions(driver).moveToElement(e).contextClick(e).perform();

    WebElement btn1 = driver.findElement(By.id("btn1"));
    new Actions(driver).click(btn1);

//    ElementQuery<VerticalLayoutElement> elementQuery = $(VerticalLayoutElement.class);
//    VerticalLayoutElement layout = elementQuery.id("content");
//    LabelElement label = layout.$(LabelElement.class).first();
//
//    Assert.assertEquals("Example", label.getText());
  }


}
