package com.flycatch.natheer.mock.service.filters;

import com.flycatch.natheer.mock.service.Constants;
import com.flycatch.natheer.mock.service.service.auth.AuthService;
import java.io.IOException;
import java.util.Base64;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


/**
 * AUTHFilter that filter requests to get token.
 */
@Slf4j
@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.debug("using basic authentication");
        String authToken = request.getHeader(Constants.AUTHORIZATION);
        log.debug("verifying basic auth token");
        if (authToken != null) {
            String[] token = extractNameAndPassword(authToken.substring(Constants.BASIC.length()));
            if (token.length == 2) {
                final String name = token[0];
                final String password = token[1];
                log.debug("Authenticating user {}", name);
                try {
                    authService.authenticateUser(name, password);
                } catch (BadCredentialsException e) {
                    log.error("failed to authenticate user");
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String[] extractNameAndPassword(String token) {
        byte[] decoded = Base64.getDecoder().decode(token);
        final String s = new String(decoded);
        return s.split(Constants.COLON);
    }
}

