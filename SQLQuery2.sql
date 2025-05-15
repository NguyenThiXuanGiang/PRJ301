USE master;
GO

-- Kiem tra va xoa database cu neu ton tai
IF EXISTS (SELECT * FROM sys.databases WHERE name = 'Workshop_2')
BEGIN
    ALTER DATABASE Workshop_2 SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE Workshop_2;
END
GO

-- Tao co so du lieu voi ho tro Unicode cho tieng Viet
CREATE DATABASE Workshop_2
COLLATE SQL_Latin1_General_CP1254_CI_AS;
GO

-- Su dung co so du lieu
USE Workshop_2;
GO

-- Xoa bang neu da ton tai
IF EXISTS (SELECT * FROM sys.tables WHERE name = 'products')
BEGIN
    DROP TABLE products;
END
GO

CREATE TABLE tblUsers (
    username VARCHAR(50) PRIMARY KEY, 
    Name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    Role VARCHAR(20) NOT NULL CHECK (Role IN ('Instructor', 'Student'))
);

CREATE TABLE tblExamCategories (
    category_id INT PRIMARY KEY IDENTITY(1,1),
    category_name VARCHAR(50) NOT NULL,
    description TEXT
);

CREATE TABLE tblExams (
    exam_id INT PRIMARY KEY IDENTITY(1,1),
    exam_title VARCHAR(100) NOT NULL,
    Subject VARCHAR(50) NOT NULL,
    category_id INT FOREIGN KEY REFERENCES tblExamCategories(category_id),
    total_marks INT NOT NULL,
    Duration INT NOT NULL
);

CREATE TABLE tblQuestions (
    question_id INT PRIMARY KEY IDENTITY(1,1),
    exam_id INT FOREIGN KEY REFERENCES tblExams(exam_id),
    question_text TEXT NOT NULL,
    option_a VARCHAR(100) NOT NULL,
    option_b VARCHAR(100) NOT NULL,
    option_c VARCHAR(100) NOT NULL,
    option_d VARCHAR(100) NOT NULL,
    correct_option CHAR(1) NOT NULL CHECK (correct_option IN ('A', 'B', 'C', 'D'))
);
