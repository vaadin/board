package junit.com.vaadin.addon.board;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.rapidpm.ddi.DI;
import org.rapidpm.microservice.Main;
import com.vaadin.testbench.TestBenchTestCase;

/**
 * Created by svenruppert on 21.04.17.
 */
public abstract class MicroserviceBaseTest extends TestBenchTestCase {


  @Before
  public void setUp() throws Exception {
    DI.clearReflectionModel();
    DI.activatePackages("org.rapidpm");
    DI.activatePackages("com.vaadin.addon");
    DI.activatePackages(this.getClass().getPackage().getName());
    Main.deploy();
  }


  @After
  public void tearDown() throws Exception {
    Main.stop();
    DI.clearReflectionModel();
    getDriver().quit();
  }

  @Test
  public void test001() throws Exception {


  }
}
