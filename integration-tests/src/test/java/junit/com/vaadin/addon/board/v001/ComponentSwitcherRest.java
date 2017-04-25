package junit.com.vaadin.addon.board.v001;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

/**
 * Created by svenruppert on 22.04.17.
 */
@Path("/switch")
public class ComponentSwitcherRest {

    @GET()
    @Produces("text/plain")
    public void get() {

    }


}
