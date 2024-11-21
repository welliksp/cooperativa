package br.com.wsp.cooperativa.repository;

import br.com.wsp.cooperativa.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {
}
