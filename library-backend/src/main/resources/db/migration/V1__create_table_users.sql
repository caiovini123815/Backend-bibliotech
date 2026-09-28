CREATE TABLE users (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name_full VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    cpf VARCHAR(15) NOT NULL UNIQUE,
    phone VARCHAR(15) NOT NULL,
    birth_date DATE NOT NULL,
    address VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_admin BOOLEAN NOT NULL DEFAULT FALSE,
    is_disable BOOLEAN NOT NULL DEFAULT TRUE,
    create_dt TIMESTAMP NOT NULL,
    update_dt TIMESTAMP NOT NULL
);
VALUES (
    'Admin Supremo',
    'adminsup@gmail.com',
    '100.090.377-00',
    '(85) 90003-8000',
    '2002-09-06',
    'Rua Manoel Satiro, 221 - Fortaleza',
    '$2a$10$UxNzYikYhFGTaTUoDVPEeudyHlQqJZimg.NX7MBc0cO4payoAEODO',
    true,
    false,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP

)