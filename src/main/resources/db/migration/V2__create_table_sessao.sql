CREATE TABLE sessao(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pauta_id BIGINT NOT NULL,
    date_start TIMESTAMP NULL,
    date_end TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_pauta FOREIGN KEY (pauta_id) REFERENCES pauta(id) ON DELETE CASCADE
    );