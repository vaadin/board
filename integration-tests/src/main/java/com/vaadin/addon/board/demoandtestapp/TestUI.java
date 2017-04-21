package com.vaadin.addon.board.demoandtestapp;

import com.vaadin.annotations.Theme;
import com.vaadin.board.Board;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

@Theme("mytheme")
public class TestUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        Board board = new Board();
        board.setSizeFull();
        Button btn1 = new Button("Button 1");
        Button btn2 = new Button("Button 2");
        board.addRow(btn1,btn2);
        setContent(board);
    }



}
