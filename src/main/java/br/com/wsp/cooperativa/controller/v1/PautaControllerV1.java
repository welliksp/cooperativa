package br.com.wsp.cooperativa.controller.v1;

import br.com.wsp.cooperativa.dto.PautaRequest;
import br.com.wsp.cooperativa.dto.PautaResponse;
import br.com.wsp.cooperativa.service.IPautaService;
import br.com.wsp.cooperativa.service.impl.PautaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/pauta")
public class PautaControllerV1 {

    private final IPautaService service;

    public PautaControllerV1(PautaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PautaResponse> save(@RequestBody @Valid PautaRequest pautaRequest) {

        PautaResponse save = service.save(pautaRequest);

        return ResponseEntity.ok().body(save);
    }
}
