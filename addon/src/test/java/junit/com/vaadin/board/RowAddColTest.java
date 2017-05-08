package junit.com.vaadin.board;

import static junit.com.vaadin.board.RowTestHelperFunctions.createButtonRow;
import static org.junit.Assert.assertEquals;
import java.util.stream.IntStream;

import org.junit.Test;

import com.vaadin.board.Board;
import com.vaadin.ui.Button;

/**
 *
 */
public class RowAddColTest {

    @Test
    public void test001()
        throws Exception {
        IntStream
            .range(1, 5)
            .forEach(i -> assertEquals(i, createButtonRow().apply(i).usedColAmount()));
    }

    @Test(expected = IllegalStateException.class)
    public void test002()
        throws Exception {
        new Board().addRow().addComponent(new Button(), 5);
    }

    @Test(expected = IllegalStateException.class)
    public void test003()
        throws Exception {
        new Board().addRow().addComponent(new Button(), 0);
    }

    @Test(expected = IllegalStateException.class)
    public void test004()
        throws Exception {
        new Board().addRow().addComponent(new Button(), -1);
    }

    @Test(expected = IllegalStateException.class)
    public void test005A()
        throws Exception {
        createButtonRow().apply(1).addComponent(new Button(), 4);
    }

    @Test()
    public void test005B()
        throws Exception {
        createButtonRow().apply(1).addComponent(new Button(), 3);
    }

}
