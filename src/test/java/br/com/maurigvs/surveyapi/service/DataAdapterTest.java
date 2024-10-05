package br.com.maurigvs.surveyapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static reactor.test.StepVerifier.create;

abstract class DataAdapterTest<M, R extends JpaRepository<M, Long>, S extends DataAdapter<M, R>> {

    protected S service;
    protected R repository;
    protected M entity;

    @Test
    void should_return_entity_when_save() {
        when(repository.save(entity)).thenReturn(entity);

        create(service.create(entity))
                .expectNext(entity)
                .verifyComplete();

        verify(repository).save(entity);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_return_entity_when_find_by_id() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(entity));

        StepVerifier.create(service.findById(id))
                .expectNext(entity)
                .verifyComplete();

        verify(repository).findById(id);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_throw_exception_when_find_by_id_not_found() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        StepVerifier.create(service.findById(id))
                .expectErrorSatisfies(throwable -> {
                    assertInstanceOf(NoSuchElementException.class, throwable);
                    assertEquals("No value present", throwable.getMessage());
                })
                .verify();

        verify(repository).findById(id);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_return_entity_stream_when_find_all() {
        when(repository.findAll()).thenReturn(List.of(entity));

        StepVerifier.create(service.findAll())
                .expectNext(entity)
                .verifyComplete();

        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_return_void_when_delete_by_id() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        doNothing().when(repository).delete(entity);

        StepVerifier.create(service.deleteById(id))
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(repository).delete(entity);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void should_return_void_when_delete() {
        doNothing().when(repository).delete(entity);

        StepVerifier.create(service.delete(entity))
                .verifyComplete();

        verify(repository).delete(entity);
        verifyNoMoreInteractions(repository);
    }
}
