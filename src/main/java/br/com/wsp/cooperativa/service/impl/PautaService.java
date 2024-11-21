package br.com.wsp.cooperativa.service.impl;

import br.com.wsp.cooperativa.dto.PautaRequest;
import br.com.wsp.cooperativa.dto.PautaResponse;
import br.com.wsp.cooperativa.model.Pauta;
import br.com.wsp.cooperativa.repository.PautaRepository;
import br.com.wsp.cooperativa.service.IPautaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PautaService implements IPautaService {

    private final PautaRepository repository;

    @Override
    public PautaResponse save(PautaRequest pautaRequest) {

        Pauta converted = Pauta.builder()
                .titulo(pautaRequest.titulo())
                .descricao(pautaRequest.descricao())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        log.info("SALVANDO PAUTA: " + converted.toString());
        Pauta saved = repository.save(converted);
        log.info("PAUTA SALVA : " + saved.toString());

        return new PautaResponse(saved.getId(), saved.getTitulo(), saved.getDescricao(), saved.getCreatedAt());
    }

    @Override
    public List<Pauta> findALl() {

        log.info("BUSCANDO TODAS AS PAUTAS");
        return repository.findAll();
    }
}