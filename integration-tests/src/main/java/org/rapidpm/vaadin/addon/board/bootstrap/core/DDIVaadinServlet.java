package org.rapidpm.vaadin.addon.board.bootstrap.core;

import java.util.List;

import javax.servlet.ServletException;

import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.ServiceException;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;

/**
 * Created by svenruppert on 06.04.17.
 */
public abstract class DDIVaadinServlet extends VaadinServlet {

    @Override
    protected void servletInitialized()
        throws ServletException {
        super.servletInitialized();

    }

    @Override
    protected VaadinServletService createServletService(
        final DeploymentConfiguration deploymentConfiguration)
        throws ServiceException {
        final DDIVaadinServletService service = new DDIVaadinServletService(
            this, deploymentConfiguration, topLevelPackagesToActivate());
        service.init();
        return service;
    }

    /**
     * return a list of pkg names that are available for Injection
     *
     * @return return
     */
    public abstract List<String> topLevelPackagesToActivate();
}