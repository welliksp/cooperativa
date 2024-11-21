package br.com.wsp.cooperativa.service;

import br.com.wsp.cooperativa.dto.VotacaoRequest;
import br.com.wsp.cooperativa.dto.VotacaoResponse;
import br.com.wsp.cooperativa.dto.VotacaoResultado;

public interface IVotacaoService {

    VotacaoResponse vote(VotacaoRequest votacaoRequest);

    VotacaoResultado resultadoVotacao(Long sessaoId);
}
