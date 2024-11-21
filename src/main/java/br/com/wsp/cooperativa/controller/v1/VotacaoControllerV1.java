package br.com.wsp.cooperativa.controller.v1;

import br.com.wsp.cooperativa.dto.VotacaoRequest;
import br.com.wsp.cooperativa.dto.VotacaoResponse;
import br.com.wsp.cooperativa.service.IVotacaoService;
import br.com.wsp.cooperativa.service.impl.VotacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/votacao")
public class VotacaoControllerV1 {

    private final IVotacaoService service;

    public VotacaoControllerV1(VotacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VotacaoResponse> vote(@RequestBody @Valid VotacaoRequest votacaoRequest) {

        VotacaoResponse save = service.vote(votacaoRequest);

        return ResponseEntity.ok().body(save);
    }

}
