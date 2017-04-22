package org.rapidpm.vaadin.addon.board.bootstrap.core;

import javax.inject.Inject;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

/**
 * Created by svenruppert on 06.04.17.
 */
@Theme("valo")
//@Push
@Widgetset("widgetset.AppWidgetSet")
public class JumpstartUI extends UI {

    @Inject JumpstartUIComponentFactory jumpstartUIComponentFactory;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final ComponentContainer mainComponent = jumpstartUIComponentFactory
            .createComponentToSetAsContent(vaadinRequest);
        setContent(mainComponent);
    }
}
