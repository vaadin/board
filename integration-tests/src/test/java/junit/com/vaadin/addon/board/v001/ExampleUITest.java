package junit.com.vaadin.addon.board.v001;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rapidpm.vaadin.addon.board.bootstrap.core.JumpstartUIComponentFactory;
import junit.com.vaadin.addon.board.BaseTestbenchTest;
import junit.com.vaadin.addon.board.MicroserviceBaseTest;
import com.vaadin.board.Board;
import com.vaadin.server.VaadinRequest;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ExampleUITest extends BaseTestbenchTest {


  @Before
  public void setUp() throws Exception {
    super.setUp();
    openURL();
  }

  @Test
  public void test001() {
    ButtonElement btn1 = $(ButtonElement.class).get(0);
    btn1.click();
    LabelElement label = $(LabelElement.class).get(0);
    Assert.assertEquals("Example", label.getText());


    Client client = ClientBuilder.newClient();
    final String generateBasicReqURL = baseRestURL(ComponentSwitcherRest.class);
    System.out.println("generateBasicReqURL = " + generateBasicReqURL);
    String val = client
        .target(generateBasicReqURL)
        .request()
        .get(String.class);
    System.out.println("val = " + val);
    client.close();

  }


  @Test
  public void test002() {
    ButtonElement btn1 = $(ButtonElement.class).get(0);
    btn1.click();
    LabelElement label = $(LabelElement.class).get(0);

    Assert.assertEquals("Example", label.getText());
  }


}
