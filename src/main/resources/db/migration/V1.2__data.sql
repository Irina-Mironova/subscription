INSERT INTO users (username, email, phone) VALUES
   ('Иван Петров', 'ivan.petrov@mail.ru', '79111234567'),
   ('Анна Сидорова', 'anna.sidorova@mail.ru', '79217654321'),
   ('Алексей Волков', 'alex.volkov@mail.ru', '79031234567'),
   ('Елена Кузнецова', 'elena.k@mail.ru', '79057654321'),
   ('Дмитрий Смирнов', 'dmitry.smirnov@mail.ru', '79165554433');

INSERT INTO subscriptions (title, description) VALUES
   ('YouTube Premium', 'Без рекламы, музыка и эксклюзивный контент'),
   ('Netflix Standard', 'Просмотр в HD на 2 устройствах'),
   ('Spotify Family', 'До 6 аккаунтов по подписке'),
   ('Яндекс.Плюс', 'Музыка, кино и кешбэк в сервисах Яндекса'),
   ('Twitch Turbo', 'Без рекламы, кастомные эмодзи');

INSERT INTO users_subscriptions (user_id, subscription_id) VALUES
    (1, 1),
    (1, 3),
    (2, 2),
    (3, 4),
    (4, 5),
    (4, 1),
    (5, 3);