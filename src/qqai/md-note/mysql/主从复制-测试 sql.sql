CREATE TABLE mytbl2
(
    id   INT,
    NAME VARCHAR(200),
    age  INT,
    dept INT
);

INSERT INTO mytbl2
VALUES (1, 'zhang3', 33, 101);
INSERT INTO mytbl2
VALUES (2, 'li4', 34, 101);
INSERT INTO mytbl2
VALUES (3, 'wang5', 34, 102);
INSERT INTO mytbl2
VALUES (4, 'zhao6', 34, 102);
INSERT INTO mytbl2
VALUES (5, 'tian7', 36, 102);


SELECT *
FROM `mytbl2` m
         INNER JOIN (
    SELECT #NAME,
           dept,
           MAX(age) maxage
    FROM mytbl2
    GROUP BY dept
) ab
                    ON ab.dept = m.dept AND m.age = ab.maxage;

#查询所有的存储引擎 MyISAM和 InnoDB的区别
SHOW ENGINES

CREATE TABLE `t_dept`
(
    `id`       INT(11) NOT NULL AUTO_INCREMENT,
    `deptName` VARCHAR(30) DEFAULT NULL,
    `address`  VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE `t_emp`
(
    `id`     INT(11) NOT NULL AUTO_INCREMENT,
    `name`   VARCHAR(20) DEFAULT NULL,
    `age`    INT(3)      DEFAULT NULL,
    `deptId` INT(11)     DEFAULT NULL,
    `empno`  int     not NULL,
    PRIMARY KEY (`id`),
    KEY `idx_dept_id1` (`deptId`)
#CONSTRAINT `fk_dept_id` FOREIGN KEY (`deptId`)REFERENCES `t_dept` (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

INSERT INTO t_dept(deptName, address)
VALUES ('华山', '华山');
INSERT INTO t_dept(deptName, address)
VALUES ('丐帮', '洛阳');
INSERT INTO t_dept(deptName, address)
VALUES ('峨眉', '峨眉山');
INSERT INTO t_dept(deptName, address)
VALUES ('武当', '武当山');
INSERT INTO t_dept(deptName, address)
VALUES ('明教', '光明顶');
INSERT INTO t_dept(deptName, address)
VALUES ('少林', '少林寺');

INSERT INTO t_emp(NAME, age, deptId, empno)
VALUES ('风清扬', 90, 1, 100001);
INSERT INTO t_emp(NAME, age, deptId, empno)
VALUES ('岳不群', 50, 1, 100002);
INSERT INTO t_emp(NAME, age, deptId, empno)
VALUES ('令狐冲', 24, 1, 100003);
INSERT INTO t_emp(NAME, age, deptId, empno)
VALUES ('洪七公', 70, 2, 100004);
INSERT INTO t_emp(NAME, age, deptId, empno)
VALUES ('乔峰', 35, 2, 100005);
INSERT INTO t_emp(NAME, age, deptId, empno)
VALUES ("周芷若", 20, 3, 100007);
INSERT INTO t_emp(NAME, age, deptId, empno)
VALUES ('灭绝师太', 70, 3, 100006);

INSERT INTO t_emp(NAME, age, deptId, empno)
VALUES ('张三丰', 100, 4, 100008);
INSERT INTO t_emp(NAME, age, deptId, empno)
VALUES ('张无忌', 25, 5, 100009);
INSERT INTO t_emp(NAME, age, deptId, empno)
VALUES ('韦小宝', 18, null, 100010);

ALTER TABLE `t_dept`
    ADD CEO INT(11);
UPDATE t_dept
SET CEO=2
WHERE id = 1;
UPDATE t_dept
SET cEO=4
WHERE id = 2;
UPDATE t_dept
SET cEO=6
WHERE id = 3;
UPDATE t_dept
SET CEO=8
WHERE id = 4;
UPDATE t_dept
SET cEO=9
WHERE id = 5;

select *
from t_dept;

SELECT *
FROM t_emp;

select *
from `t_emp` a
         inner join `t_dept` d on a.deptId = d.id

SELECT *
FROM `t_emp` a
         left JOIN `t_dept` d ON a.deptId = d.id

SELECT *
FROM `t_emp` a
         LEFT JOIN `t_dept` d ON a.deptId = d.id
where d.id is NULL

SELECT *
FROM `t_dept` d
         LEFT JOIN `t_emp` a ON a.deptId = d.id
WHERE a.id is null

SELECT a.*, d.*
FROM `t_emp` a
         LEFT JOIN `t_dept` d ON a.deptId = d.id
union
#union 用于合并两个或多个查询语句的结果集，并消去表中任何重复行。查询语句要求 列的数量相同，列的数据类型相似，列的顺序相同
SELECT a.*, d.*
FROM `t_dept` d
         LEFT JOIN `t_emp` a ON a.deptId = d.id
WHERE a.id IS NULL


SELECT a.*, d.*
FROM `t_emp` a
         LEFT JOIN `t_dept` d ON a.deptId = d.id
UNION ALL
#union 用于合并两个或多个查询语句的结果集，并消去表中任何重复行。查询语句要求 列的数量相同，列的数据类型相似，列的顺序相同
SELECT a.*, d.*
FROM `t_dept` d
         LEFT JOIN `t_emp` a ON a.deptId = d.id
WHERE a.id IS NULL


SELECT *
FROM `t_emp` a
         LEFT JOIN `t_dept` d ON a.deptId = d.id
WHERE d.id IS NULL
union all
SELECT *
FROM `t_dept` d
         LEFT JOIN `t_emp` a ON a.deptId = d.id
WHERE a.id IS NULL


select *
from `t_emp` a
         inner join `t_dept` b on a.id = b.ceo


SELECT avg(a.age)
FROM `t_emp` a
         INNER JOIN `t_dept` b ON a.id = b.ceo

select c.name, ab.name
from `t_emp` c
         left join
     (SELECT b.id, a.name
      FROM `t_emp` a
               INNER JOIN `t_dept` b ON a.id = b.ceo) ab
     on c.deptId = ab.id


select a.name, c.name ceoname
from `t_emp` a
         left join `t_dept` b on a.deptId = b.id
         left join `t_emp` c on c.id = b.ceo


#查看索引
show index from `t_emp`

#创建唯一索引
create unique index idx_empno on `t_emp` (empno)

#分析当前sql
explain
SELECT a.name, c.name ceoname
FROM `t_emp` a
         LEFT JOIN `t_dept` b ON a.deptId = b.id
         LEFT JOIN `t_emp` c ON c.id = b.ceo


EXPLAIN
SELECT c.name, ab.name
FROM `t_emp` c
         LEFT JOIN
     (SELECT b.id, a.name
      FROM `t_emp` a
               INNER JOIN `t_dept` b ON a.id = b.ceo) ab
     ON c.deptId = ab.id

# id  select_type  table   type    possible_keys  key      key_len  ref              rows  Extra   


CREATE TABLE `dept`
(
    `id`       INT(11) NOT NULL AUTO_INCREMENT,
    `deptName` VARCHAR(30) DEFAULT NULL,
    `address`  VARCHAR(40) DEFAULT NULL,
    ceo        INT     NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;


CREATE TABLE `emp`
(
    `id`     INT(11) NOT NULL AUTO_INCREMENT,
    `empno`  INT     NOT NULL,
    `name`   VARCHAR(20) DEFAULT NULL,
    `age`    INT(3)      DEFAULT NULL,
    `deptId` INT(11)     DEFAULT NULL,
    PRIMARY KEY (`id`)
    #CONSTRAINT `fk_dept_id` FOREIGN KEY (`deptId`) REFERENCES `t_dept` (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;


#开启自定义函数的功能
set global log_bin_trust_function_creators = 1;
SHOW VARIABLES LIKE 'log_bin_trust_function_creators';


DELIMITER $$ #修改结束符FUNCTION后面是参数RETURNS后面是返回值
CREATE FUNCTION rand_string(n INT) RETURNS VARCHAR(255) #创建一个函数
BEGIN
    #开始  DECLARE声明一个变量
    DECLARE chars_str VARCHAR(100) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFJHIJKLMNOPQRSTUVWXYZ';
    DECLARE return_str VARCHAR(255) DEFAULT '';
    DECLARE i INT DEFAULT 0;
    WHILE i < n
        DO
            #RAND()*52  从0-51随机获取一个值  FLOOR()取整
            SET return_str = CONCAT(return_str, SUBSTRING(chars_str, FLOOR(1 + RAND() * 52), 1));
            SET i = i + 1;
        END WHILE;
    RETURN return_str;
END $$

#函数
DELIMITER $$
CREATE FUNCTION rand_num(from_num INT, to_num INT) RETURNS INT(11)
BEGIN
    DECLARE i INT DEFAULT 0;
    SET i = FLOOR(from_num + RAND() * (to_num - from_num + 1));
    RETURN i;
END$$

#emp表中插入数据的存储过程
DELIMITER $$
CREATE PROCEDURE insert_emp(START INT, max_num INT)
BEGIN
    DECLARE i INT DEFAULT 0;
#set autocommit =0 把autocommit设置成0  
    SET autocommit = 0;
    REPEAT
        SET i = i + 1;
        INSERT INTO emp (empno, NAME, age, deptid)
        VALUES ((START + i), rand_string(6), rand_num(30, 50), rand_num(1, 10000));
    UNTIL i = max_num
        END REPEAT;
    COMMIT;
END$$

#执行存储过程，往dept表添加随机数据
DELIMITER $$
CREATE PROCEDURE `insert_dept`(max_num INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    SET autocommit = 0;
    REPEAT
        SET i = i + 1;
        INSERT INTO dept (deptname, address, ceo) VALUES (rand_string(8), rand_string(10), rand_num(1, 500000));
    UNTIL i = max_num
        END REPEAT;
    COMMIT;
END$$

DELIMITER ;

CALL insert_dept(10000);

CALL insert_emp(100000, 500000);


#查看索引
SELECT index_name
FROM information_schema.STATISTICS
WHERE table_name = 't_emp'
  AND table_schema = 'mydb'
  AND index_name <> 'PRIMARY'
  AND seq_in_index = 1


CREATE
X
DROP INDEX idx_xxx ON emp
#1 查出该表有哪些索引，索引名-->集合
SHOW INDEX FROM emp
#元数据：meta DATA  描述数据的数据
SELECT index_name
FROM information_schema.STATISTICS
WHERE table_name = 't_emp'
  AND table_schema = 'mydb'
  AND index_name <> 'PRIMARY'
  AND seq_in_index = 1
#2 如何循环集合
    CURSOR 游标
FETCH xxx
INTO xxx
#3 如何让mysql执行一个字符串
PREPARE 预编译 XXX
EXECUTE
    CALL proc_drop_index ('mydb','t_emp');


#对于emp表的索引印象执行效率的测试   删除所有索引（除主键索引）
CALL proc_drop_index('mydb', 'emp');
#查看所有索引
SHOW INDEX FROM emp
#执行效率
explain
select sql_no_cache *
from emp
where emp.age = 30
#创建索引
create index idx_age on emp (age)
#执行
EXPLAIN
SELECT SQL_NO_CACHE *
FROM emp
WHERE emp.age = 30
  and deptid = 4
#创建索引
create index idx_age_deptid on emp (age, deptid)
#执行
EXPLAIN
SELECT SQL_NO_CACHE *
FROM emp
WHERE emp.age = 30
  AND deptid = 4
  and emp.name = 'abcd'

#模糊匹配的索引优化  模糊匹配的时候首字母不能确定索引还是失效的
explain
SELECT SQL_NO_CACHE *
from emp
where emp.name like 'abc%'
#这里的where后面用了Left函数索引在这里就不生效了索引这里还是特别慢
EXPLAIN
SELECT SQL_NO_CACHE *
FROM emp
WHERE left(emp.name, 3) = 'abc'

CREATE INDEX idx_name ON emp (NAME)

EXPLAIN
SELECT SQL_NO_CACHE *
FROM emp
WHERE emp.age = 30
  AND emp.deptId > 20
  AND emp.name = 'abc';


EXPLAIN
SELECT SQL_NO_CACHE *
FROM emp
WHERE emp.name <> 'abc'

#类型不匹配  mysql会做类型转换 但是这里索引不会索引会失效
EXPLAIN
SELECT SQL_NO_CACHE *
FROM emp
WHERE emp.name = 123


CALL proc_drop_index('mydb', 'emp');

#排序的索引优化   排序字段必须和索引字段的顺序对应  否则索引无效
Explain
Select *
from emp
where age = 45
order by deptid
create index idx_age_deptid_name on emp (age, deptid, name)
#字段排序的方向示反的也会排序
EXPLAIN
SELECT *
FROM emp
WHERE age = 45
ORDER BY deptid asc, NAME desc
#有个字段没有在索引里 也会排序
EXPLAIN
SELECT *
FROM emp
WHERE age = 45
ORDER BY deptid, empno
#排序字段是反的也会排序
EXPLAIN
SELECT *
FROM emp
WHERE age = 45
ORDER BY name, deptid


#慢查询日志只对当前数据库生效，
SHOW VARIABLES LIKE '%slow_query_log%';

#如果MySQL重启后则会失效。
set GLOBAL slow_query_log = 1;

#设置超时时间
SHOW VARIABLES LIKE 'long_query_time%';
set long_query_time = 2



GRANT REPLICATION SLAVE ON *.* TO 'qqai-log-bin'@'%' IDENTIFIED BY '123456';


SHOW MASTER STATUS;

show variables like "log_%";


