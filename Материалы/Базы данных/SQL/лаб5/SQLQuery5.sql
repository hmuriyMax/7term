/*1.Составить список слушателей заданного курса. Список включает ФИО слушателя, его контактные данные, 
название курса, его тип. Список отсортировать по фамилии слушателя.*/

SELECT Student.Name_Stud, Student.Cont, Course.Title, Course.TypCrs
FROM Student
INNER JOIN Contract ON Student.Id_Stud = Contract.Id_Stud
INNER JOIN Course ON Contract.Id_Crs = Course.Id_Crs
ORDER BY Student.Name_Stud;

/*2. Составить список слушателей курсов, стоимость которых менее 1500 руб., 
и с которые имеют тип «начальный». Список отсортировать по названию курсов и фамилии слушателя.*/

SELECT Student.Name_Stud, Student.Cont, Course.Title, Course.TypCrs
FROM Student
INNER JOIN Contract ON Student.Id_Stud = Contract.Id_Stud
INNER JOIN Course ON Contract.Id_Crs = Course.Id_Crs
WHERE Course.Cost < 1500 AND Course.TypCrs = 'Начальный'
ORDER BY Student.Name_Stud, Course.Title;

/*3. Составить список контрактов для слушателей, фамилия которых начинается на букву К. 
Список должен содержать ФИО слушателя, название курсов, на которые он заключал контракт, 
стоимость каждого курса. Список отсортировать по стоимости курса.*/

SELECT Student.Name_Stud, Student.Cont, Course.Title, Course.Cost
FROM Student
INNER JOIN Contract ON Student.Id_Stud = Contract.Id_Stud
INNER JOIN Course ON Contract.Id_Crs = Course.Id_Crs
WHERE Student.Name_Stud LIKE 'К%'
ORDER BY Student.Name_Stud, Course.Cost;

/*4. Составить список слушателей, с которыми занимается заданный преподаватель. В результирующий список 
включить название курса, количество часов, тип курса, ФИО слушателя, его контактные данные. 
Список отсортировать по названию курса и ФИО слушателя.*/

SELECT Course.Title, Course.Hur, Course.TypCrs, Student.Name_Stud, Student.Cont
FROM Student
INNER JOIN Contract ON Student.Id_Stud = Contract.Id_Stud
INNER JOIN Course ON Contract.Id_Crs = Course.Id_Crs
INNER JOIN Tchr ON Course.Id_Tch = Tchr.Id_Tch
WHERE Tchr.NameTch LIKE (SELECT NameTch FROM Tchr WHERE dol = 'Доцент' AND Sal = 2000)
ORDER BY Course.Title, Student.Name_Stud

/*5. Составить список курсов, на которых обучались слушателей из заданной организации. 
Список должен содержать ФИО слушателя, контактные данные, название курса, тип курса, 
количество часов. Список отсортировать по названию курса и ФИО слушателя.*/

SELECT Student.Name_Stud, Student.Cont, 
(SELECT Title FROM Course WHERE Id_Crs IN (SELECT Id_Crs FROM Contract WHERE Id_Stud = Student.Id_Stud)) AS Title,
(SELECT TypCrs FROM Course WHERE Id_Crs IN (SELECT Id_Crs FROM Contract WHERE Id_Stud = Student.Id_Stud)) AS TypCrs,
(SELECT Hur FROM Course WHERE Id_Crs IN (SELECT Id_Crs FROM Contract WHERE Id_Stud = Student.Id_Stud)) AS Hur,
FROM Student
WHERE Student.Org LIKE 'Компания1'
ORDER BY Course.Title, Student.Name_Stud;



/*6. Составить список слушателей с фамилиями, начинающимися на букву К, которые обучались на курсах, имеющих тип «начальный». 
Список должен содержать ФИО слушателя, контактные данные, название организации, название курса, его стоимость 
Список отсортировать данные по названию организации и ФИО слушателя.*/

SELECT Name_Stud, Student.Cont, Student.Org, 
(SELECT Title FROM Course WHERE Id_Crs IN (SELECT Id_Crs FROM Contract WHERE Id_Stud = Student.Id_Stud)) AS Title,
(SELECT TypCrs FROM Course WHERE Id_Crs IN (SELECT Id_Crs FROM Contract WHERE Id_Stud = Student.Id_Stud)) AS TypCrs
FROM Student
WHERE Student.Name_Stud LIKE 'К%' AND Student.Id_Stud IN
(SELECT Id_Stud from Contract WHERE Id_Crs IN
(SELECT Id_Crs FROM Course WHERE TypCrs = 'Начальный'))
ORDER BY Student.Org, Student.Name_Stud;

SELECT Student.Name_Stud, Student.Cont, Student.Org, Course.Title, Course.Cost
FROM Student
INNER JOIN Contract ON Student.Id_Stud = Contract.Id_Stud
INNER JOIN Course ON Contract.Id_Crs = Course.Id_Crs
WHERE Student.Name_Stud LIKE 'К%' AND Course.TypCrs = 'Начальный'
ORDER BY Student.Org, Student.Name_Stud;



/*7. Составить список курсов, на которые отведено максимальное количество часов. Список должен содержать название, 
тип, стоимость, количество часов. Список отсортировать названию курса.*/

SELECT Course.Title, Course.TypCrs, Course.Cost, Course.Hur
FROM Course
WHERE Course.Hur = (SELECT MAX(Course.Hur) FROM Course)
ORDER By Course.Title;

/*8. Составить список курсов, стоимость которых выше средней. 
Список должен содержать название курса, тип, стоимость и количество часов.*/

SELECT Course.Title, Course.TypCrs, Course.Cost, Course.Hur
FROM Course
WHERE Course.Hur >= (SELECT AVG(Course.Hur) FROM Course)

/*9. Составить список слушателей, которые имеют максимальную скидку на обучение. Список должен содержать 
ФИО слушателя, контактные данные, название организации. Список отсортировать данные по фамилии.*/

SELECT Student.Name_Stud, Student.Cont, Student.Org
FROM Student
WHERE Student.disCnt = (SELECT MAX(Student.disCnt) FROM Student)
ORDER BY Student.Name_Stud;

/*10. Составить список преподавателей, у которых стоимость часа работы выше средней. Список должен содержать 
ФИО преподавателя, контактные данные, степень. Список отсортировать данные по фамилии преподавателя.*/

SELECT Tchr.NameTch, Tchr.ContTch, Tchr.Dol
FROM Tchr
WHERE Tchr.Sal >= (SELECT AVG(Tchr.Sal) FROM Tchr)
ORDER BY Tchr.NameTch;

/*11. Составить список слушателей, которые обучаются на курсах с максимальной стоимостью. Список должен 
содержать ФИО слушателя, контактные данные, название курса, его стоимость. Список отсортировать по 
названию курса и ФИО слушателя.*/

SELECT Student.Name_Stud, Student.Cont, Course.Title, Course.Cost
FROM Student
FULL JOIN Contract ON Student.Id_Stud = Contract.Id_Stud
FULL JOIN Course ON Contract.Id_Crs = Course.Id_Crs
WHERE Course.Cost = (SELECT MAX(Course.Cost) FROM Course)
ORDER BY Course.Title, Student.Name_Stud;

/*12. Составить список преподавателей, которые проводят занятия на курсах, в названии которых есть слово «SQL». 
Список должен содержать ФИО преподавателя, название курса, его тип и продолжительность. Данные отсортировать по ФИО преподавателя.*/

SELECT Tchr.NameTch, Course.Title, Course.TypCrs, Course.Hur
FROM Tchr
INNER JOIN Course ON Course.Id_Tch = Tchr.Id_Tch
WHERE Course.Title LIKE '%SQL%'
ORDER BY Tchr.NameTch;

/*13. Составить список преподавателей, имеющих степень и преподающих на курсах, длительность которых более 36 часов. 
Список должен содержать ФИО преподавателя, контактные данные, степень, название курса и его стоимость. Список отсортировать данные по фамилии преподавателя.*/

SELECT Tchr.NameTch, Tchr.ContTch, Tchr.Dol, Course.Title, Course.Cost
FROM Tchr
INNER JOIN Course ON Course.Id_Tch = Tchr.Id_Tch
WHERE Course.Hur > 36 AND NOT Tchr.Dol = ' '
ORDER BY Tchr.NameTch;

/*14. Составить список преподавателей мужского пола, имеющих степень, стоимость часа работы которых меньше 
средней стоимости часа. Список должен содержать ФИО преподавателя, контактные данные, степень, 
стоимость часа работы. Список отсортировать по степени и фамилии преподавателя.*/

SELECT Tchr.NameTch, Tchr.ContTch, Tchr.Dol, Tchr.Sal
FROM Tchr
WHERE Tchr.Sx_Tc = 'М' AND Dol != ''
AND Tchr.Sal <= (SELECT AVG(Tchr.Sal) FROM Tchr)
ORDER BY Tchr.NameTch;

/*15. Составить список преподавателей, имеющих степень и стоимость часа более 3000 руб., которые ведут занятия на курсах, 
имеющих тип «начальный» Список должен содержать ФИО преподавателя, контактные данные, стоимость часа работы, название 
курса и его стоимость. Список отсортировать по степени и стоимости часа.*/

SELECT Tchr.NameTch, Tchr.ContTch, Tchr.Sal, Course.Title, Course.Cost
FROM Tchr
INNER JOIN Course ON Course.Id_Tch = Tchr.Id_Tch
WHERE Course.TypCrs = 'Начальный' AND Tchr.Sal >= 3000
ORDER BY Tchr.Dol, Tchr.Sal;

