package br.com.maurigvs.surveyapi.service.impl;

import br.com.maurigvs.surveyapi.exception.UserAlreadyExistsException;
import br.com.maurigvs.surveyapi.model.User;
import br.com.maurigvs.surveyapi.repository.UserRepository;
import br.com.maurigvs.surveyapi.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        createLogin(user);
        validateUser(user);
        return repository.save(user);
    }

    private void validateUser(User user) {
        if(repository.existsByEmail(user.getEmail()))
            throw new UserAlreadyExistsException();
    }

    private void createLogin(User user) {
        var login = user.getName().toLowerCase().replace(" ", ".");
        user.setLogin(login);
    }
}
