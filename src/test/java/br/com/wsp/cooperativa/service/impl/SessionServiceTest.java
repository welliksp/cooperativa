package br.com.wsp.cooperativa.service.impl;

import br.com.wsp.cooperativa.dto.SessaoResponse;
import br.com.wsp.cooperativa.exception.NotFoundException;
import br.com.wsp.cooperativa.model.Pauta;
import br.com.wsp.cooperativa.model.Sessao;
import br.com.wsp.cooperativa.repository.RulingRepository;
import br.com.wsp.cooperativa.repository.SessionRepository;
import br.com.wsp.cooperativa.service.ISessaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    ISessaoService service;

    @Mock
    RulingRepository rulingRepository;

    @Mock
    SessionRepository sessionRepository;

    @Mock
    Pauta pauta;

    @Mock
    Sessao sessao;

    @BeforeEach
    void setUp() {

        service = new SessaoService(sessionRepository, rulingRepository);
    }

    @Test
    @DisplayName("TEST OPEN SESSION SHOULD RETURN SESSION CREATED")
    void test_openSession__shouldReturnSessionCreated() {

        doReturn(Optional.of(pauta)).when(rulingRepository).findById(any());
        doReturn(sessao).when(sessionRepository).save(any());

        SessaoResponse result = service.abrirSessao(1L, 20L);

        assertNotNull(result);

        verify(rulingRepository, times(1)).findById(any());
        verify(sessionRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("TEST OPEN SESSION SHOULD RETURN NOT FOUND EXCEPTION")
    void test_openSession__shouldReturnNotFoundException() {

        assertThrows(NotFoundException.class, () -> service.abrirSessao(1L, 20L));

        verify(rulingRepository, times(1)).findById(any(Long.class));
    }

}