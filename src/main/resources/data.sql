DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100;

INSERT INTO users (name, email, phone, secret)
VALUES ('User', 'user@mail.ru', '+79219990011', 'password1'),
       ('Admin', 'admin@mail.ru', '+79219990022', 'password2'),
       ('User2', 'user2@mail.ru', '+79219990033', 'password3');