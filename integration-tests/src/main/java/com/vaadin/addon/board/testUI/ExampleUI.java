package com.vaadin.addon.board.testUI;

import com.vaadin.annotations.Theme;
import com.vaadin.board.Board;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
public class ExampleUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        Board board = new Board();
        board.setSizeFull();
        Button btn1 = new Button("Button 1");
        Button btn2 = new Button("Button 2");
        Button btn3 = new Button("Button 3");
        Button btn4 = new Button("Button 4");
        board.addRow(btn1,btn2,btn3,btn4);

        Label label = new Label();
        btn1.addClickListener(e->{
            label.setValue("Example");
        });
        layout.addComponents(board,label);
        setContent(layout);
    }



}
