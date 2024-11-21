package br.com.wsp.cooperativa.dto;

import br.com.wsp.cooperativa.model.enums.VoteEnum;

import java.sql.Timestamp;

public record VotacaoResponse(Long id, String titulo, String descricao, String associadoId, VoteEnum vote,
                              Timestamp createdAt) {

}