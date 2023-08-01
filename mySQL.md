### Task 6
Нарисовать диаграмму, в которой есть класс родительский класс, домашние
животные и вьючные животные, в составы которых в случае домашних
животных войдут классы: собаки, кошки, хомяки, а в класс вьючные животные
войдут: Лошади, верблюды и ослы).
![](https://github.com/Andrei4e/finalWork2/blob/main/screens/5.png?raw=true)

### Task 7
В подключенном MySQL репозитории создать базу данных “Друзья
человека”
```sql
CREATE DATABASE friendsMans;
```

### Task 8
Создать таблицы с иерархией из диаграммы в БД

```sql
USE friendsMans;
CREATE TABLE animals
(
	Id INT AUTO_INCREMENT PRIMARY KEY, 
	ClassName VARCHAR(20)
);

INSERT INTO animals (ClassName)
VALUES ('Домашние'), ('Вьючные');  


CREATE TABLE packAnimals
(
	Id INT AUTO_INCREMENT PRIMARY KEY,
    KindName VARCHAR (20),
    ClassId INT,
    FOREIGN KEY (ClassId) REFERENCES animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO packAnimals (KindName, ClassId)
VALUES ('Лошади', 1), ('Ослы', 1), ('Верблюды', 1); 
    
CREATE TABLE homeAnimals
(
	Id INT AUTO_INCREMENT PRIMARY KEY,
    KindName VARCHAR (20),
    ClassId INT,
    FOREIGN KEY (ClassId) REFERENCES animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO homeAnimals (KindName, ClassId)
VALUES ('Собаки', 2), ('Кошки', 2), ('Хомяки', 2); 

CREATE TABLE cats 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    KindId int,
    Foreign KEY (KindId) REFERENCES homeAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE dogs 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    KindId int,
    Foreign KEY (KindId) REFERENCES homeAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE hamster 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    KindId int,
    Foreign KEY (KindId) REFERENCES homeAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE horses 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    KindId int,
    Foreign KEY (KindId) REFERENCES packAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE camels 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    KindId int,
    Foreign KEY (KindId) REFERENCES packAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE donkeys 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Birthday DATE,
    Commands VARCHAR(50),
    KindId int,
    Foreign KEY (KindId) REFERENCES packAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
```
![](https://github.com/Andrei4e/finalWork2/blob/main/screens/6.png?raw=true)
### Task 9
Заполнить низкоуровневые таблицы именами(животных), командами
которые они выполняют и датами рождения
```sql
USE friendsMans;

INSERT INTO cats (Name, Birthday, Commands, KindId)
VALUES ('Бобока', '2017-07-07', 'лежать', 1),
('Ева', '2015-01-01', 'Есть', 1),  
('Бармалей', '2011-01-01', 'Кис', 1); 

INSERT INTO dogs (Name, Birthday, Commands, KindId)
VALUES ('Шарик', '2010-01-01', 'сиджеть, лежать', 2),
('Бобик', '2015-01-01', 'голос, лапу', 2);

INSERT INTO hamster (Name, Birthday, Commands, KindId)
VALUES ('Машка', '2022-01-01', 'грызть', 3),
('Буся', '2023-01-01', 'висеть', 3);

INSERT INTO horses (Name, Birthday, Commands, KindId)
VALUES ('Сивка', '2010-01-01', 'голоп', 1),
('Бурка', '2015-01-01', 'молодец', 1);

INSERT INTO camels (Name, Birthday, Commands, KindId)
VALUES ('Горб1', '2010-01-01', 'команда1', 3),
('Горб1', '2015-01-01', 'команда2', 3);

INSERT INTO donkeys (Name, Birthday, Commands, KindId)
VALUES ('Малой', '2010-01-01', 'команда1', 2),
('Сизый', '2015-01-01', 'команда2', 2);
```
![](https://github.com/Andrei4e/finalWork2/blob/main/screens/7.png?raw=true)
### Task 10
Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.

```sql
SET SQL_SAFE_UPDATES = 0;
DELETE FROM camels;

SELECT Name, Birthday, Commands FROM horses
UNION SELECT  Name, Birthday, Commands FROM donkeys;
```
![](https://github.com/Andrei4e/finalWork2/blob/main/screens/8.png?raw=true)
### Task 11
Создать новую таблицу “молодые животные” в которую попадут все
животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
до месяца подсчитать возраст животных в новой таблице
```sql
CREATE TEMPORARY TABLE allAnimals AS 
SELECT *, 'Лошади' as Kind FROM horses
UNION SELECT *, 'Ослы' AS Kind FROM donkeys
UNION SELECT *, 'Собаки' AS Kind FROM dogs
UNION SELECT *, 'Кошки' AS Kind FROM cats
UNION SELECT *, 'Хомяки' AS Kind FROM hamster;

CREATE TABLE youngAnimal AS
SELECT Name, Birthday, Commands, Kind, TIMESTAMPDIFF(MONTH, Birthday, CURDATE()) AS Age
FROM allAnimals WHERE Birthday BETWEEN ADDDATE(curdate(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR);
 
SELECT * FROM youngAnimal;
```
![](https://github.com/Andrei4e/finalWork2/blob/main/screens/9.png?raw=true)
### Task 12
 Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
прошлую принадлежность к старым таблицам.

```sql
SELECT ho.Name, ho.Birthday, ho.Commands, pa.KindName, ya.Age
FROM horses ho
LEFT JOIN youngAnimal ya ON ya.Name = ho.Name
LEFT JOIN packAnimals pa ON pa.Id = ho.KindId
UNION

SELECT d.Name, d.Birthday, d.Commands, pa.KindName, ya.Age
FROM donkeys d 
LEFT JOIN youngAnimal ya ON ya.Name = d.Name
LEFT JOIN packAnimals pa ON pa.Id = d.KindId
UNION

SELECT c.Name, c.Birthday, c.Commands, ha.KindName, ya.Age
FROM cats c
LEFT JOIN youngAnimal ya ON ya.Name = c.Name
LEFT JOIN homeAnimals ha ON ha.Id = c.KindId
UNION

SELECT d.Name, d.Birthday, d.Commands, ha.KindName, ya.Age
FROM dogs d
LEFT JOIN youngAnimal ya ON ya.Name = d.Name
LEFT JOIN homeAnimals ha ON ha.Id = d.KindId
UNION

SELECT hm.Name, hm.Birthday, hm.Commands, ha.KindName, ya.Age
FROM hamster hm
LEFT JOIN youngAnimal ya ON ya.Name = hm.Name
LEFT JOIN homeAnimals ha ON ha.Id = hm.KindId;
```
![](https://github.com/Andrei4e/finalWork2/blob/main/screens/10.png?raw=true)