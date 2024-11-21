package br.com.wsp.cooperativa.service;

import br.com.wsp.cooperativa.dto.PautaRequest;
import br.com.wsp.cooperativa.dto.PautaResponse;
import br.com.wsp.cooperativa.model.Pauta;

import java.util.List;

public interface IPautaService {

    PautaResponse save(PautaRequest pautaRequest);

    List<Pauta> findAll();
}
