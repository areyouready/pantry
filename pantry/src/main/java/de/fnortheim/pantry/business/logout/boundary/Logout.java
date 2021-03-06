package de.fnortheim.pantry.business.logout.boundary;

import de.fnortheim.pantry.business.logging.boundary.LogSink;
import org.apache.shiro.SecurityUtils;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

/**
 * Created by sebastianbasner on 16.01.17.
 */
@Named
@RequestScoped //automatic CDI injection when requested
public class Logout {

    @Inject
    LogSink LOG;

    /**
     * Shiro logout for the current user
     */
    public void submit() throws IOException {
        final String username = SecurityUtils.getSubject().getPrincipal().toString();
        if (SecurityUtils.getSubject().hasRole("root")) {
            final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            SecurityUtils.getSubject().logout();
            externalContext.invalidateSession();  // cleanup user related session state
            externalContext.redirect("login.xhtml?faces-redirect=true");
        }
        LOG.log("-------- User: " + username + " logged out --------");
    }

}
