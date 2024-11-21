package br.com.wsp.cooperativa.service.impl;

import br.com.wsp.cooperativa.dto.VotacaoRequest;
import br.com.wsp.cooperativa.dto.VotacaoResponse;
import br.com.wsp.cooperativa.exception.VoteFoundException;
import br.com.wsp.cooperativa.model.Sessao;
import br.com.wsp.cooperativa.model.Votacao;
import br.com.wsp.cooperativa.repository.SessionRepository;
import br.com.wsp.cooperativa.repository.VotacaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VoteServiceTest {

    @InjectMocks
    VotacaoService service;

    @Mock
    SessionRepository sessionRepository;

    @Mock
    VotacaoRepository repository;
    @Mock
    Votacao vote;
    @Mock
    VotacaoRequest votacaoRequest;

    @Mock
    Sessao session;

    @Test
    @DisplayName("TEST CREATE VOTATION SHOULD RETURN SUCESS")
    void test__createVotation__shouldReturnSucess() {

        doReturn(Optional.empty()).when(repository).existsBySessionIdAndAssociatedId(anyLong(), anyString());
        doReturn(Optional.of(session)).when(sessionRepository).findById(anyLong());
        doReturn(vote).when(repository).save(any());

        VotacaoResponse result = service.vote(votacaoRequest);

        assertNotNull(result);

        verify(sessionRepository, times(1)).findById(anyLong());
        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("TEST CREATE VOTATION SHOULD THROWS EXCEPTION")
    void test__createVotation__throwsException() {

        doThrow(VoteFoundException.class).when(repository).existsBySessionIdAndAssociatedId(anyLong(), anyString());

        assertThrows(VoteFoundException.class, () -> service.vote(votacaoRequest));
    }
}