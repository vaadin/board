package junit.com.vaadin.addon.board.v001;

import org.rapidpm.vaadin.addon.board.bootstrap.core.JumpstartUIComponentFactory;
import com.vaadin.board.Board;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by svenruppert on 21.04.17.
 */
public class MyUIComponentFactory implements JumpstartUIComponentFactory {

  //@Inject LoginScreenCustom loginScreen;

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
      label.setValue("Example");
    });
    layout.addComponents(board, label);
    return layout;
  }
}
