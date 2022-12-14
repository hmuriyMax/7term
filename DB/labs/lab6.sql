select count(*) as "Общее число курсов" from "Course";
select "TypCrc" as "Тип курса", count(*) as "Количество" from "Course" group by "TypCrc";

select count(*) as "Общее число слушателей" from "Student";
select "Cty" as "Город", count(*) as "Количество"
from "Student"
where "Cty" is not NULL
group by "Cty";

select count(*) as "Общее число преподавателей" from "Tchr";
select "Dol" as "Степень", count(*) as "Количество"
from "Tchr"
where "Dol" is not NULL
group by "Dol";

select count(*) as "Общее число преподавателей" from "Tchr";
select "Sal" as "Стоймость часа", count(*) as "Количество"
from "Tchr"
where "Sal" is not NULL
group by "Sal";

select count(*) as "Общее число курсов" from "Course";
select "Hur" as "Количество часов", count(*) as "Количество"
from "Course"
where "Hur" is not NULL
group by "Hur";

select count(*) as "Общее число курсов" from "Course";
select "TypCrc" as "Тип курса", max("Cost") as "Максимальная стоймость"
from "Course"
where "TypCrc" is not NULL
group by "TypCrc";

select count(*) as "Общее число курсов" from "Course";
select "TypCrc" as "Тип курса", max("Cost") as "Максимальная стоймость"
from "Course"
where "TypCrc" is not NULL
group by "TypCrc";

select count(*) as "Общее число курсов" from "Course";
select "TypCrc" as "Тип курса", count(*) as "Количество", avg("Cost") as "Средняя стоймость"
from "Course"
where "TypCrc" is not NULL
group by "TypCrc";

select count(*) as "Общее число преподавателей" from "Tchr";
select "Dol" as "Степень", min("Sal") as "Минимальная стоймость часа", max("Sal") as "Максимальная стоймость часа"
from "Tchr"
where "Dol" is not NULL
group by "Dol";

select count(*) as "Общее число преподавателей" from "Tchr";
select "Dol" as "Степень", avg("Sal") as "Средняя стоймость часа"
from "Tchr"
where "Dol" is not NULL
group by "Dol";

select count(*) as "Общее число слушателей" from "Student";
select "disCnt" as "Размер скидки", count(*) as "Количество"
from "Student"
where "disCnt" is not NULL
group by "disCnt";
CREATE OR REPLACE FUNCTION query1(crs_name VARCHAR(20))
RETURNS TABLE (ФИО VARCHAR(40), Контакты VARCHAR(20), Название VARCHAR(20), Тип VARCHAR(20))
AS $$
    BEGIN
    RETURN QUERY select "Name_stud", "Cont", "Title", "TypCrc"
        from "Contract"
        inner join "Student" using ("Id_Stud")
        inner join "Course" using ("Id_Crs")
        where "Title" = crs_name
        order by "Name_stud";
    END; $$
LANGUAGE 'plpgsql';

SELECT * FROM query1('Матанализ');

-- drop function query1(crs_name VARCHAR(20))



CREATE OR REPLACE FUNCTION get_contract_sum(student_name VARCHAR(20))
    RETURNS bigint AS
$$
BEGIN
    RETURN (SELECT sum("Course"."Cost") AS cost
            FROM "Student"
                     INNER JOIN "Contract" using ("Id_Stud")
                     INNER JOIN "Course" using("Id_Crs")
            WHERE "Student"."Name_stud" = student_name);
END
$$  LANGUAGE plpgsql;

SELECT get_contract_sum('Щемилкин Максим Михайлович') as sum_of_cost_courses;



CREATE TYPE report AS (city VARCHAR(20), count integer, studs VARCHAR(50)[]);

CREATE OR REPLACE FUNCTION stats()
    RETURNS SETOF report
AS $$
DECLARE
     cr1 CURSOR FOR SELECT "Cty" FROM "Student" group by "Cty";

     city_name VARCHAR(20);
      count integer := 0;
      students VARCHAR(50)[];
      record report;
BEGIN
     OPEN cr1;
     LOOP
        FETCH cr1 INTO city_name;
        record.city = city_name;
        IF NOT FOUND THEN EXIT;END IF;
         SELECT COUNT("Id_Stud") INTO count FROM "Student" WHERE "Cty" = city_name;
        record.count = count;
        SELECT array_agg("Name_stud") INTO students FROM "Student" WHERE "Cty" = city_name;
        record.studs = students;
        RETURN NEXT record;
     END LOOP;
     CLOSE cr1;
     RETURN;
END;
$$
LANGUAGE 'plpgsql';

SELECT * FROM stats();

