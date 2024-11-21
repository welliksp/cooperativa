package br.com.wsp.cooperativa.dto;

import java.sql.Timestamp;

public record SessaoResponse(Long id, Long pautaId, Timestamp dateStart, Timestamp dateEnd, Timestamp createdAt) {
}