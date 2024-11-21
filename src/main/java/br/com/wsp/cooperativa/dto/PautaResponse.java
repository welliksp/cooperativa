package br.com.wsp.cooperativa.dto;

import java.sql.Timestamp;

public record PautaResponse(Long id, String titulo, String descricao, Timestamp createdAt) {
}