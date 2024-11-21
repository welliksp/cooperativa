package br.com.wsp.cooperativa.dto;

public class VotacaoResultado {

    private Long sessaoId;
    private Long totalVotos;
    private Long votosAceitos;
    private Long votosNegados;

    public VotacaoResultado(Long sessaoId, Long totalVotos, Long votosAceitos, Long votosNegados) {
        this.sessaoId = sessaoId;
        this.totalVotos = totalVotos;
        this.votosAceitos = votosAceitos;
        this.votosNegados = votosNegados;
    }

    public Long getSessaoId() {
        return sessaoId;
    }

    public Long getTotalVotos() {
        return totalVotos;
    }

    public Long getVotosAceitos() {
        return votosAceitos;
    }

    public Long getVotosNegados() {
        return votosNegados;
    }

    public String getResult() {
        if (votosAceitos > votosNegados) {
            return "Aceitos";
        } else if (votosAceitos < votosNegados) {
            return "Negados";
        } else {
            return "Empate";
        }
    }
}