package br.com.wsp.cooperativa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "pauta")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Pauta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String titulo;

    @NotNull
    @NotEmpty
    private String descricao;

    @Column(name = "created_at")
    private Timestamp createdAt;
}