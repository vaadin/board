package com.vaadin.board.declarative;

import com.vaadin.board.Board;
import com.vaadin.board.Row;
import com.vaadin.ui.Component;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.declarative.DesignContext;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BoardComponentMapper extends Design.DefaultComponentMapper {

    @Override
    public Component tagToComponent(String tagName, Design.ComponentFactory componentFactory, DesignContext context) {
        if(tagName.toLowerCase().equals("vaadin-board")) {
            return new Board();
        }
        else if (tagName.toLowerCase().equals("vaadin-board-row")) {
            return new Row();
        }
        else {
            return super.tagToComponent(tagName, componentFactory, context);
        }
    }

    @Override
    public String componentToTag(Component component, DesignContext context) throws NotImplementedException{
        if(component instanceof Board) {
            return "vaadin-board";
        } else if(component instanceof Row) {
            return "vaadin-board-row";
        } else {
            return super.componentToTag(component, context);
        }
    }
}
