select "Name_stud", "Cont", "Title", "TypCrc"
from "Contract"
inner join "Student" using ("Id_Stud")
inner join "Course" using ("Id_Crs")
where "Title" = 'Матанализ'
order by "Name_stud";

select "Title", "Name_stud"
from "Contract"
inner join "Student" using ("Id_Stud")
inner join "Course" using ("Id_Crs")
where "TypCrc" = 'начальный' and "Cost" < 1500
order by "Title", "Name_stud";

select "Title", "Name_stud", "Cost"
from "Contract"
inner join "Student" using ("Id_Stud")
inner join "Course" using ("Id_Crs")
where "Name_stud" ilike 'К%'
order by "Cost";

select "Title", "Hur", "TypCrc", "Name_stud", "Cont"
from "Contract"
inner join "Student" using ("Id_Stud")
inner join "Course" using ("Id_Crs")
inner join "Tchr" using ("Id_Tch")
where "NameTch" = 'Сидорова Наталья Петровна'
order by "Title", "Name_stud";

select "Name_stud", "Cont", "Title", "Hur", "TypCrc"
from "Contract"
inner join "Student" using ("Id_Stud")
inner join "Course" using ("Id_Crs")
where "Org" = 'МЭИ'
order by "Title", "Name_stud";

select "Name_stud", "Cont", "Org", "Title", "Cost"
from "Contract"
inner join "Student" using ("Id_Stud")
inner join "Course" using ("Id_Crs")
where "Name_stud" ilike 'К%' and "TypCrc" = 'начальный'
order by "Org", "Name_stud";

select "Title", "TypCrc", "Cost", "Hur"
from "Course"
where "Cost" = (
	select max("Cost") from "Course"
)
order by "Title";

select "Title", "TypCrc", "Cost", "Hur"
from "Course"
where "Cost" > (
	select avg("Cost") from "Course"
)
order by "Title";

select "Name_stud", "Cont", "Org"
from "Student"
where "disCnt" = (
	select max("disCnt") from "Student"
)
order by "Name_stud";

select "NameTch", "ContTch", "Dol"
from "Tchr"
where "Sal" > (
	select avg("Sal") from "Tchr"
)
order by "NameTch";

select "Name_stud", "Cont", "Title", "Cost"
from "Contract"
inner join "Course" using ("Id_Crs")
inner join "Student" using ("Id_Stud")
where "Cost" = (
	select max("Cost") from "Course"
)
order by "Title", "Name_stud";

select "NameTch", "ContTch", "Dol", "Title", "Cost"
from "Course"
inner join "Tchr" using ("Id_Tch")
where "Hur" > 36 and "Dol" is not null
order by "NameTch";

select "NameTch", "ContTch", "Dol", "Sal"
from "Tchr"
where "Sx_Tc" = 'м' and "Sal" < (
    select avg("Sal") from "Tchr"
    )
order by "NameTch";

select "NameTch", "ContTch", "Sal", "Title", "Cost"
from "Course"
inner join "Tchr" using ("Id_Tch")
where "Sal" > 1000 and "Dol" is not null and "TypCrc" = 'начальный'
order by "NameTch";