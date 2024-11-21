package br.com.wsp.cooperativa.service.impl;

import br.com.wsp.cooperativa.dto.SessaoResponse;
import br.com.wsp.cooperativa.exception.NotFoundException;
import br.com.wsp.cooperativa.model.Sessao;
import br.com.wsp.cooperativa.repository.PautaRepository;
import br.com.wsp.cooperativa.repository.SessaoRepository;
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

    private final SessaoRepository sessaoRepository;
    private final PautaRepository pautaRepository;


    @Override
    public SessaoResponse abrirSessao(Long pautaId, Long duration) {

        log.info("BUSCANDO PAUTA PELO ID: " + pautaId);
        var ruling = pautaRepository.findById(pautaId).orElseThrow(() -> new NotFoundException("Ruling not found with id: " + pautaId));
        log.info("PAUTA: " + ruling.toString());

        Sessao session = Sessao.builder()
                .pauta(ruling)
                .dateStart(Timestamp.valueOf(LocalDateTime.now()))
                .dateEnd(Timestamp.valueOf(LocalDateTime.now().plusMinutes(duration)))
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        log.info("SALVANDO SESSAO");
        Sessao saved = sessaoRepository.save(session);
        log.info("SESSION SAVED: " + session.toString());

        return new SessaoResponse(saved.getId(), saved.getPauta().getId(), saved.getDateStart(), saved.getDateEnd(), saved.getCreatedAt());
    }
}