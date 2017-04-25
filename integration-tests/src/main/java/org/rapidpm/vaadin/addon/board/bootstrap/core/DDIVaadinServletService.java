package org.rapidpm.vaadin.addon.board.bootstrap.core;

import java.util.List;

import javax.annotation.PostConstruct;

import org.rapidpm.ddi.DI;
import com.vaadin.server.DefaultUIProvider;
import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.ServiceException;
import com.vaadin.server.UICreateEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;
import com.vaadin.ui.UI;

/**
 * Created by svenruppert on 06.04.17.
 */
public class DDIVaadinServletService extends VaadinServletService {

    public DDIVaadinServletService(VaadinServlet servlet,
        DeploymentConfiguration deploymentConfiguration,
        List<String> topLevelPackagesToActivated)
        throws ServiceException {

        super(servlet, deploymentConfiguration);

        topLevelPackagesToActivated.stream()
            .filter(pkg -> !DI.isPkgPrefixActivated(pkg))
            .forEach(DI::activatePackages);

        addSessionInitListener(
            event -> event.getSession().addUIProvider(new DefaultUIProvider() {
                @Override
                public UI createInstance(final UICreateEvent event) {
                    final UI instance = super.createInstance(event);
                    System.out.println("instance = " + instance);
                    return DI.activateDI(instance);
                }
            }));

        addSessionDestroyListener(event -> {
            //      VaadinSessionDestroyEvent sessionDestroyEvent = new VaadinSessionDestroyEvent(CDIUtil.getSessionId(event.getSession()));
            //      getBeanManager().fireEvent(sessionDestroyEvent);
        });
    }

    @Override
    public void handleRequest(VaadinRequest request, VaadinResponse response)
        throws ServiceException {
        super.handleRequest(request, response);
    }

    @PostConstruct
    public void initialize() {
    }
}