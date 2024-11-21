package br.com.wsp.cooperativa.service.impl;

import br.com.wsp.cooperativa.dto.PautaRequest;
import br.com.wsp.cooperativa.dto.PautaResponse;
import br.com.wsp.cooperativa.model.Pauta;
import br.com.wsp.cooperativa.repository.PautaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PautaServiceTest {

    @InjectMocks
    PautaService service;
    @Mock
    PautaRepository repository;
    @Mock
    PautaRequest pautaRequest;
    @Mock
    Pauta pauta;

    @Test
    @DisplayName("TESTE CRIAR PAUTA DEVE RETORNAR PAUTA CRIADA")
    void test_criarPauta__deveRetornarPautaCriada() {

        doReturn(pauta).when(repository).save(any(Pauta.class));

        PautaResponse result = service.save(pautaRequest);

        assertNotNull(result);

        verify(repository, times(1)).save(any(Pauta.class));
    }

    @Test
    @DisplayName("TESTE BUSCANDO TODAS AS PAUTAS DEVE RETORNAR LISTA DE PAUTAS")
    void test_buscandoPautas__deveRetornarTodasAsPautas() {

        doReturn(List.of(pauta)).when(repository).findAll();

        List<Pauta> result = service.findALl();

        assertNotNull(result);

        verify(repository, times(1)).findAll();
    }
}
