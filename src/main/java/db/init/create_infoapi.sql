
CREATE TABLE IF NOT EXISTS informacao_api (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    url VARCHAR(255) NOT NULL,
    ambiente VARCHAR(255) NOT NULL
);