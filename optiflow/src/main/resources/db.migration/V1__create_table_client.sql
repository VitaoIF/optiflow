CREATE TABLE client(
    id serial PRIMARY KEY,
    name varchar(255) NOT NULL,
    phone varchar(100),
    cpf varchar(100) NOT NULL,
    address varchar(255),
    created_at timestamp,
    updated_at timestamp
);