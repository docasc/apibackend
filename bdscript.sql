drop table if exists posts;
drop table if exists usuarios;
create table if not exists usuarios(
    id serial primary key not null,
    cellphone Varchar(9) not null,
    name varchar not null,
    lastname varchar not null,
    password varchar not null,
    fec_user_reg timestamp null,
    fec_user_mod timestamp null
);

create table if not exists posts(
    id serial primary key not null,
    text varchar not null,
    idUser integer not null,
    fec_post_reg timestamp null,
    fec_post_mod timestamp null
);

ALTER TABLE posts
ADD CONSTRAINT fk_constraint
FOREIGN KEY (idUser) REFERENCES usuarios (id);

SET TIME ZONE 'America/Lima';

INSERT INTO public.usuarios (id, cellphone, name, lastname, password,fec_user_reg,fec_user_mod) VALUES (1, '994313644', 'Alexander', 'Ocas', '123456',now(),now());
INSERT INTO public.usuarios (id, cellphone, name, lastname, password,fec_user_reg,fec_user_mod) VALUES (2, '966538008', 'Rosario', 'Carrasco', '1234567',now(),now());
INSERT INTO public.usuarios (id, cellphone, name, lastname, password,fec_user_reg,fec_user_mod) VALUES (3, '954370170', 'Davids', 'Ocas', '12345678',now(),now());
INSERT INTO public.usuarios (id, cellphone, name, lastname, password,fec_user_reg,fec_user_mod) VALUES (14, '974563216', 'Diana Jaqueline', 'Janampa Meneses', '974563216',now(),now());
INSERT INTO public.usuarios (id, cellphone, name, lastname, password,fec_user_reg,fec_user_mod) VALUES (17, '983456329', 'Julio', 'Calizaya', '983456329',now(),now());



insert into posts(text, idUser,fec_post_reg,fec_post_mod) values('PRIMER POST',1,now(),now());
insert into posts(text, idUser,fec_post_reg,fec_post_mod) values('PRIMER POST',2,now(),now());
insert into posts(text, idUser,fec_post_reg,fec_post_mod) values('PRIMER POST',3,now(),now());
insert into posts(text, idUser,fec_post_reg,fec_post_mod) values('PRIMER POST',14,now(),now());
