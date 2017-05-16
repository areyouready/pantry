package de.fnordheim.pantry.business;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by sebastianbasner on 16.05.17.
 */
public class ExtendedAuthenticationFilter extends FormAuthenticationFilter {

    private final Logger log = LoggerFactory.getLogger(ExtendedAuthenticationFilter.class);

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        log.info("-------- User: " + subject.getPrincipal().toString() + " logged in ------------");
        return super.onLoginSuccess(token, subject, request, response);
    }
}
