CREATE TABLE buyers (
  id SERIAL PRIMARY KEY,
  surname VARCHAR(30) NOT NULL,
  name VARCHAR(30) NOT NULL,
  patronymic VARCHAR(30),
  gender VARCHAR(10) NOT NULL CHECK (gender IN ('MALE', 'FEMALE')),
  nation VARCHAR(30) NOT NULL,
  height INT NOT NULL,
  weight INT NOT NULL,
  birthday DATE NOT NULL,
  phone_number VARCHAR(15) NOT NULL,
  post_code VARCHAR(10) NOT NULL,
  country VARCHAR(30) NOT NULL,
  region VARCHAR(30),
  district VARCHAR(30),
  city VARCHAR(30) NOT NULL,
  street VARCHAR(30) NOT NULL,
  building VARCHAR(10) NOT NULL,
  flat INT,
  credit_card VARCHAR(16) CHECK (NOT credit_card like '%[^0-9]%'),
  bank_account VARCHAR(34)  
);

INSERT INTO buyers (surname, name, patronymic, gender, nation, height, weight, 
                    birthday, phone_number, post_code, country, region, district,
                    city, street, building, flat, credit_card, bank_account)
  VALUES ('Горбач', 'Дмитрий', 'Николаевич', 'MALE', 'Belarusian', 182, 70,
         (TO_DATE('13.09.2001', 'DD.MM.YYYY')), '375291976500', '220137', 'Belarus', 'Minsk', 'Minsk', 
         'Minsk', 'ул. Ангарская', '21', 1, '1234432187650987', '12345680'),
         ('Врублевская', 'Екатерина', 'Александровна', 'FEMALE', 'Belarusian', 173, 50,
         (TO_DATE('29.07.2002', 'DD.MM.YYYY')), '375291972233', '220102', 'Belarus', 'Minsk', 'Minsk', 
         'Dziarzhynsk', 'пер. Весенний', '23', NULL, '1234432187650232', '12345610'),
         ('Доскоч', 'Роман', 'Дмитриевич', 'MALE', 'Belarusian', 183, 68,
         (TO_DATE('15.05.2002', 'DD.MM.YYYY')), '375295553535', '220047', 'Belarus', 'Mahilyow', 'Horki', 
         'Horki', 'ул. Сельскохозяйственная', '3', 1, '1231232187650987', '12345220'),
         ('Буль', 'Константин', 'Леонидович', 'MALE', 'Belarusian', 165, 70,
         (TO_DATE('15.04.2003', 'DD.MM.YYYY')), '375297258444', '220047', 'Belarus', 'Minsk', 'Minsk', 
         'Minsk', 'ул. Нестерова', '54/2', 47, '1324432187650987', '12345910'),
         ('Уехавшая', 'Елизавета', 'Валерьевна', 'FEMALE', 'Belarusian', 172, 51,
         (TO_DATE('27.09.2002', 'DD.MM.YYYY')), '375292003040', '220137', 'Belarus', 'Brest', 'Brest', 
         'Brest', 'ул. Польская', '35', 121, '1123232187650987', '23345680'), 
		 ('Котько', 'Пётр', 'Васильевич', 'MALE', 'Ukrainian', 175, 80,
         (TO_DATE('10.12.1999', 'DD.MM.YYYY')), '480291976500', '220137', 'Ukraine', 'Kyiv', 'Kyiv', 
         'Kyiv', 'вул. Аеродромна', '14/2', 1, '1234432187650987', '12345680');
		 
CREATE TABLE shop (
  id SERIAL PRIMARY KEY,
  shop_name VARCHAR(30),
  shop_description VARCHAR(255)
);

INSERT INTO shop (shop_name, shop_description)
    values ('Евроопт', null),
           ('Гиппо', null),
            ('Виталюр', null),
            ('Белмаркет', null),
            ('Макдональдс', null);