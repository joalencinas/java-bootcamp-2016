
create database highschool;

use highschool;

create table STUDENT (studentID integer not null,
    STUDENTFirstName varchar(50) not null,
    STUDENTLastName varchar(50) not null,
    STUDENTDateOfBirth date not null,
    primary key (studentID));

create table TEACHER (teacherID integer not null,
    TEACHERFirstName varchar(50) not null,
    TEACHERLastName varchar(50) not null,
    TEACHERdateOfBirth date not null,
    primary key (teacherID));

create table COURSE(courseID integer not null,
    COURSEName varchar(50) not null,
    COURSEAssignedTeacher integer not null,
    COURSEHoursPerWeek integer not null,
    primary key (courseID),
    foreign key (COURSEAssignedTeacher) references TEACHER (teacherID));

create table SCHEDULE(SCHEDULECourseID integer not null,
    day varchar(50) not null,
    starts time not null,
    ends time not null,
    foreign key (SCHEDULECourseID) references COURSE (courseID));

create table NOTES(NOTESStudentID integer not null,
    NOTESCourse integer not null,
    firstNote integer not null,
    secondNote integer not null,
    thirdNote integer not null,
    finalNote real not null,
    foreign key (NOTESStudentID) references STUDENT (studentID),
    foreign key (NOTESCourse) references COURSE (courseID));





insert into STUDENT values
(1,'Juan','Perez','1993-02-04'),
(2,'Pedro','Ramirez','1992-01-31'),
(3,'Carlos','Garcia','1981-11-23'),
(4,'Alfredo','Di Stefano','1926-07-04'),
(5,'El Pato','Donald','1934-06-09'),
(6,'Carlos','Bilardo','1939-03-19'),
(7,'Roger','Federer','1981-08-08'),
(8,'Juan Martin','Del Potro','1988-09-23'),
(9,'Oscar','Gomez','1982-10-11'),
(10,'Diego Armando','Maradona','1960-10-30'),
(11,'Pablo','Marmol','1932-12-14'),
(12,'Carlos','Gonzalez','1992-06-13'),
(13,'Camilo','Lopez','1996-09-22'),
(14,'Gonzalo','Ruiz','1995-01-14'),
(15,'Tomas','Rodriguez','1991-09-09'),
(16,'Marcos','Torrejon','1978-11-22'),
(17,'Mauro','Paez','1987-10-30'),
(18,'Gaston','Gomez','1988-03-24'),
(19,'Ignacio','Acosta','1991-09-18'),
(20,'Luis','Suarez','1985-05-13'),
(21,'Luis Cesar','Menotti','1938-10-22'),
(22,'Walter','Cano','1980-09-22'),
(23,'Adrian','Paenza','1949-05-09'),
(24,'Andres','Iniesta','1984-05-11'),
(25,'Javier','Mascherano','1984-06-08'),
(26,'Marcelo','Bielsa','1955-07-21'),
(27,'Carlos','Tevez','1984-02-05'),
(28,'Gaston','Turus','1980-05-27'),
(29,'Juan Carlos','Olave','1976-02-21'),
(30,'Mario','Kempes','1954-07-15');





insert into TEACHER values
(1,'Edsger','Dijkstra','1930-05-11'),
(2,'George','Boole','1815-11-02'),
(3,'Alan','Turing','1912-06-23');



insert into COURSE values
(1,'Algorithms and Data Structures',1,8),
(2,'Number Theory',2,6),
(3,'Cryptography',3,6);




insert into NOTES values
(1,1,3,7,3,4.33),
(2,1,8,9,9,8.33),
(3,1,1,10,7,6.0),
(4,1,5,6,7,6.0),
(5,1,6,7,8,7.0),
(6,1,5,9,9,7.66),
(7,1,7,7,7,7.0),
(8,1,8,8,8,8.0),
(9,1,9,9,9,9.0),
(10,1,10,10,10,10.0),
(11,2,4,5,3,4.0),
(12,2,7,5,9,7.0),
(13,2,5,5,1,3.66),
(14,2,9,8,7,8.0),
(15,2,6,5,4,5.0),
(16,2,9,1,9,6.33),
(17,2,7,7,7,7.0),
(18,2,6,6,6,6.0),
(19,2,9,2,2,4.33),
(20,2,4,4,3,3.66),
(21,3,5,6,7,6.0),
(22,3,3,1,1,1.66),
(23,3,9,8,7,8.0),
(24,3,7,8,9,8.0),
(25,3,9,8,8,8.33),
(26,3,1,9,9,6.33),
(27,3,10,6,8,8.0),
(28,3,9,2,10,7.0),
(29,3,8,4,4,5.33),
(30,3,3,7,7,5.66);


insert into SCHEDULE values
(1,'Monday','09:00:00','13:00:00'),
(1,'Wednesday','09:00:00','13:00:00'),
(2,'Tuesday','09:00:00','13:00:00'),
(2,'Thursday','09:00:00','11:00:00'),
(3,'Thursday','11:00:00','13:00:00'),
(3,'Friday','09:00:00','13:00:00');

--Practice 3

select COURSEName,
    TEACHERLastName,
    TEACHERFirstName,
    STUDENTLastName,
    STUDENTFirstName
    from COURSE
    join NOTES on courseID=NOTESCourse 
    join STUDENT on studentID=NOTESStudentID 
    join TEACHER on teacherID=COURSEAssignedTeacher 
    where COURSEName= 'Algorithms and Data Structures' 
    order by STUDENTLastName;

-- Practice 4

select (select count(*) from NOTES
    join COURSE on courseID=NOTESCourse 
    where courseID=1 and finalNOTE >= 4) /
    (select count(*) from NOTES
    join COURSE on courseID=NOTESCourse
    where courseID=1) * 100
    as PercentageOfStudentsApproved;

-- Practice 5

select  TEACHERLastName,
        TEACHERFirstName,
        day,
        starts,
        ends,
        COURSEName
    from TEACHER
    join COURSE on COURSEAssignedTeacher=teacherID
    join SCHEDULE on SCHEDULECourseID=courseID
    where teacherID=1;

select  TEACHERLastName,
        TEACHERFirstName,
        day,
        starts,
        ends,
        COURSEName
    from TEACHER
    join COURSE on COURSEAssignedTeacher=teacherID
    join SCHEDULE on SCHEDULECourseID=courseID
    where teacherID=2;

select  TEACHERLastName,
        TEACHERFirstName,
        day,
        starts,
        ends,
        COURSEName
    from TEACHER
    join COURSE on COURSEAssignedTeacher=teacherID
    join SCHEDULE on SCHEDULECourseID=courseID
    where teacherID=3;









