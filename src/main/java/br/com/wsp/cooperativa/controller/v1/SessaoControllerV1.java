package br.com.wsp.cooperativa.controller.v1;

import br.com.wsp.cooperativa.dto.SessaoResponse;
import br.com.wsp.cooperativa.service.ISessaoService;
import br.com.wsp.cooperativa.service.impl.SessaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/sessao")
public class SessaoControllerV1 {

    private final ISessaoService service;

    public SessaoControllerV1(SessaoService service) {
        this.service = service;
    }

    @PutMapping
    public ResponseEntity<SessaoResponse> abrirSessao(@RequestParam Long pautaId, @RequestParam(defaultValue = "1") Long duracao) {

        SessaoResponse save = service.abrirSessao(pautaId, duracao);

        return ResponseEntity.ok().body(save);
    }

    @PutMapping("/fechar-sessao")
    public ResponseEntity<SessaoResponse> fecharSessao(@RequestParam Long sessaoId) {

        service.fecharSessao(sessaoId);

        return ResponseEntity.ok().build();
    }

}
