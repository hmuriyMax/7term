-- 2
alter table "Course"
    add "Doc" varchar(16) default 'удостоверение';

comment on column "Course"."Doc" is 'Вид документа, может принимать значение: удостоверение, диплом, сертификат, справка';

-- 3
alter table "Course"
    add constraint check_name
        check ("Doc" in ('удостоверение', 'диплом', 'cертификат', 'справка'));

-- 4
alter table "Course"
    add constraint cost_check
        check ("Cost" >= 500),
    add constraint hur_check
        check ("Hur" >= 16),
    add constraint type_check
        check ("TypCrc" in ('начальный', 'продвинутый', 'профессиональный'));

-- 5
insert into "Course" values (9, 'НИР', 'просто сделать', 500, 4, 2, 'начальный');

-- 6
alter table "Course"
add constraint doc_check check (
    ("Doc" in('cертификат', 'cправка') and "Hur" <= 36) or
    ("Doc" in('удостоверение', 'сертификат') and "Hur" between 72 and 126) or
    ("Doc" in('удостоверение', 'диплом') and "Hur" > 126) or
    ("Hur" between 37 and 71));

-- 7
update "Course"
set "Hur" = 72 where "Title" = 'ОП';

-- 8
alter table "Tchr"
    add constraint gender_check
        check ("Sx_Tc" in ('м', 'ж'));

-- 9
insert into "Tchr"
values (10, 'Грин Боб Джекобна', 'greenbob@gosdep.us', 5000, 'профессор', 'а')