CREATE TABLE IF NOT EXISTS Company(
    id BIGSERIAL PRIMARY KEY,
    cnpj TEXT NOT NULL,
    balance DECIMAL NOT NULL,
    system_rate DECIMAL NOT NULL
);

CREATE TABLE IF NOT EXISTS Client(
    id BIGSERIAL PRIMARY KEY,
    cpf TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Transactions(
    id BIGSERIAL PRIMARY KEY,
    type_transaction TEXT NOT NULL,
    company_id BIGINT REFERENCES Company(id),
    client_id BIGINT REFERENCES Client(id),
    value_transaction DECIMAL NOT NULL,
    realized_at TIMESTAMP NOT NULL
);