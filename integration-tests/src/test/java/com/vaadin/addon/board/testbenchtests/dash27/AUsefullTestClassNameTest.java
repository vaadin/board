package com.vaadin.addon.board.testbenchtests.dash27;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.Test;

import com.vaadin.board.Board;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
public class AUsefullTestClassNameTest {

    private List<Class<? extends AbstractOrderedLayout>> classList = Arrays.asList(
        HorizontalLayout.class,
        VerticalLayout.class,
        FormLayout.class
    );

    @Test
    public void anMoreUsefullTesMethodName()
        throws Exception {
        Board board = new Board();
        board.addRow(new HorizontalLayout(new Button(), new Button()));
        HorizontalLayout layout = new HorizontalLayout(board);



    }
}
