package br.com.wsp.cooperativa.dto;

public record VotacaoRequest(Long sessaoId, String associadoId, String vote) {
}
