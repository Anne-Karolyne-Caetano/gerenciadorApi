CREATE TABLE categoria (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao VARCHAR(255)
);
CREATE TABLE item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255),
    quantidade INT NOT NULL,
    categoria_id BIGINT NOT NULL,
        CONSTRAINT fk_item_categoria
            FOREIGN KEY (categoria_id)
                REFERENCES categoria(id)
);
