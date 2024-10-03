package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.exception.BusinessException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static reactor.test.StepVerifier.create;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
abstract class DatabaseServiceTest<M, R extends JpaRepository<M, Long>, S extends DatabaseService<M, R>> {

    protected S service;
    protected R repository;
    protected M entity;

    @Test
    void should_return_entity_when_save() {
        given(repository.save(any())).willReturn(entity);

        create(service.save(entity))
                .expectNext(entity)
                .verifyComplete();

        then(repository).should(times(1)).save(entity);
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_return_entity_when_find_by_id() {
        var id = 1L;
        given(repository.findById(anyLong())).willReturn(Optional.of(entity));

        create(service.findById(id))
                .expectNext(entity)
                .verifyComplete();

        then(repository).should(times(1)).findById(id);
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_throw_exception_when_find_by_id_not_found() {
        var id = 1L;
        var message = entity.getClass().getSimpleName() + " not found by Id " + id;
        given(repository.findById(id)).willReturn(Optional.empty());

        create(service.findById(id))
                .expectErrorSatisfies(throwable -> {
                    var exception = assertInstanceOf(BusinessException.class, throwable);
                    assertEquals(message, exception.getMessage());
                })
                .verify();

        then(repository).should(times(1)).findById(id);
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_return_entity_stream_when_find_all() {
        given(repository.findAll()).willReturn(List.of(entity));

        create(service.findAll())
                .expectNext(entity)
                .verifyComplete();

        then(repository).should(times(1)).findAll();
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_return_void_when_delete_by_id() {
        doNothing().when(repository).delete(entity);

        create(service.delete(entity))
                .verifyComplete();

        then(repository).should(times(1)).delete(entity);
        then(repository).shouldHaveNoMoreInteractions();
    }
}
