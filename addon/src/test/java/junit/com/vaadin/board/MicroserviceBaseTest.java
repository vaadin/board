package junit.com.vaadin.board;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.rapidpm.ddi.DI;
import org.rapidpm.microservice.Main;

/**
 * Created by svenruppert on 21.04.17.
 */
public class MicroserviceBaseTest {


  @Before
  public void setUp() throws Exception {
    DI.clearReflectionModel();
    DI.activatePackages("org.rapidpm");
    DI.activatePackages("com.vaadin.board");
    DI.activatePackages(this.getClass().getPackage().getName());
    Main.deploy();
  }


  @After
  public void tearDown() throws Exception {
    Main.stop();
    DI.clearReflectionModel();
  }

  @Test
  public void test001() throws Exception {



  }
}
