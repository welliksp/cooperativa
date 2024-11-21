package br.com.wsp.cooperativa.repository;

import br.com.wsp.cooperativa.model.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VotacaoRepository extends JpaRepository<Votacao, Long> {


    @Query(
            "SELECT v FROM Votacao  v " +
                    "WHERE v.sessao.id= :sessaoId " +
                    "AND v.associadoId= :associadoId"
    )
    Optional<Votacao> existsBySessionIdAndAssociatedId(@Param("sessaoId") Long sessaoId, @Param("associadoId") String associadoId);
}