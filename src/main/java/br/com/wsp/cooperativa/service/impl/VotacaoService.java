package br.com.wsp.cooperativa.service.impl;

import br.com.wsp.cooperativa.dto.VotacaoRequest;
import br.com.wsp.cooperativa.dto.VotacaoResponse;
import br.com.wsp.cooperativa.exception.NotFoundException;
import br.com.wsp.cooperativa.exception.TimeExpiredException;
import br.com.wsp.cooperativa.exception.VoteFoundException;
import br.com.wsp.cooperativa.model.Sessao;
import br.com.wsp.cooperativa.model.Votacao;
import br.com.wsp.cooperativa.model.enums.VoteEnum;
import br.com.wsp.cooperativa.repository.SessionRepository;
import br.com.wsp.cooperativa.repository.VotacaoRepository;
import br.com.wsp.cooperativa.service.IVotacaoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class VotacaoService implements IVotacaoService {

    private final VotacaoRepository repository;
    private final SessionRepository sessionRepository;

    @Override
    public VotacaoResponse vote(VotacaoRequest votacaoRequest) {

        log.info("BUSCANDO SE EXISTE VOTACAO COM A SESSAO: " + votacaoRequest.sessaoId() + " E ASSOCIADO:" + votacaoRequest.associadoId());

        repository.existsBySessionIdAndAssociatedId(votacaoRequest.sessaoId(), votacaoRequest.associadoId()).ifPresent(v -> {
            throw new VoteFoundException("JÁ EXISTE UMA VOTACAO");
        });

        log.info("BUSCANDO SESSAO PELO ID: " + votacaoRequest.sessaoId());
        Sessao session = sessionRepository.findById(votacaoRequest.sessaoId()).orElseThrow(() -> new NotFoundException("SESSAO " + votacaoRequest.sessaoId() + " NAO ENCONTRADA"));
        log.info("SESSAO: " + session.toString());

        validateSession(session);

        Votacao newVote = Votacao.builder()
                .sessao(session)
                .associadoId(votacaoRequest.associadoId())
                .vote(VoteEnum.NAO.equals(votacaoRequest.vote()) ? VoteEnum.NAO : VoteEnum.SIM)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        log.info("SALVANDO VOTACAO");
        Votacao voteSaved = repository.save(newVote);
        log.info("VOTACAO SALVA: " + voteSaved.getId());

        return new VotacaoResponse(voteSaved.getId(), voteSaved.getSessao().getPauta().getTitulo(), voteSaved.getSessao().getPauta().getDescricao(), voteSaved.getAssociadoId(), voteSaved.getVote(), voteSaved.getCreatedAt());
    }

    private void validateSession(Sessao session) {

        if (LocalDateTime.now().isAfter(session.getDateEnd().toLocalDateTime())) {

            throw new TimeExpiredException("TEMPO PARA VOTAR EXPIRADO");
        }
    }
}