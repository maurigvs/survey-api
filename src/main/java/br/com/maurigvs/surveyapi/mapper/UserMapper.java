package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.requests.UserRequest;
import br.com.maurigvs.surveyapi.model.User;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.function.Function;

@RequiredArgsConstructor
public class UserMapper implements Function<UserRequest, User> {

    private final LocalDateTime createdAt;

    @Override
    public User apply(UserRequest request) {
        return new User(null,
                request.name(),
                request.email(),
                request.name().toLowerCase().replace(" ", "."),
                request.password(),
                createdAt);
    }
}
