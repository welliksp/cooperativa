package br.com.wsp.cooperativa.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PautaRequest(@NotNull @NotEmpty String titulo, @NotNull @NotEmpty String descricao) {
}