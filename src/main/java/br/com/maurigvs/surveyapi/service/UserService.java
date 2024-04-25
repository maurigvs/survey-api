package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.User;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> create(Mono<User> userMono);

    // read
    // update
    // delete
}
