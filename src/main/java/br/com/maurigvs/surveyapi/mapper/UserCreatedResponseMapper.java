package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.responses.UserCreatedResponse;
import br.com.maurigvs.surveyapi.model.User;

import java.util.function.Function;

public class UserCreatedResponseMapper implements Function<User, UserCreatedResponse> {

    @Override
    public UserCreatedResponse apply(User user) {
        var createdAt = new LocalDateTimeMapper().apply(user.getCreatedAt());

        return new UserCreatedResponse(user.getLogin(), createdAt);
    }
}
