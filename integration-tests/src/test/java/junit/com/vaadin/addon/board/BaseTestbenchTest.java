package junit.com.vaadin.addon.board;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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

/**
 * Created by svenruppert on 21.04.17.
 */
public class BaseTestbenchTest extends MicroserviceBaseTest {

  @Rule public TestName testName = new TestName();

  public static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
  public static final String WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";
  public static final String PHANTOMJS_BINARY_PATH = "phantomjs.binary.path";


  //  public static final String baseUrl =
//      "http://localhost:" + MainUndertow.DEFAULT_SERVLET_PORT
//          + MainUndertow.MYAPP;
  public static final String VAADIN_TESTBENCH_DRIVER_PROPERTY = "vaadin.testbench.driver";
  public static final String FIREFOX = "firefox";
  public static final String CHROME = "chrome";
  public static final String PHANTOMJS = "phantomjs";
  public static final String JAVAFX = "javafx";
  public static final String DEAFAULT_WEB_DRIVER = PHANTOMJS;
  private static RemoteWebDriver remoteWebDriver;


  @BeforeClass
  public static void initWebdriver() throws Exception {
    // TODO extract Factory
//        System.setProperty("phantomjs.binary.path",
//            "/Users/svenruppert/Applications/phantomjs-2.0.0-macosx/bin/phantomjs");

    final String operatingSystem = "osx";
    final String relativeBaseDir = "/_data/driver/";
    final String absolutePath = new File("").getAbsolutePath();

    String absolutePathChromeDriver = absolutePath
        + relativeBaseDir + operatingSystem + "/chrome/chromedriver";
    System.setProperty(WEBDRIVER_CHROME_DRIVER, absolutePathChromeDriver);

    String absolutePathGeckoDriver = absolutePath
        + relativeBaseDir + operatingSystem + "/gecko/geckodriver";
    System.setProperty(WEBDRIVER_GECKO_DRIVER, absolutePathGeckoDriver);

    String absolutePathPhantomJS = absolutePath
        + relativeBaseDir + operatingSystem + "/phantomjs/phantomjs";
    System.setProperty(PHANTOMJS_BINARY_PATH, absolutePathPhantomJS);
    remoteWebDriver = getRemoteWebDriver();
  }

  @AfterClass
  public static void tearDownTestbench() throws Exception {
    // Calling quit() on the driver closes the test browser.
    // When called like this, the browser is immediately closed on _any_
    // error. If you wish to take a screenshot of the browser at the time
    // the error occurred, you'll need to add the ScreenshotOnFailureRule
    // to your test and remove this call to quit().
//    WebDriver driver = getDriver();
//    if (driver != null)
//      driver.quit();
    remoteWebDriver.quit();
  }

  @Before
  public void setUp() throws Exception {
    super.setUp();
    setUpTestbench();
    restartApp();
    saveScreenshot("before");
  }

  @After
  public void tearDown() throws Exception {
    saveScreenshot("after");
    //tearDownTestbench();
    super.tearDown();
  }

  //@Before
  public void setUpTestbench() throws Exception {

    setDriver(remoteWebDriver);
    //    setDriver(new PhantomJSDriver());

    // Open the test application URL with the ?restartApplication URL
    // parameter to ensure Vaadin provides us with a fresh UI instance.

//    getDriver().get(baseURL() + "?restartApplication");

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
//    String pageSource = getDriver().getPageSource();
//    String errorMsg = "Application is not available at " + baseURL()
//        + ". Server not started?";
//    Assert.assertFalse(errorMsg,
//        pageSource.contains("HTTP Status 404") || pageSource
//            .contains("can't establish a connection to the server"));
  }

  private static RemoteWebDriver getRemoteWebDriver() {

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




  protected void saveScreenshot(String name) throws IOException {
    String fileName = String.format("%s_%s.png",
        getClass().getSimpleName() + "_" + testName.getMethodName(), name);
    byte[] screenshotAs = remoteWebDriver.getScreenshotAs(OutputType.BYTES);
    File file = new File("target", fileName);
    try (final FileOutputStream fileOutputStream = new FileOutputStream(file)) {
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
        (baseURL().endsWith("/")
            ? baseURL().substring(0, baseURL().length() - 1)
            : baseURL()
        )
            + getDeploymentPath();
  }

  protected void restartApp() {
    getDriver().get(baseURL() + "?restartApplication");
  }

  protected void openURL() {
    String url = getTestUrl();
    WebDriver driver = getDriver();
    driver.get(url);
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
