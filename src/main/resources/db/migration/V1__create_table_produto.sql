CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE table produto
(
    id            UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome          VARCHAR(100) NOT NULL,
    descricao     VARCHAR(250) NOT NULL,
    precoUnitario money        NOT NULL
);