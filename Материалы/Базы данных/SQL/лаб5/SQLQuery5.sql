/*1.��������� ������ ���������� ��������� �����. ������ �������� ��� ���������, ��� ���������� ������, 
�������� �����, ��� ���. ������ ������������� �� ������� ���������.*/

SELECT Student.Name_Stud, Student.Cont, Course.Title, Course.TypCrs
FROM Student
INNER JOIN Contract ON Student.Id_Stud = Contract.Id_Stud
INNER JOIN Course ON Contract.Id_Crs = Course.Id_Crs
ORDER BY Student.Name_Stud;

/*2. ��������� ������ ���������� ������, ��������� ������� ����� 1500 ���., 
� � ������� ����� ��� ����������. ������ ������������� �� �������� ������ � ������� ���������.*/

SELECT Student.Name_Stud, Student.Cont, Course.Title, Course.TypCrs
FROM Student
INNER JOIN Contract ON Student.Id_Stud = Contract.Id_Stud
INNER JOIN Course ON Contract.Id_Crs = Course.Id_Crs
WHERE Course.Cost < 1500 AND Course.TypCrs = '���������'
ORDER BY Student.Name_Stud, Course.Title;

/*3. ��������� ������ ���������� ��� ����������, ������� ������� ���������� �� ����� �. 
������ ������ ��������� ��� ���������, �������� ������, �� ������� �� �������� ��������, 
��������� ������� �����. ������ ������������� �� ��������� �����.*/

SELECT Student.Name_Stud, Student.Cont, Course.Title, Course.Cost
FROM Student
INNER JOIN Contract ON Student.Id_Stud = Contract.Id_Stud
INNER JOIN Course ON Contract.Id_Crs = Course.Id_Crs
WHERE Student.Name_Stud LIKE '�%'
ORDER BY Student.Name_Stud, Course.Cost;

/*4. ��������� ������ ����������, � �������� ���������� �������� �������������. � �������������� ������ 
�������� �������� �����, ���������� �����, ��� �����, ��� ���������, ��� ���������� ������. 
������ ������������� �� �������� ����� � ��� ���������.*/

SELECT Course.Title, Course.Hur, Course.TypCrs, Student.Name_Stud, Student.Cont
FROM Student
INNER JOIN Contract ON Student.Id_Stud = Contract.Id_Stud
INNER JOIN Course ON Contract.Id_Crs = Course.Id_Crs
INNER JOIN Tchr ON Course.Id_Tch = Tchr.Id_Tch
WHERE Tchr.NameTch LIKE (SELECT NameTch FROM Tchr WHERE dol = '������' AND Sal = 2000)
ORDER BY Course.Title, Student.Name_Stud

/*5. ��������� ������ ������, �� ������� ��������� ���������� �� �������� �����������. 
������ ������ ��������� ��� ���������, ���������� ������, �������� �����, ��� �����, 
���������� �����. ������ ������������� �� �������� ����� � ��� ���������.*/

SELECT Student.Name_Stud, Student.Cont, 
(SELECT Title FROM Course WHERE Id_Crs IN (SELECT Id_Crs FROM Contract WHERE Id_Stud = Student.Id_Stud)) AS Title,
(SELECT TypCrs FROM Course WHERE Id_Crs IN (SELECT Id_Crs FROM Contract WHERE Id_Stud = Student.Id_Stud)) AS TypCrs,
(SELECT Hur FROM Course WHERE Id_Crs IN (SELECT Id_Crs FROM Contract WHERE Id_Stud = Student.Id_Stud)) AS Hur,
FROM Student
WHERE Student.Org LIKE '��������1'
ORDER BY Course.Title, Student.Name_Stud;



/*6. ��������� ������ ���������� � ���������, ������������� �� ����� �, ������� ��������� �� ������, ������� ��� ����������. 
������ ������ ��������� ��� ���������, ���������� ������, �������� �����������, �������� �����, ��� ��������� 
������ ������������� ������ �� �������� ����������� � ��� ���������.*/

SELECT Name_Stud, Student.Cont, Student.Org, 
(SELECT Title FROM Course WHERE Id_Crs IN (SELECT Id_Crs FROM Contract WHERE Id_Stud = Student.Id_Stud)) AS Title,
(SELECT TypCrs FROM Course WHERE Id_Crs IN (SELECT Id_Crs FROM Contract WHERE Id_Stud = Student.Id_Stud)) AS TypCrs
FROM Student
WHERE Student.Name_Stud LIKE '�%' AND Student.Id_Stud IN
(SELECT Id_Stud from Contract WHERE Id_Crs IN
(SELECT Id_Crs FROM Course WHERE TypCrs = '���������'))
ORDER BY Student.Org, Student.Name_Stud;

SELECT Student.Name_Stud, Student.Cont, Student.Org, Course.Title, Course.Cost
FROM Student
INNER JOIN Contract ON Student.Id_Stud = Contract.Id_Stud
INNER JOIN Course ON Contract.Id_Crs = Course.Id_Crs
WHERE Student.Name_Stud LIKE '�%' AND Course.TypCrs = '���������'
ORDER BY Student.Org, Student.Name_Stud;



/*7. ��������� ������ ������, �� ������� �������� ������������ ���������� �����. ������ ������ ��������� ��������, 
���, ���������, ���������� �����. ������ ������������� �������� �����.*/

SELECT Course.Title, Course.TypCrs, Course.Cost, Course.Hur
FROM Course
WHERE Course.Hur = (SELECT MAX(Course.Hur) FROM Course)
ORDER By Course.Title;

/*8. ��������� ������ ������, ��������� ������� ���� �������. 
������ ������ ��������� �������� �����, ���, ��������� � ���������� �����.*/

SELECT Course.Title, Course.TypCrs, Course.Cost, Course.Hur
FROM Course
WHERE Course.Hur >= (SELECT AVG(Course.Hur) FROM Course)

/*9. ��������� ������ ����������, ������� ����� ������������ ������ �� ��������. ������ ������ ��������� 
��� ���������, ���������� ������, �������� �����������. ������ ������������� ������ �� �������.*/

SELECT Student.Name_Stud, Student.Cont, Student.Org
FROM Student
WHERE Student.disCnt = (SELECT MAX(Student.disCnt) FROM Student)
ORDER BY Student.Name_Stud;

/*10. ��������� ������ ��������������, � ������� ��������� ���� ������ ���� �������. ������ ������ ��������� 
��� �������������, ���������� ������, �������. ������ ������������� ������ �� ������� �������������.*/

SELECT Tchr.NameTch, Tchr.ContTch, Tchr.Dol
FROM Tchr
WHERE Tchr.Sal >= (SELECT AVG(Tchr.Sal) FROM Tchr)
ORDER BY Tchr.NameTch;

/*11. ��������� ������ ����������, ������� ��������� �� ������ � ������������ ����������. ������ ������ 
��������� ��� ���������, ���������� ������, �������� �����, ��� ���������. ������ ������������� �� 
�������� ����� � ��� ���������.*/

SELECT Student.Name_Stud, Student.Cont, Course.Title, Course.Cost
FROM Student
FULL JOIN Contract ON Student.Id_Stud = Contract.Id_Stud
FULL JOIN Course ON Contract.Id_Crs = Course.Id_Crs
WHERE Course.Cost = (SELECT MAX(Course.Cost) FROM Course)
ORDER BY Course.Title, Student.Name_Stud;

/*12. ��������� ������ ��������������, ������� �������� ������� �� ������, � �������� ������� ���� ����� �SQL�. 
������ ������ ��������� ��� �������������, �������� �����, ��� ��� � �����������������. ������ ������������� �� ��� �������������.*/

SELECT Tchr.NameTch, Course.Title, Course.TypCrs, Course.Hur
FROM Tchr
INNER JOIN Course ON Course.Id_Tch = Tchr.Id_Tch
WHERE Course.Title LIKE '%SQL%'
ORDER BY Tchr.NameTch;

/*13. ��������� ������ ��������������, ������� ������� � ����������� �� ������, ������������ ������� ����� 36 �����. 
������ ������ ��������� ��� �������������, ���������� ������, �������, �������� ����� � ��� ���������. ������ ������������� ������ �� ������� �������������.*/

SELECT Tchr.NameTch, Tchr.ContTch, Tchr.Dol, Course.Title, Course.Cost
FROM Tchr
INNER JOIN Course ON Course.Id_Tch = Tchr.Id_Tch
WHERE Course.Hur > 36 AND NOT Tchr.Dol = ' '
ORDER BY Tchr.NameTch;

/*14. ��������� ������ �������������� �������� ����, ������� �������, ��������� ���� ������ ������� ������ 
������� ��������� ����. ������ ������ ��������� ��� �������������, ���������� ������, �������, 
��������� ���� ������. ������ ������������� �� ������� � ������� �������������.*/

SELECT Tchr.NameTch, Tchr.ContTch, Tchr.Dol, Tchr.Sal
FROM Tchr
WHERE Tchr.Sx_Tc = '�' AND Dol != ''
AND Tchr.Sal <= (SELECT AVG(Tchr.Sal) FROM Tchr)
ORDER BY Tchr.NameTch;

/*15. ��������� ������ ��������������, ������� ������� � ��������� ���� ����� 3000 ���., ������� ����� ������� �� ������, 
������� ��� ���������� ������ ������ ��������� ��� �������������, ���������� ������, ��������� ���� ������, �������� 
����� � ��� ���������. ������ ������������� �� ������� � ��������� ����.*/

SELECT Tchr.NameTch, Tchr.ContTch, Tchr.Sal, Course.Title, Course.Cost
FROM Tchr
INNER JOIN Course ON Course.Id_Tch = Tchr.Id_Tch
WHERE Course.TypCrs = '���������' AND Tchr.Sal >= 3000
ORDER BY Tchr.Dol, Tchr.Sal;

