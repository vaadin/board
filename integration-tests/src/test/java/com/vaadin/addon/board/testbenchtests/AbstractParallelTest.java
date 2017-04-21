package com.vaadin.addon.board.testbenchtests;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vaadin.testbench.annotations.BrowserConfiguration;
import com.vaadin.testbench.annotations.RunOnHub;
import com.vaadin.testbench.parallel.BrowserUtil;
import com.vaadin.testbench.parallel.ParallelTest;

@RunOnHub("tb3-hub.intra.itmill.com")
public abstract class AbstractParallelTest extends ParallelTest {

    @Before
    public void setup() throws Exception {
        super.setup();
        openURL();
    }

    protected List<DesiredCapabilities> allBrowsers = null;

    /**
     * @return all supported browsers which are actively tested
     */
    public List<DesiredCapabilities> getAllBrowsers() {
        final String FIREFOX_VERSION = "45";

        if (allBrowsers == null) {
            allBrowsers = new ArrayList<DesiredCapabilities>();
            allBrowsers.add(BrowserUtil.ie11());

            allBrowsers.add(BrowserUtil.chrome());
            // Selenium doesn't support PhantomJS with Shadow DOM
//            allBrowsers.add(BrowserUtil.phantomJS());

            //Selenium has issue with firefox and webcomponents
            // https://github.com/webcomponents/shadydom/issues/145
//            DesiredCapabilities firefox = DesiredCapabilities.firefox();
//            firefox.setVersion(FIREFOX_VERSION);
//            firefox.setPlatform(Platform.WINDOWS);
//            firefox.setCapability(FirefoxDriver.MARIONETTE, false);
//            allBrowsers.add(firefox);



        }
        return Collections.unmodifiableList(allBrowsers);
    }

    @BrowserConfiguration
    public List<DesiredCapabilities> getBrowserConfiguration() {
        return getAllBrowsers();
    }

    public AbstractParallelTest() {
        super();
    }

    protected String getPort() {
        if ((getRunLocallyBrowser() != null)) {
            return "8080";
        } else if (getRunOnHub(getClass()) != null) {
            return "8080";
        }
        else {
            // can't find any configuration to setup WebDriver
            throw new IllegalArgumentException(
                "Can't instantiate WebDriver: No configuration found. Test case was not annotated with @RunLocally annotation nor @RunOnHub annotation, and system variable 'useLocalWebDriver' was not found or not set to true.");
        }
    }
    protected String getBaseURL() {
        //getHubHostname
        return "http://"+findAutoHostname()+":"+getPort();
    }

    protected abstract Class<?> getUIClass();

    protected String getDeploymentPath() {
        Class<?> uiClass = getUIClass();
        if (uiClass != null) {
            return "/" + uiClass.getSimpleName();
        }
        return "/";
    }

    protected String getTestUrl() {
        String baseUrl = getBaseURL();
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }

        return baseUrl + getDeploymentPath();
    }

    private String findAutoHostname() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface
                .getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface current = interfaces.nextElement();
                if (!current.isUp() || current.isLoopback()
                    || current.isVirtual()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = current.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress current_addr = addresses.nextElement();
                    if (current_addr.isLoopbackAddress()) {
                        continue;
                    }
                    if (current_addr.isSiteLocalAddress()) {
                        return current_addr.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException("Could not enumerate ");
        }
        throw new RuntimeException(
            "No compatible (192.168.*) ip address found.");
    }


    protected void openURL() {
        String url = getTestUrl();
        getDriver().get(url);
        removeLicenseChecker();
    }

    //TODO this should be removed when component itself removes the license checker.
    private void  removeLicenseChecker(){
        WebDriver driver = getDriver();
        JavascriptExecutor js;
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor) driver;
            js.executeScript("var elem = document.querySelector(\"vaadin-license-dialog\");"
                + "if(elem) {elem.style=\"display:none\";}");
        }

    }
}
