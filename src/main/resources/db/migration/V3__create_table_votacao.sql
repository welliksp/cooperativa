    CREATE TABLE votacao(

        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        sessao_id BIGINT NOT NULL,
        associado_id VARCHAR(15) NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        vote ENUM('SIM', 'NAO') NOT NULL,

        CONSTRAINT fk_sessao FOREIGN KEY (sessao_id) REFERENCES sessao(id) ON DELETE CASCADE
        );
