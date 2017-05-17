package com.vaadin.addon.board.testUI;

import static com.vaadin.addon.board.testUI.UIFunctions.testLayout;

import java.util.stream.Stream;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
public class Dash27VerticalLayoutUI extends AbstractTestUI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Button a1 = new Button("A1");
        a1.setSizeFull();
        Button a2 = new Button("A2");
        a2.setSizeFull();
        Button a3 = new Button("A3");
        a3.setSizeFull();
        setContent(
            testLayout()
                .apply(Stream.of(
                    new VerticalLayout(a1),
                    new VerticalLayout(a2),
                    new VerticalLayout(a3))
                ));
    }
}
