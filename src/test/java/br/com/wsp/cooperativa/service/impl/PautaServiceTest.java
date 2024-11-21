package br.com.wsp.cooperativa.service.impl;

import br.com.wsp.cooperativa.dto.PautaRequest;
import br.com.wsp.cooperativa.dto.PautaResponse;
import br.com.wsp.cooperativa.model.Pauta;
import br.com.wsp.cooperativa.repository.RulingRepository;
import br.com.wsp.cooperativa.service.IPautaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PautaServiceTest {

    IPautaService service;

    @Mock
    RulingRepository repository;

    @Mock
    PautaRequest pautaRequest;

    @Mock
    Pauta pauta;

    @BeforeEach
    void setUp() {

        service = new PautaService(repository);

    }

    @Test
    @DisplayName("Test Create New Ruling Should Return Sucess")
    void test_createNewRuling__shouldReturnObjectCreatedS() {

        doReturn(pauta).when(repository).save(any(Pauta.class));

        PautaResponse result = service.save(pautaRequest);

        assertNotNull(result);

        verify(repository, times(1)).save(any(Pauta.class));
    }

    @Test
    @DisplayName("Test Find All Should Return List")
    void test_findAllRulings__shouldReturn() {

        doReturn(List.of(pauta)).when(repository).findAll();

        List<Pauta> result = service.findALl();

        assertNotNull(result);

        verify(repository, times(1)).findAll();
    }
}
