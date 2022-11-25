DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100;

INSERT INTO users (name, email, phone, secret)
VALUES ('User', 'user@mail.ru', '+79219990011', '$2a$10$K9Q7V4.Sa9YL2KD/slK0q.w9lSKF2lATK.F6HxMQTFKb1gbK33EUm'),
       ('Admin', 'admin@mail.ru', '+79219990022', '$2a$10$K9Q7V4.Sa9YL2KD/slK0q.w9lSKF2lATK.F6HxMQTFKb1gbK33EUm'),
       ('User2', 'user2@mail.ru', '+79219990033', '$2a$10$K9Q7V4.Sa9YL2KD/slK0q.w9lSKF2lATK.F6HxMQTFKb1gbK33EUm');