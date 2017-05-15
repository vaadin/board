package com.vaadin.addon.board.testUI;

import com.vaadin.board.Board;
import com.vaadin.board.Row;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
public class ColRemoveUI extends AbstractTestUI {

    public final static String RMV_BUTTON_ID = "removeBtn";

    @Override
    protected void init(VaadinRequest request) {

        Board board = new Board();
        //        board.setWidth(700, Unit.PIXELS);
        board.setSizeFull();

        Button btn1 = new Button("Button 1");
        Button btn2 = new Button("Button 2");

        Button btn3 = new Button("Button 3");
        Button btn4 = new Button("Button 4");

        Row row1 = board.addRow(btn1, btn2, btn3, btn4);

        Button btnA = new Button("Button A");
        Button btnB = new Button("Button B");
        Button btnC = new Button("Button C");
        Row row2 = board.addRow(btnA, btnB, btnC);
        row2.setCols(btnA, 2);

        Button removeColForBtnAButton = new Button("Remove col Btn A", e -> {
            board.removeCol(row2, btnA);
        });



        Button btnA1 = new Button("Button A1");
        Button btnB1 = new Button("Button B1");
        Row row3 = board.addRow(btnA1, btnB1);

        row3.setCols(btnA1, 2);

        Button resizeColForBtnA1Button = new Button("Resize col Btn A1", e -> {
            row3.setCols(btnA1, 2);
        });

        VerticalLayout layout = new VerticalLayout();
        layout.addComponents(board, removeColForBtnAButton, resizeColForBtnA1Button);
        setContent(layout);
    }

}
