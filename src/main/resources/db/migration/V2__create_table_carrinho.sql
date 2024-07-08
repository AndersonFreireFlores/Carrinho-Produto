CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE table carrinho
(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    preco_total money NOT NULL
)