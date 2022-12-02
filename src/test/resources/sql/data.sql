INSERT INTO roles(name)
VALUES ('admin'),
       ('user');

INSERT INTO principals(email, password)
VALUES ('admin@email.com', '$2a$10$mfqNWGgqtMJIFf6.r1fajuTYJN66vnxFOh87/Qt190ek4CEwG5Y1W'),
       ('user@email.com', '$2a$10$mfqNWGgqtMJIFf6.r1fajuTYJN66vnxFOh87/Qt190ek4CEwG5Y1W');

INSERT INTO principals_roles(principal_id, role_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO users(first_name, last_name, patronymic, principal_id)
VALUES ('John', 'Doe', null, 1),
       ('Bob', 'Newman', null, 2);

INSERT INTO advertisements(title, content, created_at, author_id)
VALUES ('Some advertisement', 'Hello, world!', (SELECT EXTRACT (EPOCH from CURRENT_TIMESTAMP()) * 1000), 1);