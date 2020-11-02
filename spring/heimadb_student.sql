create table student
(
    sid      int auto_increment
        primary key,
    NAME     varchar(20) null,
    age      int         null,
    birthday date        null
);

INSERT INTO heimadb.student (sid, NAME, age, birthday) VALUES (1, '张三', 30, '1999-09-23');
INSERT INTO heimadb.student (sid, NAME, age, birthday) VALUES (2, '李四', 24, '1998-08-10');
INSERT INTO heimadb.student (sid, NAME, age, birthday) VALUES (3, '王五', 25, '1996-06-06');
INSERT INTO heimadb.student (sid, NAME, age, birthday) VALUES (4, '赵六', 26, '1994-10-20');