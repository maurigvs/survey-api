package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.requests.UserRequest;
import br.com.maurigvs.surveyapi.model.User;

import java.time.LocalDateTime;
import java.util.function.Function;

public class UserMapper implements Function<UserRequest, User> {

    private final LocalDateTime createdAt;

    public UserMapper(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public User apply(UserRequest request) {
        return new User(null,
                request.name(),
                request.email(),
                request.password(),
                createdAt);
    }
}
