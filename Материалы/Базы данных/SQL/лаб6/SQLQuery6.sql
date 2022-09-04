/*Реализовать в виде хранимой процедуры статистические запросы, из задания к лабораторной работе №5 (по заданию преподавателя)*/

CREATE PROCEDURE Student_Info
@Course_Title varchar(30)
AS
BEGIN

--список слушателей
SELECT Name_Stud, Cont, Title, TypCrs FROM Student
JOIN Contract ON Contract.Id_Stud = Student.Id_Stud
JOIN Course ON Course.Id_Crs = Contract.Id_Crs
WHERE Course.Title = @Course_Title
ORDER BY Student.Name_Stud;

--список слушателей курсов, стоимость которых менее 3500 руб., и с которые имеют тип «начальный»
SELECT Student.Name_Stud, Student.Cont, Course.Title, Course.TypCrs
FROM Student
INNER JOIN Contract ON Student.Id_Stud = Contract.Id_Stud
INNER JOIN Course ON Course.Id_Crs = Contract.Id_Crs
WHERE Course.Cost < 3500 AND Course.TypCrs = 'Начальный'
ORDER BY Student.Name_Stud, Course.Title;

--список слушателей, с которыми занимается заданный преподаватель
SELECT Course.Title, Course.Hur, Course.TypCrs, Student.Name_Stud, Student.Cont
FROM Student
INNER JOIN Contract ON Student.Id_Stud = Contract.Id_Stud
INNER JOIN Course ON Contract.Id_Crs = Course.Id_Crs
INNER JOIN Tchr ON Course.Id_Tch = Tchr.Id_Tch
WHERE Tchr.NameTch LIKE 'Наталья Петровна'
ORDER BY Course.Title, Student.Name_Stud;

--имеют максимальную скидку на обучение
SELECT Student.Name_Stud, Student.Cont, Student.Org
FROM Student
WHERE Student.disCnt = (SELECT MAX(Student.disCnt) FROM Student)
ORDER BY Student.Name_Stud;
END

/*2. Выполнить разработанную процедуру*/

DROP PROCEDURE Student_Info
EXEC Student_Info 'Базы данных SQL'

/*3. Разработать хранимую процедуру с входным и выходным параметрами для определения общей суммы контрактов заданного слушателя*/


CREATE PROCEDURE Student_Contracts_Sum
@Stud_Name varchar(30),
@Contracts_Count int OUTPUT
AS
BEGIN
SELECT @Contracts_Count = SUM(Course.Cost)
FROM Course
INNER JOIN Contract ON Contract.Id_Crs = Course.Id_Crs
INNER JOIN Student ON Student.Id_Stud = Contract.Id_Stud
WHERE Student.Name_Stud = @Stud_Name;
PRINT(@Contracts_Count)
END


 

/*4. Выполнить разработанную процедуру*/
DROP PROCEDURE Student_Contracts_Sum; 
EXEC Student_Contracts_Sum 'Крылов Кирилл', 0;

/*5. Разработать процедуру по заданию преподавателя с использованием курсора (Приложение II)
Разработать хранимую процедуру рассчитывающую статистику учащихся по городам. Данные отчета сформировать в виде
<Город> - <кол-во учащихся из этого города> <список имен учащихся>.*/

CREATE PROCEDURE City_stat
AS BEGIN
Declare @city varchar(20)
declare @cnt int
declare cur cursor
for select s.Cty,COUNT(*) as cnt
from Student as s
group by s.Cty;
open cur
fetch next from cur into @city,@cnt
while @@FETCH_STATUS=0
begin
select @city as City,@cnt as [Count]
select s.Name_stud as Name
from Student as s where s.Cty=@city
fetch next from cur into @city,@cnt
end
close cur
deallocate cur
END


/*6. Выполнить разработанную процедуру*/
DROP PROCEDURE City_stat;
EXEC City_stat;