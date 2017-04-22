package junit.com.vaadin.addon.board.v001;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rapidpm.vaadin.addon.board.bootstrap.core.JumpstartUIComponentFactory;
import junit.com.vaadin.addon.board.MicroserviceBaseTest;
import com.vaadin.board.Board;
import com.vaadin.server.VaadinRequest;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ExampleUITest extends MicroserviceBaseTest {


  @Before
  public void setup() throws Exception {
    super.setup();
    openURL();
  }

  @Test
  public void test001() {
    ButtonElement btn1 = $(ButtonElement.class).get(0);
    btn1.click();
    LabelElement label = $(LabelElement.class).get(0);

    Assert.assertEquals("Example", label.getText());
  }

  @Test
  public void test002() {
    ButtonElement btn1 = $(ButtonElement.class).get(0);
    btn1.click();
    LabelElement label = $(LabelElement.class).get(0);

    Assert.assertEquals("Example", label.getText());
  }


}
