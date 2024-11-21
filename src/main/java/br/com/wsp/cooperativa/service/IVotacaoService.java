package br.com.wsp.cooperativa.service;

import br.com.wsp.cooperativa.dto.VotacaoRequest;
import br.com.wsp.cooperativa.dto.VotacaoResponse;

public interface IVotacaoService {

    VotacaoResponse vote(VotacaoRequest votacaoRequest);
}
