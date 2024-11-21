package br.com.wsp.cooperativa.service;

import br.com.wsp.cooperativa.dto.SessaoResponse;

public interface ISessaoService {

    SessaoResponse abrirSessao(Long pautaId, Long duracao);

    void fecharSessao(Long sessaoId);
}
