package junit.com.vaadin.addon.board.v002;

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

public class ExampleFooUITest extends BaseTestbenchTest {

    public static class MyUIComponentFactory implements JumpstartUIComponentFactory {
        @Override
        public ComponentContainer createComponentToSetAsContent(final VaadinRequest vaadinRequest) {
            VerticalLayout layout = new VerticalLayout();
            Board board = new Board();
            board.setSizeFull();
            Button btn1 = new Button("Button 1");
            Button btn2 = new Button("Button 2");
            Button btn3 = new Button("Button 3");
            Button btn4 = new Button("Button 4");
            board.addRow(btn1, btn2, btn3, btn4);

            Label label = new Label();
            btn1.addClickListener(e -> {
                label.setValue("ExampleFoo");
            });
            layout.addComponents(board, label);
            return layout;
        }
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        openURL();
    }


    @Test
    public void test1() {
        ButtonElement btn1 = $(ButtonElement.class).get(0);
        btn1.click();
        LabelElement label = $(LabelElement.class).get(0);

        Assert.assertEquals("ExampleFoo",label.getText());
    }


}
