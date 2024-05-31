package br.com.maurigvs.surveyapi.service.impl;

import br.com.maurigvs.surveyapi.model.User;
import br.com.maurigvs.surveyapi.repository.UserRepository;
import br.com.maurigvs.surveyapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public Mono<User> create(Mono<User> userMono) {
        return userMono
                .map(repository::save)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
