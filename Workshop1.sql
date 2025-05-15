
USE master;
GO

-- Xóa database nếu đã tồn tại
IF EXISTS (SELECT * FROM sys.databases WHERE name = 'Workshop1')
BEGIN
    ALTER DATABASE Workshop1 SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE Workshop1;
END
GO

-- Tạo database với hỗ trợ Unicode tiếng Việt
CREATE DATABASE Workshop1
COLLATE Vietnamese_CI_AS;
GO

-- Sử dụng database mới tạo
USE Workshop1;
GO

-- Bảng Users: Lưu thông tin người dùng
CREATE TABLE tblUsers (
    Username NVARCHAR(50) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Password NVARCHAR(255) NOT NULL,  -- Nên mã hóa mật khẩu
    Role NVARCHAR(20) NOT NULL CHECK (Role IN ('Founder', 'Team Member'))
);
GO

-- Bảng Startup Projects: Lưu thông tin dự án
CREATE TABLE tblStartupProjects (
    project_id INT PRIMARY KEY IDENTITY(1,1),
    project_name NVARCHAR(100) NOT NULL,
    Description NVARCHAR(MAX),
    Status NVARCHAR(20) NOT NULL CHECK (Status IN ('Ideation', 'Development', 'Launch', 'Scaling')),
    estimated_launch DATE NULL  -- Cho phép NULL nếu chưa có ngày dự kiến
);
GO

-- Bảng Project Stages: Lưu các giai đoạn của dự án
CREATE TABLE tblProjectStages (
    stage_id INT PRIMARY KEY IDENTITY(1,1),
    project_id INT NOT NULL,
    stage_name NVARCHAR(100) NOT NULL,
    start_date DATE,
    end_date DATE,
    status NVARCHAR(20) NOT NULL CHECK (status IN ('Not Started', 'In Progress', 'Completed')),
    FOREIGN KEY (project_id) REFERENCES tblStartupProjects(project_id) ON DELETE CASCADE
);
GO

-- Bảng Team Assignments: Quản lý thành viên tham gia dự án
CREATE TABLE tblTeamAssignments (
    assignment_id INT PRIMARY KEY IDENTITY(1,1),
    project_id INT NOT NULL,
    username NVARCHAR(50) NOT NULL,
    FOREIGN KEY (project_id) REFERENCES tblStartupProjects(project_id) ON DELETE CASCADE,
    FOREIGN KEY (username) REFERENCES tblUsers(Username) ON DELETE CASCADE
);
GO

-- Bảng Progress Updates: Lưu thông tin cập nhật tiến độ
CREATE TABLE tblProgressUpdates (
    update_id INT PRIMARY KEY IDENTITY(1,1),
    project_id INT NOT NULL,
    username NVARCHAR(50) NOT NULL,
    update_date DATE NOT NULL,
    progress NVARCHAR(MAX) NOT NULL,
    status NVARCHAR(20) NOT NULL CHECK (status IN ('On Track', 'At Risk', 'Delayed')),
    FOREIGN KEY (project_id) REFERENCES tblStartupProjects(project_id) ON DELETE CASCADE,
    FOREIGN KEY (username) REFERENCES tblUsers(Username) ON DELETE CASCADE
);
GO

-- Thêm dữ liệu mẫu vào bảng Users
INSERT INTO tblUsers (Username, Name, Password, Role) VALUES
(N'founder1', N'Alice Johnson', N'123456', N'Founder'),
(N'team_member1', N'Bob Smith', N'123456', N'Team Member'),
(N'team_member2', N'Charlie Brown', N'123456', N'Team Member'),
(N'admin', N'Giang', N'123456', N'Team Member');
GO

-- Thêm dữ liệu mẫu vào bảng Startup Projects
INSERT INTO tblStartupProjects (project_name, Description, Status, estimated_launch) VALUES
(N'Project Alpha', N'Dự án này phát triển ứng dụng quản lý công việc.', N'Ideation', '2024-06-01'),
(N'Project Beta', N'Một dự án về nền tảng thương mại điện tử.', N'Development', '2024-12-15');
GO

-- Thêm dữ liệu mẫu vào bảng Project Stages
INSERT INTO tblProjectStages (project_id, stage_name, start_date, end_date, status) VALUES
(1, N'Phát triển ý tưởng', '2024-01-01', '2024-02-01', N'Completed'),
(1, N'Thiết kế nguyên mẫu', '2024-02-02', '2024-03-01', N'In Progress'),
(2, N'Khảo sát thị trường', '2024-03-01', '2024-04-01', N'Completed'),
(2, N'Phát triển sản phẩm', '2024-04-02', NULL, N'In Progress');
GO

-- Thêm dữ liệu mẫu vào bảng Team Assignments
INSERT INTO tblTeamAssignments (project_id, username) VALUES
(1, N'founder1'),
(1, N'team_member1'),
(2, N'founder1'),
(2, N'team_member2');
GO

-- Thêm dữ liệu mẫu vào bảng Progress Updates
INSERT INTO tblProgressUpdates (project_id, username, update_date, progress, status) VALUES
(1, N'team_member1', '2024-01-15', N'Hoàn thành phát triển ý tưởng.', N'On Track'),
(1, N'team_member1', '2024-02-15', N'Đang thiết kế nguyên mẫu.', N'At Risk'),
(2, N'team_member2', '2024-04-15', N'Khảo sát thị trường xong, bắt đầu phát triển sản phẩm.', N'On Track');
GO
