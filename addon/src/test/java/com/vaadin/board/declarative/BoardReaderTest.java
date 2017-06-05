package com.vaadin.board.declarative;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.board.Board;
import com.vaadin.board.Row;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.declarative.DesignException;

public class BoardReaderTest {

    private static final String BOARD_WITH_ONE_ROW_CONFIG =
        "<vaadin-board size-full>\n" +
            "  <vaadin-board-row>\n" +
            "    <vaadin-label>1-1</vaadin-label>\n" +
            "  </vaadin-board-row>\n" +
            "</vaadin-board>";
    private static final String BOARD_WITH_BOARDCOLS_CONFIG = "<vaadin-board size-full>\n" +
        "  <vaadin-board-row>\n" +
        "    <vaadin-label width-full board-cols=\"2\">1-1</vaadin-label>" +
        "  </vaadin-board-row>" +
        "</vaadin-board>";
    private static final String BOARD_WITH_INNER_ROW_CONFIG = "<vaadin-board size-full>\n" +
        "  <vaadin-board-row>\n" +
        "    <vaadin-board-row> \n" +
        "      <vaadin-label>1-1</vaadin-label>" +
        "    </vaadin-board-row>" +
        "  </vaadin-board-row>" +
        "</vaadin-board>";
    private static final String BOARD_WITH_UNPARSED_CONFIG = "<vaadin-board size-full>\n" +
        " <vaadin-label>FOo Bar</vaadin-label>" +
        "  <vaadin-board-row>\n" +
        "    <vaadin-label width-full board-cols=\"2\">1-1</vaadin-label>" +
        "  </vaadin-board-row>" +
        "</vaadin-board>";

    @Before
    public void setUp() {
        Design.setComponentMapper(new BoardComponentMapper());
    }

    @Test
    public void readConfiguration_boardWithOneRow_parsed() {
        Board board = createBoard(BOARD_WITH_ONE_ROW_CONFIG);
        int count = 0;
        Iterator<Component> iter = board.iterator();
        while (iter.hasNext()) {
            Component comp = iter.next();
            Row row = (Row) comp;
            Assert.assertEquals("Row has one component", 1, row.getComponentCount());
            Iterator<Component> rowComps = row.iterator();
            while (rowComps.hasNext()) {
                Component rowComp = rowComps.next();
                Assert.assertTrue("Component inside Row is Vaadin label", rowComp instanceof Label);
            }
            count++;
        }
        Assert.assertEquals("Board has one row", 1, count);
    }

    @Test
    public void readConfiguration_rowHasAttributes_parsed() {
        Board board = createBoard(BOARD_WITH_BOARDCOLS_CONFIG);
        Iterator<Component> iter = board.iterator();
        while (iter.hasNext()) {
            Component comp = iter.next();
            Row row = (Row) comp;
            Assert.assertEquals("Row has one component", 1, row.getComponentCount());
            Iterator<Component> rowComps = row.iterator();
            while (rowComps.hasNext()) {
                Label label = (Label) rowComps.next();
                int boardCols = row.getCols(label);
                Assert.assertEquals("Label board cols attribute is 2", 2, boardCols);
            }
        }
    }

    @Test
    public void readConfiguration_rowWithInnerRows_parsed() {

        Board board = createBoard(BOARD_WITH_INNER_ROW_CONFIG);
        Iterator<Component> iter = board.iterator();
        while (iter.hasNext()) {
            Component comp = iter.next();
            Row row = (Row) comp;
            Iterator<Component> innerRows = row.iterator();
            while (innerRows.hasNext()) {
                Row innerRow = (Row) innerRows.next();
                Iterator<Component> compsIter = innerRow.iterator();
                int count = 0;
                while (compsIter.hasNext()) {
                    Component label = compsIter.next();
                    Assert.assertTrue("Inner Row has Vaadin label", label instanceof Label);
                    count++;
                }
                Assert.assertEquals("Row has one inner row", 1, count);
            }
        }

    }

    @Test(expected = DesignException.class)
    public void readConfiguration_boardIncludesNotARowComponent_raisesException()
        throws UnsupportedEncodingException {
        createBoard(BOARD_WITH_UNPARSED_CONFIG);
    }

    private Board createBoard(String design) {
        try {
            return (Board) Design.read(new ByteArrayInputStream(design.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
