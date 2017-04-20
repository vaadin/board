package junit.com.vaadin.board;

import org.junit.Assert;
import org.junit.Test;
import com.vaadin.board.Board;
import com.vaadin.ui.Button;

/**
 * Created by svenruppert on 20.04.17.
 */
public class BoardTest {

  @Test(expected = java.lang.RuntimeException.class)
  public void test001() throws Exception {
    final Board components = new Board();
    components.addRow(
        new Button(),
        new Button(),
        new Button(),
        new Button(),
        new Button());
    Assert.fail("Exception expected");
  }
}
