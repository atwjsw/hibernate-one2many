CREATE TABLE grade 
(
	gid INT PRIMARY KEY,
	gname VARCHAR(20) NOT NULL,
	gdesc VARCHAR(50)
);
CREATE TABLE student 
(
	sid INT PRIMARY KEY,
	sname VARCHAR(20) NOT NULL,
	sex CHAR(2),
	gid INT
);

ALTER TABLE student ADD CONSTRAINT fk_student_gid FOREIGN KEY (gid) REFERENCES grade(gid);