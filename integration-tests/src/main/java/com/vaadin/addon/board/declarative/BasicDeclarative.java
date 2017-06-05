package com.vaadin.addon.board.declarative;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.board.declarative.BoardComponentMapper;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

@DesignRoot(value = "basic.html")
public class BasicDeclarative extends VerticalLayout {

    public BasicDeclarative() {
        Design.setComponentMapper(new BoardComponentMapper());
        Design.read(this);
    }
}
