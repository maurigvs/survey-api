package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.exception.NotFoundException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
abstract class AbstractCrudServiceTest<M, R extends JpaRepository<M, Long>, S extends AbstractCrudService<M, R>> {

    protected S service;
    protected R repository;
    protected M entity;

    @Test
    void should_return_entity_when_create() {
        given(repository.save(any())).willReturn(entity);

        StepVerifier.create(service.create(entity))
                .expectNext(entity)
                .verifyComplete();

        then(repository).should(times(1)).save(entity);
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_return_entity_when_find_by_id() {
        var id = 1L;
        given(repository.findById(anyLong())).willReturn(Optional.of(entity));

        StepVerifier.create(service.findById(id))
                .expectNext(entity)
                .verifyComplete();

        then(repository).should(times(1)).findById(id);
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_throw_exception_when_find_by_id_not_found() {
        var id = 1L;
        var message = entity.getClass().getSimpleName() + " not found by Id " + id;
        given(repository.findById(anyLong())).willReturn(Optional.empty());

        StepVerifier.create(service.findById(id))
                .expectErrorSatisfies(throwable -> {
                    var exception = assertInstanceOf(NotFoundException.class, throwable);
                    assertEquals(message, exception.getMessage());
                })
                .verify();

        then(repository).should(times(1)).findById(id);
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_return_entity_stream_when_find_all() {
        given(repository.findAll()).willReturn(List.of(entity));

        StepVerifier.create(service.findAll())
                .expectNext(entity)
                .verifyComplete();

        then(repository).should(times(1)).findAll();
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void should_return_void_when_delete_by_id() {
        var id = 1L;
        given(repository.findById(anyLong())).willReturn(Optional.of(entity));

        StepVerifier.create(service.deleteById(id))
                .verifyComplete();

        then(repository).should(times(1)).findById(id);
        then(repository).should(times(1)).delete(entity);
        then(repository).shouldHaveNoMoreInteractions();
    }
}
