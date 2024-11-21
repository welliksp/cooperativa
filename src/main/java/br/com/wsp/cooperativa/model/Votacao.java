package br.com.wsp.cooperativa.model;

import br.com.wsp.cooperativa.model.enums.VoteEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "votacao")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Votacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Sessao sessao;


    @Column(name = "associado_id")
    private String associadoId;

    @Enumerated(EnumType.STRING)
    private VoteEnum vote;

    @Column(name = "created_at")
    private Timestamp createdAt;
}