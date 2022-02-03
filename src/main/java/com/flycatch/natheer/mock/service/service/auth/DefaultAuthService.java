package com.flycatch.natheer.mock.service.service.auth;

import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


/**
 * Detailed Auth service.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultAuthService implements AuthService {

    private final AuthenticationManager authenticationManager;

    @Override
    public void authenticateUser(String name, String password) {
        log.info("Authenticating user");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>())
        );
        log.debug("user authenticated");
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}

