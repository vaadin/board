package junit.com.vaadin.board;

import org.junit.Test;

import com.vaadin.board.Board;
import com.vaadin.board.Row;
import com.vaadin.ui.Button;

/**
 *
 */
public class RowRemoveColTest {

    @Test
    public void test001()
        throws Exception {

        Row row = new Board().addRow();
        Button component = new Button();
        row.addComponent(component, 4);
        row.removeColsForComponent(component);
        row.addComponent(new Button(), 3);
    }



    @Test
    public void test002()
        throws Exception {

        Row row = new Board().addRow();
        Button component = new Button();
        row.addComponent(component, 4);
        row.setCols(component, 2);
        row.addComponent(new Button(), 2);
    }



}
