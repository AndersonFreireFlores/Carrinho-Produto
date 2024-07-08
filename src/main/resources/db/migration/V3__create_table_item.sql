CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE table item
(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    quantidade int NOT NULL ,
    valor_total money not null,
    carrinho_id uuid,
    produto_id uuid,
    CONSTRAINT fk_carrinho FOREIGN KEY (carrinho_id) REFERENCES carrinho(id),
    CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES produto(id)
)