DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100;

INSERT INTO users (name, email, phone, secret)
VALUES ('User', 'user@mail.ru', '+79219990011', '$2a$10$Opr43HKpcEv/Vk0Rlfx/9OfpxOvsKW9Yq2T8NpVcJBoS/a9iyEG7G'),
       ('Admin', 'admin@mail.ru', '+79219990022', '$2a$10$Opr43HKpcEv/Vk0Rlfx/9OfpxOvsKW9Yq2T8NpVcJBoS/a9iyEG7G'),
       ('User2', 'user2@mail.ru', '+79219990033', '$2a$10$Opr43HKpcEv/Vk0Rlfx/9OfpxOvsKW9Yq2T8NpVcJBoS/a9iyEG7G');