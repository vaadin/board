package com.vaadin.addon.board.testbenchtests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vaadin.testbench.parallel.ParallelTest;

public abstract class AbstractParallelTest extends ParallelTest {

    public AbstractParallelTest() {
        super();
        setDriver(new ChromeDriver());
    }

    protected String getBaseURL() {
        return "localhost:8080";
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
            js.executeScript("document.querySelector(\"vaadin-license-dialog\").style=\"display:none\";");
        }

    }
}
