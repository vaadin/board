package com.vaadin.board.client;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.shared.Connector;
import com.vaadin.shared.ui.AbstractComponentContainerState;

public class RowState extends AbstractComponentContainerState {
    public Map<Connector, Integer> cols = new HashMap<>();

    public int usedColAmount(){
        return cols.values().stream().mapToInt(value -> value).sum();
    }







}
