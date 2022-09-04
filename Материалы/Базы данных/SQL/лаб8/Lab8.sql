
/*2
�������� �������� ������� Contract, ������ ���� Id_Crs ���
������� ����, ����������� ��������� ������� ������������
�������
*/

alter table Contract
add foreign key (Id_Crs) references Course(Id_Crs) on update no action

/*3
��������� �������� �������� �����, � ������� ������� ����
�� ���� ������ ������� Contract
*/

delete from Course where Id_Crs = 1

/*4
�������� �������, ������� ��������� ��������� ���������
����� � ��������� ������� � �������� Contract � Tchr ��� ���������
���� �������������
*/
if object_id ('dbo.CascadeUpdate') is not null
    drop trigger dbo.CascadeUpdate;

create trigger CascadeUpdate
on dbo.Tchr
for update
as
begin
	declare @Id_new int
	declare @Id_old int
	select @Id_new = Id_Tch from inserted
	select @Id_old = Id_Tch from deleted
	update Course set dbo.Course.Id_Tch = @Id_new
	where dbo.Course.Id_Tch = @Id_old
end
;

alter table Course
add foreign key (Id_Tch) references Tchr(Id_Tch) on update cascade

/*5
��������� ����������������� ��������, �������� �������
��������� ���� ������������� � ������� Tchr
*/
select * 
from dbo.Course 
where Id_Tch in (1, 5)
;

update dbo.Tchr
set dbo.Tchr.Id_Tch =7
where dbo.Tchr.Id_Tch = 5
;

select * 
from dbo.Course 
where Id_Tch in (1, 7)
;

select * 
from dbo.Tchr 
where Id_Tch in (1, 7)
;

/*6
����������� �������, ���������� �������� ������ ���
��������� �� ���������� �������
*/
if object_id ('dbo.update_cnt') is not null
    drop trigger dbo.update_cnt;

CREATE TRIGGER Contract_Insert
   ON  Contract
   AFTER Insert
AS 
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	DECLARE @sum INT;
	DECLARE @discnt INT;
	SELECT @sum = SUM(Cost) FROM Course
	JOIN Contract ON Contract.Id_Crs = Course.Id_Crs;
	IF (@sum BETWEEN 50000 AND 70000)
		SET @discnt = 5;
	IF (@sum BETWEEN 70000 AND 80000)
		SET @discnt = 6;
	IF (@sum BETWEEN 80000 AND 100000)
		SET @discnt = 10;
	IF (@sum > 100000)
		SET @discnt = 15;
    -- Insert statements for trigger here
	UPDATE Student
		SET disCnt = @discnt
	WHERE Id_Stud = (SELECT Id_Stud FROM inserted);

END



