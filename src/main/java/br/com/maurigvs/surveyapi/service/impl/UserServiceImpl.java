package br.com.maurigvs.surveyapi.service.impl;

import br.com.maurigvs.surveyapi.model.User;
import br.com.maurigvs.surveyapi.repository.UserRepository;
import br.com.maurigvs.surveyapi.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<User> create(Mono<User> userMono) {
        return userMono
                .map(repository::save)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
