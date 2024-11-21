package br.com.wsp.cooperativa.service.impl;

import br.com.wsp.cooperativa.dto.SessaoResponse;
import br.com.wsp.cooperativa.exception.NotFoundException;
import br.com.wsp.cooperativa.model.Sessao;
import br.com.wsp.cooperativa.repository.RulingRepository;
import br.com.wsp.cooperativa.repository.SessionRepository;
import br.com.wsp.cooperativa.service.ISessaoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class SessaoService implements ISessaoService {

    private final SessionRepository sessionRepository;
    private final RulingRepository rulingRepository;


    @Override
    public SessaoResponse abrirSessao(Long pautaId, Long duration) {

        log.info("BUSCANDO PAUTA PELO ID: " + pautaId);
        var ruling = rulingRepository.findById(pautaId).orElseThrow(() -> new NotFoundException("Ruling not found with id: " + pautaId));
        log.info("PAUTA: " + ruling.toString());

        Sessao session = Sessao.builder()
                .pauta(ruling)
                .dateStart(Timestamp.valueOf(LocalDateTime.now()))
                .dateEnd(Timestamp.valueOf(LocalDateTime.now().plusMinutes(duration)))
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        log.info("SALVANDO SESSAO");
        Sessao saved = sessionRepository.save(session);

        log.info("SESSION SAVED: " + session.toString());

        return new SessaoResponse(saved.getId(), saved.getPauta().getId(), saved.getDateStart(), saved.getDateEnd(), saved.getCreatedAt());
    }
}