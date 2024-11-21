package br.com.wsp.cooperativa.repository;

import br.com.wsp.cooperativa.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RulingRepository extends JpaRepository<Pauta, Long> {
}