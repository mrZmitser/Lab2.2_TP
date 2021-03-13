create table shop (
  id serial primary key,
  shop_name varchar(30),
  shop_description varchar(255)
);

insert into shop (shop_name, shop_description)
    values ('Евроопт', null),
           ('Гиппо', null),
            ('Виталюр', null),
            ('Белмаркет', null),
            ('Макдональдс', null);

create table buyers (
  id serial primary key,
  surname varchar(30) not null,
  name VARCHAR(30) NOT NULL,
  patronymic varchar(30),
  gender varchar(10) not null check (gender in ('MALE', 'FEMALE')),
  nation varchar(30) not null,
  height int not null,
  weight int not null,
  birthday date not null,
  phone_number varchar(15) not null,
  post_code varchar(10) not null,
  country varchar(30) not null,
  region varchar(30),
  district varchar(30),
  city varchar(30) not null,
  street varchar(30) not null,
  building varchar(10) not null,
  flat int,
  credit_card varchar(16) check (not credit_card like '%[^0-9]%'),
  bank_account varchar(34),
  shop int,
  constraint shop
      foreign key(shop)
	  references shop(id)
);

insert into buyers (surname, name, patronymic, gender, nation, height, weight,
                    birthday, phone_number, post_code, country, region, district,
                    city, street, building, flat, credit_card, bank_account, shop)
  values ('Горбач', 'Дмитрий', 'Николаевич', 'MALE', 'Belarusian', 182, 70,
         (to_date('13.09.2001', 'DD.MM.YYYY')), '375291976500', '220137', 'Belarus', 'Minsk', 'Minsk',
         'Minsk', 'ул. Ангарская', '21', 1, '1234432187650987', '12345680', 1),
         ('Врублевская', 'Екатерина', 'Александровна', 'FEMALE', 'Belarusian', 173, 50,
         (to_date('29.07.2002', 'DD.MM.YYYY')), '375291972233', '220102', 'Belarus', 'Minsk', 'Minsk',
         'Dziarzhynsk', 'пер. Весенний', '23', null, '1234432187650232', '12345610', 1),
         ('Доскоч', 'Роман', 'Дмитриевич', 'MALE', 'Belarusian', 183, 68,
         (to_date('15.05.2002', 'DD.MM.YYYY')), '375295553535', '220047', 'Belarus', 'Mahilyow', 'Horki',
         'Horki', 'ул. Сельскохозяйственная', '3', 1, '1231232187650987', '12345220', 2),
         ('Буль', 'Константин', 'Леонидович', 'MALE', 'Belarusian', 165, 70,
         (to_date('15.04.2003', 'DD.MM.YYYY')), '375297258444', '220047', 'Belarus', 'Minsk', 'Minsk',
         'Minsk', 'ул. Нестерова', '54/2', 47, '1324432187650987', '12345910', 3),
         ('Уехавшая', 'Елизавета', 'Валерьевна', 'FEMALE', 'Belarusian', 172, 51,
         (to_date('27.09.2002', 'DD.MM.YYYY')), '375292003040', '220137', 'Belarus', 'Brest', 'Brest',
         'Brest', 'ул. Польская', '35', 121, '1123232187650987', '23345680', 4),
		 ('Котько', 'Пётр', 'Васильевич', 'MALE', 'Ukrainian', 175, 80,
         (to_date('10.12.1999', 'DD.MM.YYYY')), '480291976500', '220137', 'Ukraine', 'Kyiv', 'Kyiv',
         'Kyiv', 'вул. Аеродромна', '14/2', 1, '1234432187650987', '12345680', 5);
		 
