package com.flycatch.natheer.mock.service.bootstrap;

import com.flycatch.natheer.mock.service.models.User;
import com.flycatch.natheer.mock.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class UserBootstrap implements CommandLineRunner {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        if (StringUtils.hasText("username")) {
            repository.findByUsername("username").ifPresentOrElse((user) -> {
            }, () -> repository.save(new User()
                    .setUsername("username")
                    .setPassword(encoder.encode("password"))
            ));
        }

    }
}

