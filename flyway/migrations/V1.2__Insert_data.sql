INSERT into role values (1,'ROLE_ADMIN');
INSERT into role values (2,'ROLE_USER');
INSERT into role values (3,'ROLE_TRIBUTE');

INSERT INTO price values (1,'salary', 1000);
INSERT INTO price values (2,'Ежедневный приз', 1000);
INSERT INTO price values (3,'Начальный баланс', 1000);

insert into status values (1, 'Наблюдатель', 1);
insert into status values (2, 'Трибут', 1);
insert into status values (3, 'Распорядитель', 1);

INSERT INTO weapon VALUES (1,'Нож','Режущее',50,10,null);
INSERT INTO weapon VALUES (2,'Трезубец','Режущее',50,40,null);
INSERT INTO weapon VALUES (3,'Лук','Другое',70,200,null);
INSERT INTO weapon VALUES (4,'Копьё','Колющее',80,70,null);
INSERT INTO weapon VALUES (5,'Проволока','Другое',30,5,null);
INSERT INTO weapon VALUES (6,'Леска','Другое',20,5,null);

INSERT INTO skill VALUES (0,'Управление людьми','','Психологические',null);
INSERT INTO skill VALUES (1,'Знание боевых искусств','','Другие',null);
INSERT INTO skill VALUES (2,'Обращение с оружием','','Боевые',null);
INSERT INTO skill VALUES (3,'Знание электричества','','Боевые',null);
INSERT INTO skill VALUES (4,'Рыболовство','','Информационные',null);
INSERT INTO skill VALUES (5,'Знание энергетики','','Другие',null);
INSERT INTO skill VALUES (6,'Знание механики','','Информационные',null);
INSERT INTO skill VALUES (7,'Обращение с древесиной','','Информационные',null);
INSERT INTO skill VALUES (8,'Вязание узлов','','Информационные',null);
INSERT INTO skill VALUES (9,'Сила и выносливость','','Спортивные',null);
INSERT INTO skill VALUES (10,'Охота','','Информационные',null);
INSERT INTO skill VALUES (11,'Знание о культурных растениях','','Информационные',null);
INSERT INTO skill VALUES (12,'Разжигание костра','','Информационные',null);

INSERT INTO skill VALUES (13,'Владение ножом','','Боевые',1);
INSERT INTO skill VALUES (14,'Владение копьём','','Боевые',4);
INSERT INTO skill VALUES (15,'Владение луком и стрелами','','Боевые',3);
INSERT INTO skill VALUES (16,'Владение трезубцем','','Боевые',2);
INSERT INTO skill VALUES (17,'Маскировка','','Другие',null);
INSERT INTO skill VALUES (18,'Плавание','','Другие',null);
INSERT INTO skill VALUES (19,'Владение булавой','','Другие',null);
INSERT INTO skill VALUES (20,'Рукопашный бой','','Другие',null);

INSERT INTO district VALUES (0,'Капитолий','Управление',0);
INSERT INTO district VALUES (1,'Дистрикт 1','Роскошь',1);
INSERT INTO district VALUES (2,'Дистрикт 2','Оружие',2);
INSERT INTO district VALUES (3,'Дистрикт 3','Электроника',3);
INSERT INTO district VALUES (4,'Дистрикт 4','Рыболовство',4);
INSERT INTO district VALUES (5,'Дистрикт 5','Энергетика',5);
INSERT INTO district VALUES (6,'Дистрикт 6','Транспорт',6);
INSERT INTO district VALUES (7,'Дистрикт 7','Древесина',7);
INSERT INTO district VALUES (8,'Дистрикт 8','Текстиль',8);
INSERT INTO district VALUES (9,'Дистрикт 9','Сельское хозяйство',9);
INSERT INTO district VALUES (10,'Дистрикт 10','Животноводство',10);
INSERT INTO district VALUES (11,'Дистрикт 11','Сельское хозяйство',11);
INSERT INTO district VALUES (12,'Дистрикт 12','Добыча угля',12);

INSERT INTO location VALUES (1,'Пустыня',NULL);
INSERT INTO location VALUES (2,'Тропический лес',NULL);
INSERT INTO location VALUES (3,'Холмистая',NULL);
INSERT INTO location VALUES (4,'Водная',NULL);
INSERT INTO location VALUES (5,'Равнина',NULL);
INSERT INTO location VALUES (6,'Горы',NULL);
INSERT INTO location VALUES (7,'Лес',NULL);

INSERT INTO hook VALUES (0,'Осы-убийцы',1,30,10);
INSERT INTO hook VALUES (1,'Водопад',5,50,10);
INSERT INTO hook VALUES (2,'Вулкан',1,50,10);
INSERT INTO hook VALUES (3,'Кровавый дождь',2,15,10);
INSERT INTO hook VALUES (4,'Капкан',4,40,10);
INSERT INTO hook VALUES (5,'Дикие обезьяны',4,40,10);

INSERT INTO shop VALUES (1,'Водяная трубка',1000,'Инструменты','Напиток','Трубка для добычи воды из дерева',null,40);
INSERT INTO shop VALUES (2,'Булочки',2000,'Еда','Еда','Корзинка булочек',null,40);
INSERT INTO shop VALUES (3,'Мазь от укусов',7000,'Лекарства','Лекарство','Небольшая балочка мази, спасает от укусов ос-убийц',null,40);
INSERT INTO shop VALUES (4,'Бутылка воды',5000,'Напиток','Напиток','Маленькая бутылка воды',null,40);
INSERT INTO shop VALUES (5,'Мазь для ожогов',7000,'Лекарства','Лекарство','Небольшая балочка мази, спасает от ожогов',null,40);
INSERT INTO shop VALUES (6,'Нож',1000,'Инструменты','Еда','Раскладной ножик',null,40);
INSERT INTO shop VALUES (7,'Сетка',1000,'Другое','Еда','Сетка для ловли рыбы',null,40);
INSERT INTO shop VALUES (8,'Суп',2000,'Еда','Еда','Маленькая чашка с супом',null,40);
INSERT INTO shop VALUES (9,'Стрелы',5000,'Инструменты','Еда','6 стрел',null,40);
INSERT INTO shop VALUES (10,'Фляга',3000,'Другое','Напиток','Небольшая фляга для воды',null,40);
INSERT INTO shop VALUES (11,'Йод',3000,'Лекарства','Лекарство','Баночка с йодом',null,40);

INSERT INTO training VALUES (1,'Занятие с мечом',10,10,10,'Тренировка с мечами в зале',100,0);
INSERT INTO training VALUES (2,'Занятие с луком',15,10,8,'Стрельба из лука в лесу',100,0);
INSERT INTO training VALUES (3,'Занятие с булавой',19,10,1,'Тренировка с булавой на площадке',100,1);
INSERT INTO training VALUES (4,'Охота на животных',10,10,3,'Охота в лесу на мелких животных',100,1);
INSERT INTO training VALUES (5,'Сила и выносливость',9,10,3,'Бег в поле',100,2);
INSERT INTO training VALUES (6,'Теория. Культурные растения',11,10,6,'Лекция о культурных растениях',100,2);
INSERT INTO training VALUES (7,'Маскировка',17,10,9,'Обучение маскировке',100,3);
INSERT INTO training VALUES (8,'Занятие механикой',6,10,5,'Теория. Механика',100,3);
INSERT INTO training VALUES (9,'Занятие боевыми искусствами',1,10,3,'Боевые искусства в зале',100,4);
INSERT INTO training VALUES (10,'Разжигание костра',12,10,10,'Обучение разжиганию костра',100,4);
INSERT INTO training VALUES (11,'Занятие с  копьём',10,10,3,'Занятие с копьём',100,5);
INSERT INTO training VALUES (12,'Плавание',18,10,8,'Плавание в бассейне',100,5);
INSERT INTO training VALUES (13,'Рыболовство',4,10,3,'Ловля рыбы в озере',100,6);
INSERT INTO training VALUES (14,'Занятие энергетикой',5,10,2,'Теория. Энергетика',100,6);

INSERT INTO price VALUES (4,'Ежедневный приз',1000);
INSERT INTO price VALUES (5,'Начальный баланс',2000);

INSERT INTO products_and_location VALUES (1,1,1);
INSERT INTO products_and_location VALUES (2,1,2);
INSERT INTO products_and_location VALUES (3,1,3);
INSERT INTO products_and_location VALUES (4,1,4);
INSERT INTO products_and_location VALUES (5,1,5);
INSERT INTO products_and_location VALUES (6,1,6);
INSERT INTO products_and_location VALUES (7,1,7);
INSERT INTO products_and_location VALUES (8,2,1);
INSERT INTO products_and_location VALUES (9,2,2);
INSERT INTO products_and_location VALUES (10,3,1);
INSERT INTO products_and_location VALUES (11,3,2);
INSERT INTO products_and_location VALUES (12,3,3);
INSERT INTO products_and_location VALUES (13,3,4);
INSERT INTO products_and_location VALUES (14,3,5);
INSERT INTO products_and_location VALUES (15,3,6);
INSERT INTO products_and_location VALUES (16,3,7);
INSERT INTO products_and_location VALUES (17,4,1);
INSERT INTO products_and_location VALUES (18,4,2);
INSERT INTO products_and_location VALUES (19,4,6);
INSERT INTO products_and_location VALUES (20,5,1);
INSERT INTO products_and_location VALUES (21,5,2);
INSERT INTO products_and_location VALUES (22,5,5);
INSERT INTO products_and_location VALUES (23,5,6);
INSERT INTO products_and_location VALUES (24,5,7);
INSERT INTO products_and_location VALUES (25,6,2);
INSERT INTO products_and_location VALUES (26,6,3);
INSERT INTO products_and_location VALUES (27,6,4);
INSERT INTO products_and_location VALUES (28,6,7);
INSERT INTO products_and_location VALUES (29,7,1);
INSERT INTO products_and_location VALUES (30,7,2);
INSERT INTO products_and_location VALUES (31,7,3);
INSERT INTO products_and_location VALUES (32,7,4);
INSERT INTO products_and_location VALUES (33,7,5);
INSERT INTO products_and_location VALUES (34,7,6);
INSERT INTO products_and_location VALUES (35,7,7);
INSERT INTO products_and_location VALUES (36,8,1);
INSERT INTO products_and_location VALUES (37,8,2);
INSERT INTO products_and_location VALUES (38,8,5);
INSERT INTO products_and_location VALUES (39,8,6);
INSERT INTO products_and_location VALUES (40,8,7);
INSERT INTO products_and_location VALUES (41,9,2);
INSERT INTO products_and_location VALUES (42,9,3);
INSERT INTO products_and_location VALUES (43,9,4);
INSERT INTO products_and_location VALUES (44,10,2);
INSERT INTO products_and_location VALUES (45,10,4);
INSERT INTO products_and_location VALUES (46,10,5);
INSERT INTO products_and_location VALUES (47,10,6);
INSERT INTO products_and_location VALUES (48,10,3);
INSERT INTO products_and_location VALUES (49,11,2);
INSERT INTO products_and_location VALUES (50,11,7);




