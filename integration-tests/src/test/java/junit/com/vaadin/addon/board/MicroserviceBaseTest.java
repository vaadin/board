package junit.com.vaadin.addon.board;


import static java.lang.System.getProperty;
import static org.rapidpm.microservice.MainUndertow.CONTEXT_PATH_REST;
import static org.rapidpm.microservice.MainUndertow.DEFAULT_SERVLET_PORT;
import static org.rapidpm.microservice.MainUndertow.MYAPP;
import static org.rapidpm.microservice.MainUndertow.REST_HOST_PROPERTY;
import static org.rapidpm.microservice.MainUndertow.REST_PORT_PROPERTY;
import static org.rapidpm.microservice.MainUndertow.SERVLET_HOST_PROPERTY;
import static org.rapidpm.microservice.MainUndertow.SERVLET_PORT_PROPERTY;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.rapidpm.ddi.DI;
import org.rapidpm.dependencies.core.net.PortUtils;
import org.rapidpm.microservice.Main;
import org.rapidpm.microservice.MainUndertow;
import org.rapidpm.microservice.test.RestUtils;
import com.vaadin.testbench.TestBenchTestCase;

/**
 * Created by svenruppert on 21.04.17.
 */
public abstract class MicroserviceBaseTest extends TestBenchTestCase {


  @BeforeClass
  public static void setUpClass() {
    final PortUtils portUtils = new PortUtils();

    System.setProperty(REST_HOST_PROPERTY, "127.0.0.1");
    System.setProperty(SERVLET_HOST_PROPERTY, "127.0.0.1");

    System.setProperty(REST_PORT_PROPERTY, portUtils.nextFreePortForTest() + "");
    System.setProperty(SERVLET_PORT_PROPERTY, portUtils.nextFreePortForTest() + "");
  }

  @Before
  public void setUp() throws Exception {
    DI.clearReflectionModel();
    DI.activatePackages("org.rapidpm");
    DI.activatePackages("com.vaadin.addon");
    DI.activatePackages(this.getClass().getPackage().getName());
    DI.activateDI(this);

    Main.deploy();
  }

  @After
  public void tearDown() throws Exception {
    Main.stop();
    DI.clearReflectionModel();
  }

  @Test
  public void test001() throws Exception {
    //health check ??
  }

  public String baseURL() {
    final String key = SERVLET_PORT_PROPERTY;

    final String actualUsedServletPort = (getProperty(key) == null)
        ? (DEFAULT_SERVLET_PORT + "")
        : getProperty(key);

    return "http://localhost:" + actualUsedServletPort +"/" + MYAPP;
  }

  public String baseRestURL(Class restClass) {
    return new RestUtils().generateBasicReqURL(restClass, CONTEXT_PATH_REST);
  }
}
