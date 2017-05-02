package org.rapidpm.vaadin.addon.board.bootstrap.core;

import java.io.Serializable;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.ComponentContainer;

/**
 * Created by svenruppert on 06.04.17.
 */
@FunctionalInterface
public interface JumpstartUIComponentFactory extends Serializable {
    ComponentContainer createComponentToSetAsContent(
        VaadinRequest vaadinRequest);
}
