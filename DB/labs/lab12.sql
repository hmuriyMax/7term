-- 2
alter table "Contract"
    drop constraint "Course";

alter table "Contract"
    add constraint "Course"
        foreign key ("Id_Crs") references "Course"
            on update restrict;

-- 3
delete from "Course"
where "Title" = 'Основы SQL';

-- 4
create or replace function update_course_teacher() returns trigger as $course$
begin
    update "Course"
    set "Id_Tch" = new."Id_Tch"
    where "Id_Tch" = old."Id_Tch";
    return new;
end;
$course$ language plpgsql;

create trigger update_course_teacher_trigger
after update on "Tchr"
for each row execute procedure update_course_teacher();

-- 5
update "Tchr" set "Id_Tch" = 10 where "Id_Tch" = 7;

-- 6
create or replace function set_discount() returns trigger as $discount$
declare total_sum bigint = 0;
    idStud int;
begin
    if tg_op = 'DELETE' then
        idStud = old."Id_Stud";
    end if;

    if tg_op = 'UPDATE' or tg_op = 'INSERT' then
        idStud = new."Id_Stud";
    end if;

   select sum("Cost") into total_sum from "Contract"
    inner join "Course"
    on "Contract"."Id_Crs" = "Course"."Id_Crs"
    where "Contract"."Id_Stud" = idStud;

    if (total_sum is null) then
    update "Student"
        set "disCnt" = 0
        where "Id_Stud" = idStud;
    end if;

    if (total_sum between 50000 and 70000) then
    update "Student"
        set "disCnt" = 5
        where "Id_Stud" = idStud;
    end if;

    if (total_sum between 70000 and 80000) then
    update "Student"
        set "disCnt" = 6
        where "Id_Stud" = idStud;
    end if;

    if (total_sum between 80000 and 100000) then
    update "Student"
        set "disCnt" = 10
        where "Id_Stud" = idStud;
    end if;

    if (total_sum > 100000) then
        update "Student"
        set "disCnt" = 15
        where "Id_Stud" = idStud;
    end if;

    if tg_op = 'DELETE' then
        return old;
    end if;
    return new;
end;
$discount$ language plpgsql;

create or replace trigger update_discount_for_insert
    after insert on "Contract"
    for each row execute function set_discount();

create or replace trigger update_discount_for_delete
    after delete on "Contract"
    for each row execute procedure set_discount();

create or replace trigger update_discount_for_update
    after update on "Contract"
    for each row execute function set_discount();
