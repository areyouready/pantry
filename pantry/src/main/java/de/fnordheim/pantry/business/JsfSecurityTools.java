package de.fnordheim.pantry.business;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Helper class to make Security functions of Shiro available in JSF
 * Created by sebastianbasner on 16.01.17.
 */
@Named
@RequestScoped
public class JsfSecurityTools {
    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }
}
