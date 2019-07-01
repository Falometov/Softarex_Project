INSERT INTO tag (id, name)
VALUES (1, 'New Year'),
       (2, 'Merry Christmas'),
       (3, 'Birthday');

INSERT INTO gift_certificate (id, name, description, price, date_of_creation, date_of_modification, duration_in_days)
VALUES (1, 'Happy New Year', 'Nice certificate', 23.5, '2018-11-11', '2019-11-11', 30),
       (2, 'Merry Christmas', 'Nice certificate', 50.0, '2018-12-24', '2019-02-24', 30),
       (3, 'Birthday', 'Nice certificate', 65.0, '2018-12-24', '2019-02-24', 30);

INSERT INTO gift_certificate_m2m_tag (gift_certificate_id, tag_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);
