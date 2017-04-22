package junit.com.vaadin.addon.board;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.rapidpm.ddi.DI;
import org.rapidpm.microservice.Main;
import org.rapidpm.microservice.MainUndertow;
import com.vaadin.testbench.TestBenchTestCase;

/**
 * Created by svenruppert on 21.04.17.
 */
public class BaseTestbenchTest extends TestBenchTestCase {


  @Rule public TestName testName = new TestName();

  public static final String baseUrl =
      "http://localhost:" + MainUndertow.DEFAULT_SERVLET_PORT
          + MainUndertow.MYAPP;
  public static final String VAADIN_TESTBENCH_DRIVER_PROPERTY = "vaadin.testbench.driver";
  public static final String FIREFOX = "firefox";
  public static final String CHROME = "chrome";
  public static final String PHANTOMJS = "phantomjs";
  public static final String JAVAFX = "javafx";
  public static final String DEAFAULT_WEB_DRIVER = CHROME;
  private RemoteWebDriver remoteWebDriver;

  @Before
  public void setUpBase()
      throws Exception {
    DI.clearReflectionModel();
    DI.activatePackages("org.rapidpm");
    DI.activatePackages(this.getClass());
    DI.activateDI(this);
    Main.deploy();
    setUpTestbench();
    saveScreenshot("before");
  }

  //@Before
  public void setUpTestbench()
      throws Exception {

//        System.setProperty("phantomjs.binary.path",
//            "/Users/svenruppert/Applications/phantomjs-2.0.0-macosx/bin/phantomjs");

    String absolutePathChromeDriver = new File("").getAbsolutePath()
        + "/_data/driver/osx/chrome/chromedriver";
    System.setProperty("webdriver.chrome.driver", absolutePathChromeDriver);

    String absolutePathGeckoDriver = new File("").getAbsolutePath()
        + "/_data/driver/osx/gecko/geckodriver";
    System.setProperty("webdriver.gecko.driver", absolutePathGeckoDriver);

    String absolutePathPhantomJS = new File("").getAbsolutePath()
        + "/_data/driver/osx/phantomjs/phantomjs";
    System.setProperty("phantomjs.binary.path", absolutePathPhantomJS);

    remoteWebDriver = getRemoteWebDriver();
    // Create a new Selenium driver - it is automatically extended to work
    // with TestBench
    setDriver(remoteWebDriver);
    //    setDriver(new PhantomJSDriver());

    // Open the test application URL with the ?restartApplication URL
    // parameter to ensure Vaadin provides us with a fresh UI instance.
    getDriver().get(baseUrl + "?restartApplication");
    final Capabilities remoteWebDriverCapabilities = remoteWebDriver
        .getCapabilities();
    if (remoteWebDriverCapabilities != null)
      if (remoteWebDriverCapabilities.getBrowserName()
          .equals(BrowserType.PHANTOMJS)) {
        getCommandExecutor().resizeViewPortTo(1280, 768);
      }

    getCommandExecutor().enableWaitForVaadin();

    // If you deploy using WTP in Eclipse, this will fail. You should
    // update baseUrl to point to where the app is deployed.
    String pageSource = getDriver().getPageSource();
    String errorMsg = "Application is not available at " + baseUrl
        + ". Server not started?";
    Assert.assertFalse(errorMsg,
        pageSource.contains("HTTP Status 404") || pageSource
            .contains("can't establish a connection to the server"));
  }

  private RemoteWebDriver getRemoteWebDriver() {

    String webDriver = System
        .getProperty(VAADIN_TESTBENCH_DRIVER_PROPERTY, DEAFAULT_WEB_DRIVER);
    RemoteWebDriver remoteWebDriver;
    switch (webDriver.toLowerCase()) {
      case FIREFOX:
        remoteWebDriver = new FirefoxDriver();
        break;
      case CHROME:
        remoteWebDriver = new ChromeDriver();
        break;
      case PHANTOMJS:
        remoteWebDriver = new PhantomJSDriver();
        break;
//      case JAVAFX:
//        // JavaFX browser comes preinstalled with JavaFX
//        // UserAgent needs to be tuned though: https://github.com/MachinePublishers/jBrowserDriver/issues/260
//        remoteWebDriver = new JBrowserDriver(
//            com.machinepublishers.jbrowserdriver.Settings.builder()
//                .requestHeaders(RequestHeaders.CHROME).userAgent(
//                new UserAgent(UserAgent.Family.WEBKIT, "Google Inc.",
//                    "Win32", "Windows NT 6.1",
//                    "5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2869.0 Safari/537.36",
//                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2869.0 Safari/537.36"))
//                .build());
//        break;
      default:
        remoteWebDriver = new FirefoxDriver();
    }
    return remoteWebDriver;
  }

  @After
  public void tearDown()
      throws Exception {
    saveScreenshot("after");
    tearDownTestbench();
    Main.stop();
    DI.clearReflectionModel();
  }

  //@After
  public void tearDownTestbench()
      throws Exception {
    // Calling quit() on the driver closes the test browser.
    // When called like this, the browser is immediately closed on _any_
    // error. If you wish to take a screenshot of the browser at the time
    // the error occurred, you'll need to add the ScreenshotOnFailureRule
    // to your test and remove this call to quit().
    WebDriver driver = getDriver();
    if (driver != null)
      driver.quit();
  }

  protected void saveScreenshot(String name)
      throws IOException {
    String fileName = String.format("%s_%s.png",
        getClass().getSimpleName() + "_" + testName.getMethodName(), name);
    byte[] screenshotAs = remoteWebDriver.getScreenshotAs(OutputType.BYTES);
    File file = new File("target", fileName);
    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
      fileOutputStream.write(screenshotAs);
    }
  }

  protected String getDeploymentPath() {
    Class<?> uiClass = this.getClass();
    if (uiClass != null) {
      return "/" + uiClass.getSimpleName();
    }
    return "/";
  }

  protected String getTestUrl() {
    return
        (baseUrl.endsWith("/") ?
            baseUrl.substring(0, baseUrl.length() - 1)
            : ""
        )
        + getDeploymentPath();
  }

  protected void openURL() {
    String url = getTestUrl();
    getDriver().get(url);
    removeLicenseChecker();
  }

  //TODO this should be removed when component itself removes the license checker.
  private void removeLicenseChecker() {
    WebDriver driver = getDriver();
    JavascriptExecutor js;
    if (driver instanceof JavascriptExecutor) {
      js = (JavascriptExecutor) driver;
      js.executeScript("document.querySelector(\"vaadin-license-dialog\").style=\"display:none\";");
    }

  }

}
