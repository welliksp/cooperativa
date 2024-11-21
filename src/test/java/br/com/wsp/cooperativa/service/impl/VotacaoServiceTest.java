package br.com.wsp.cooperativa.service.impl;

import br.com.wsp.cooperativa.dto.VotacaoRequest;
import br.com.wsp.cooperativa.dto.VotacaoResponse;
import br.com.wsp.cooperativa.dto.VotacaoResultado;
import br.com.wsp.cooperativa.exception.TimeExpiredException;
import br.com.wsp.cooperativa.exception.VoteFoundException;
import br.com.wsp.cooperativa.model.Pauta;
import br.com.wsp.cooperativa.model.Sessao;
import br.com.wsp.cooperativa.model.Votacao;
import br.com.wsp.cooperativa.repository.SessaoRepository;
import br.com.wsp.cooperativa.repository.VotacaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VotacaoServiceTest {

    @InjectMocks
    VotacaoService service;

    @Mock
    SessaoRepository sessaoRepository;

    @Mock
    VotacaoRepository repository;
    @Mock
    Votacao vote;
    @Mock
    VotacaoRequest votacaoRequest;
    @Mock
    Sessao sessao;
    @Mock
    Pauta pauta;
    @Mock
    VotacaoResultado votacaoResultado;


    @Test
    @DisplayName("TESTE CRIANDO VOTACAO DEVE RETORNAR SUCESSO")
    void test__createVotation__shouldReturnSucess() {

        doReturn(Optional.empty()).when(repository).existsBySessionIdAndAssociatedId(any(), any());
        doReturn(Timestamp.valueOf(LocalDateTime.now().plusHours(1))).when(sessao).getDateEnd();
        doReturn(Optional.of(sessao)).when(sessaoRepository).findById(anyLong());
        doReturn(vote).when(repository).save(any());
        doReturn(pauta).when(sessao).getPauta();
        doReturn(sessao).when(vote).getSessao();

        VotacaoResponse result = service.vote(votacaoRequest);

        assertNotNull(result);

        verify(repository, times(1)).existsBySessionIdAndAssociatedId(any(), any());
        verify(sessaoRepository, times(1)).findById(anyLong());
        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("TESTE CRIANDO VOTACAO DEVE RETORNAR EXCEPTION")
    void test__createVotation__throwsException() {

        doThrow(VoteFoundException.class).when(repository).existsBySessionIdAndAssociatedId(any(), any());

        assertThrows(VoteFoundException.class, () -> service.vote(votacaoRequest));
    }

    @Test
    @DisplayName("TESTE CRIANDO VOTACAO DEVE RETORNAR TIME EXPIRED EXCEPTION")
    void test__criarVotacao__deveRetornarTimeExpiredException() {

        doReturn(Optional.empty()).when(repository).existsBySessionIdAndAssociatedId(any(), any());
        doReturn(Timestamp.valueOf(LocalDateTime.now())).when(sessao).getDateEnd();
        doReturn(Optional.of(sessao)).when(sessaoRepository).findById(anyLong());

        assertThrows(TimeExpiredException.class, () -> service.vote(votacaoRequest));

    }

    @Test
    @DisplayName("TESTE RESULTADO VOTACAO DEVE RETORNAR RESULTADO TOTAL DA VOTACAO")
    void test__resultadoVotacao_shouldReturnSucess() {

        doReturn(votacaoResultado).when(repository).calculaResultadoVotacao(anyLong());

        VotacaoResultado result = service.resultadoVotacao(1L);

        assertNotNull(result);

        verify(repository, times(1)).calculaResultadoVotacao(anyLong());
    }
}