
create database highschool;

create table STUDENT (regNumber integer not null,
    firstName varchar(50) not null,
    lastName varchar(50) not null,
    dateOfBirth date not null,
    primary key (regNumber));

create table TEACHER (teacherID integer not null,
    firstName varchar(50) not null,
    lastName varchar(50) not null,
    dateOfBirth date not null,
    primary key (teacherID));

create table COURSE(courseID integer not null,
    name varchar(50) not null,
    assignedTeacher integer not null,
    hoursByWeek integer not null,
    scheduleTime varchar(70) not null,
    primary key (courseID),
    foreign key (assignedTeacher) references TEACHER (teacherID));

create table NOTES(studentID integer not null,
    course integer not null,
    firstNote integer not null,
    secondNote integer not null,
    thirdNote integer not null,
    finalNote real not null,
    primary key(studentID, course)
    foreign key (studentID) references STUDENT (regNumber),
    foreign key (course) references COURSE (courseID));


insert into STUDENT (regNumber, firstName, lastName, dateOfBirth) values
('1','Juan','Perez','1993-02-04'),
('2','Pedro','Ramirez','1992-01-31'),
('3','Carlos','Garcia','1981-11-23'),
('4','Alfredo','Di Stefano','1926-07-04'),
('5','El Pato','Donald','1934-06-09'),
('6','Carlos','Bilardo','1939-03-19'),
('7','Roger','Federer','1981-08-1981'),
('8','Juan Martin','Del Potro','1988-09-23'),
('9','Oscar','Gomez','1982-10-11'),
('10','Diego Armando','Maradona','1960-10-30'),
('11','Pablo','Marmol','1932-12-14'),
('12','Carlos','Gonzalez','1992-06-13'),
('13','Camilo','Lopez','1996-09-22'),
('14','Gonzalo','Ruiz','1995-01-14'),
('15','Tomas','Rodriguez','1991-09-09'),
('16','Marcos','Torrejon','1978-11-22'),
('17','Mauro','Paez','1987-10-30'),
('18','Gaston','Gomez','1988-03-24'),
('19','Ignacio','Acosta','1991-09-18'),
('20','Luis','Suarez','1985-05-13'),
('21','Luis Cesar','Menotti','1938-10-22');
('22','Walter','Cano','1980-09-22'),
('23','Adrian','Paenza','1949-05-09'),
('24','Andres','Iniesta','1984-05-11'),
('25','Javier','Mascherano','1984-06-08'),
('26','Marcelo','Bielsa','1955-07-21');
('27','Carlos','Tevez','1984-02-05'),
('28','Gaston','Turus','1980-05-27'),
('29','Juan Carlos','Olave','1976-02-21'),
('30','Mario','Kempes','1954-07-15');





insert into TEACHERS (teacherID, firstName, lastName, dateOfBirth) values
('1','Edsger','Dijkstra','1930-05-11'),
('2','George','Boole','1815-11-02'),
('3','Alan','Turing','1912-06-23');



insert into COURSE (courseID, name, assignedTeacher, hoursByWeek, scheduleTime) values
('1','Algoritmos y Estructuras de Datos','1','8','Monday 9:00hs to 13:00hs, Wednesday 9:00hs to 13:00hs'),
('2','Matematica Discreta II','2','6','Tuesday 14:00hs to 18:00hs, Thursday 14:00hs to 16:00hs'),
('3','Lenguajes Formales y Computabilidad','3','6','Wednesday 14:00hs to 18:00hs, Friday 9:00hs to 11:00hs');




insert into NOTES (studentID, course, firstNote, secondNote, thirdNote, finalNote) values
('1','1','3','7','3','4.33'),
('2','1','8','9','9','8.33'),
('3','1','1','10','7','6.0'),
('4','1','5','6','7','6.0'),
('5','1','6','7','8','7.0'),
('6','1','5','9','9','7.66'),
('7','1','7','7','7','7.0'),
('8','1','8','8','8','8.0'),
('9','1','9','9','9','9.0'),
('10','1','10','10','10','10.0'),
('11','2','4','5','3','4.0'),
('12','2','7','5','9','7.0'),
('13','2','5','5','1','3.66'),
('14','2','9','8','7','8.0'),
('15','2','6','5','4','5.0'),
('16','2','9','1','9','6.33'),
('17','2','7','7','7','7.0'),
('18','2','6','6','6','6.0'),
('19','2','9','2','2','4.33'),
('20','2','4','4','3','3.66'),
('21','3','5','6','7','6.0'),
('22','3','3','1','1','1.66'),
('23','3','9','8','7','8.0'),
('24','3','7','8','9','8.0'),
('25','3','9','8','8','8.33'),
('26','3','1','9','9','6.33'),
('27','3','10','6','8','8.0'),
('28','3','9','2','10','7.0'),
('29','3','8','4','4','5.33'),
('30','3','3','7','7','5.66');











