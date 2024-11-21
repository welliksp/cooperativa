package br.com.wsp.cooperativa.service.impl;

import br.com.wsp.cooperativa.dto.SessaoResponse;
import br.com.wsp.cooperativa.exception.NotFoundException;
import br.com.wsp.cooperativa.model.Pauta;
import br.com.wsp.cooperativa.model.Sessao;
import br.com.wsp.cooperativa.repository.PautaRepository;
import br.com.wsp.cooperativa.repository.SessaoRepository;
import org.junit.jupiter.api.BeforeEach;
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
class SessaoServiceTest {

    @InjectMocks
    SessaoService service;
    @Mock
    PautaRepository pautaRepository;
    @Mock
    SessaoRepository sessaoRepository;
    @Mock
    Pauta pauta;

    Sessao sessao;

    @BeforeEach
    void setUp() {

        sessao = new Sessao(1L, new Pauta(), Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));

    }

    @Test
    @DisplayName("TESTE ABRIR SESSAO DEVE RETORNAR SESSAO CRIADA")
    void test_abrirSessao__deveRetornarSessaoCriada() {

        doReturn(Optional.of(pauta)).when(pautaRepository).findById(any());
        doReturn(sessao).when(sessaoRepository).save(any());

        SessaoResponse result = service.abrirSessao(1L, 20L);

        assertNotNull(result);

        verify(pautaRepository, times(1)).findById(any());
        verify(sessaoRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("TESTE ABRIR SESSAO DEVE RETORNAR NOT FOUND EXCEPTION")
    void test_abrirSessao__deveRetornarNotFoundException() {

        assertThrows(NotFoundException.class, () -> service.abrirSessao(1L, 20L));

        verify(pautaRepository, times(1)).findById(any(Long.class));
    }

}