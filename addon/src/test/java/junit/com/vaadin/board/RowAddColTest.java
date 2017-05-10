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
    public void testFrom1To4()
        throws Exception {
        IntStream
            .range(1, 5)
            .peek(p -> {System.out.println("test001 p = " + p);})
            .forEachOrdered(i -> assertEquals(i, createButtonRow().apply(i).usedColAmount()));
    }

    @Test(expected = IllegalStateException.class)
    public void testWithColValue5_IllegalStateException()
        throws Exception {
        new Board().addRow().addComponent(new Button(), 5);
    }

    @Test(expected = IllegalStateException.class)
    public void testWithColValue0_IllegalStateException()
        throws Exception {
        new Board().addRow().addComponent(new Button(), 0);
    }

    @Test(expected = IllegalStateException.class)
    public void testWithColValueMinusOne_IllegalStateException()
        throws Exception {
        new Board().addRow().addComponent(new Button(), -1);
    }

    @Test(expected = IllegalStateException.class)
    public void testWithOnePlus4_IllegalStateException()
        throws Exception {
        createButtonRow().apply(1).addComponent(new Button(), 4);
    }

    @Test()
    public void testWithOnePlus3()
        throws Exception {
        createButtonRow().apply(1).addComponent(new Button(), 3);
    }

}
