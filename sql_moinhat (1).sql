CREATE DATABASE DB_QuanCaPhe
use DB_QuanCaPhe
-- Bảng Danh Muc
CREATE TABLE DanhMuc (
    DanhMucID INT IDENTITY PRIMARY KEY,
    TenDanhMuc NVARCHAR(255) NOT NULL
);

-- Bảng Mon
CREATE TABLE Mon (
    MonID INT IDENTITY PRIMARY KEY,
    TenMon NVARCHAR(255) NOT NULL,
    MoTa NVARCHAR(MAX),
    Gia MONEY NOT NULL,
    DanhMucID INT,
	HinhAnh varchar(255),
	
    FOREIGN KEY (DanhMucID) REFERENCES DanhMuc(DanhMucID)
);
-- Bảng KhoNguyenLieu
CREATE TABLE KhoNguyenLieu (
    NguyenLieuID INT IDENTITY PRIMARY KEY,
    TenNguyenLieu NVARCHAR(255) NOT NULL,
    DonVi NVARCHAR(50),
    GiaTheoDonVi MONEY,
    SoLuongTon FLOAT,
);

CREATE TABLE Mon_NguyenLieu(
	MonID Int,
	NguyenLieuID Int,
	SoLuongSuDungNguyenLieu float,
	CONSTRAINT pk_Mon_NguyenLieu PRIMARY KEY (MonID,NguyenLieuID),
	CONSTRAINT fk_1 foreign KEY (MonID) references Mon(MonID),
	CONSTRAINT fk_2 foreign KEY (NguyenLieuID) references KhoNguyenLieu(NguyenLieuID)


	

);


-- Bảng Ban
CREATE TABLE Ban (
    BanID INT IDENTITY PRIMARY KEY,
    TenBan NVARCHAR(255) NOT NULL,
    SoGhe INT,
    TrangThai NVARCHAR(50)
);

-- Bảng NhanVien
CREATE TABLE NhanVien (
    NhanVienID INT IDENTITY PRIMARY KEY,
    HoNV NVARCHAR(255),
    TenNV NVARCHAR(255),
    ViTri NVARCHAR(255),
    Luong MONEY
);

-- Bảng DatHang
CREATE TABLE DatHang (
    DatHangID INT IDENTITY PRIMARY KEY,
    BanID INT,
    NhanVienID INT,
    NgayDat DATETIME,
    GhiChu nvarchar(255),
    TongGia MONEY DEFAULT 0,
    FOREIGN KEY (BanID) REFERENCES Ban(BanID) on delete cascade,
    FOREIGN KEY (NhanVienID) REFERENCES NhanVien(NhanVienID)
);


-- Bảng ChiTietDatHang
CREATE TABLE ChiTietDatHang (
    ChiTietDatHangID INT IDENTITY PRIMARY KEY,
    DatHangID INT,
    MonID INT,
    SoLuong INT,
    FOREIGN KEY (DatHangID) REFERENCES DatHang(DatHangID) on delete  cascade,
    FOREIGN KEY (MonID) REFERENCES Mon(MonID) on delete  cascade
);




-- Bảng NhaCungCap
CREATE TABLE NhaCungCap (
    NhaCungCapID INT IDENTITY PRIMARY KEY,
    TenNhaCungCap NVARCHAR(255) NOT NULL,
    ThongTinLienLac NVARCHAR(255)
);
CREATE TABLE NhapKho (
    NhapKhoID INT IDENTITY PRIMARY KEY,
	NhanVienID INT,
	TenNhanVien VARCHAR(255),
    NguyenLieuID INT,
	TenNguyenLieu VARCHAR(255),
    NhaCungCapID INT,
	TenNhaCungCap VARCHAR(255),
    SoLuongNhap INT,
    GiaNhap MONEY,
    NgayNhap DATETIME,
    FOREIGN KEY (NguyenLieuID) REFERENCES KhoNguyenLieu(NguyenLieuID),
    FOREIGN KEY (NhaCungCapID) REFERENCES NhaCungCap(NhaCungCapID),
	FOREIGN KEY (NhanVienID) REFERENCES NhanVien(NhanVienID)
);

-- Bảng XuatKho
CREATE TABLE XuatKho (
    XuatKhoID INT IDENTITY PRIMARY KEY,
	NhanVienID INT,
	TenNhanVien VARCHAR(255),
    NguyenLieuID INT,
	TenNguyenLieu VARCHAR(255),
    SoLuongXuat INT,
    NgayXuat DATETIME,
    FOREIGN KEY (NguyenLieuID) REFERENCES KhoNguyenLieu(NguyenLieuID),
	FOREIGN KEY (NhanVienID) REFERENCES NhanVien(NhanVienID)
);
-- Tài Khoản
CREATE TABLE TaiKhoan(
	NhanVienID INT,
	TenTaiKhoan VARCHAR(255)  PRIMARY KEY,
	MatKhau VARCHAR(255) NOT NULL,
	FOREIGN KEY (NhanVienID) REFERENCES NhanVien(NhanVienID)
);


INSERT INTO DanhMuc (TenDanhMuc) VALUES (N'Đồ ăn');
INSERT INTO DanhMuc (TenDanhMuc) VALUES (N'Thức uống nóng');
INSERT INTO DanhMuc (TenDanhMuc) VALUES (N'Thức uống đá');
INSERT INTO DanhMuc (TenDanhMuc) VALUES (N'Nước đóng chai');
INSERT INTO DanhMuc (TenDanhMuc) VALUES (N'Nước có ga');
INSERT INTO DanhMuc (TenDanhMuc) VALUES (N'Sinh tố');
INSERT INTO DanhMuc (TenDanhMuc) VALUES (N'Trà sữa');
select * from Mon
delete from Mon
--nhanvien
INSERT INTO NhanVien (HoNV, TenNV, ViTri, Luong)
VALUES (N'Trần Đình', N'Long', N'Quản lý', 2000.00);
INSERT INTO NhanVien (HoNV, TenNV, ViTri, Luong)
VALUES (N'Nguyễn Chí', N'Khanh', N'Nhân viên', 0.00);
INSERT INTO NhanVien (HoNV, TenNV, ViTri, Luong)
VALUES (N'Lương Khánh', N'Phong', N'Nhân viên', 0.00);
INSERT INTO NhanVien (HoNV, TenNV, ViTri, Luong)
VALUES (N'Trần Bảo', N'Nhi', N'Nhân viên', 0.00);
INSERT INTO NhanVien (HoNV, TenNV, ViTri, Luong)
VALUES (N'Lê Kiều Diễm', N'Hoàng', N'Nhân viên', 0.00);
--mon
-- Thức uống nóng
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Cà phê đen nóng', N'ly', 5000, 2);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Cà phê sữa nóng', N'ly', 7000, 2);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Sữa nóng', N'ly', 7000, 2);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Trà đường nóng', N'ly', 5000, 2);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Chanh muối nóng', N'ly', 5000, 2);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Milo nóng', N'ly', 5000, 2);

-- Thức uống đá
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID,HinhAnh) VALUES (N'Cà phê bạc xỉu đá', N'ly', 10000,3,'caphe_bacxiuda.jpg');
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Cà phê sữa đá', N'ly', 12000, 3);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Trà đá đường', N'ly', 10000, 3);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Đá chanh muối', N'ly', 10000, 3);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Đá chanh tươi', N'ly', 10000, 3);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Đá dâu', N'ly', 12000, 3);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Đá me', N'ly', 12000, 3);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Milo đá', N'ly', 12000, 3);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Nước cam ép', N'ly', 10000, 3);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Trà đào', N'ly', 10000, 3);

-- Sinh tố
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Sinh tố dâu', N'ly', 15000, 6);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Sinh tố bơ', N'ly', 15000, 6);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Sinh tố mãng cầu', N'ly', 15000, 6);

-- Nước có ga
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Pepsi', N'chai', 10000, 5);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Seven Up', N'chai', 10000, 5);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Mirinda xá xị', N'lon', 10000, 5);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Mirinda cam', N'lon', 10000, 5);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Mirinda đá me', N'lon', 10000, 5);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Mirinda soda kem', N'lon', 10000, 5);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Fanta xá xị', N'chai', 10000, 5);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Fanta cam', N'chai', 10000, 5);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Sting', N'chai', 10000, 5);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Number one', N'chai', 10000, 5);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Redbull', N'lon', 10000, 5);

-- Nước đóng chai
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'AQUAFINA', N'chai', 7000, 4);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Trà xanh không độ', N'chai', 10000, 4);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'C2', N'chai', 10000, 4);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Ô long', N'chai', 10000, 4);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Dr Thanh', N'chai', 12000, 4);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Twister cam ép', N'chai', 10000, 4);

-- Đồ ăn
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Đậu phộng rang', N'bịch', 10000, 1);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Bắp rang', N'bịch', 10000, 1);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Khoai tây chiên', N'phần', 30000, 1);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Mì cay bò', N'tô', 30000, 1);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Mì cay hải sản', N'tô', 30000, 1);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Mì cay thập cẩm', N'tô', 40000, 1);

-- Trà sữa
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Trà sữa truyền thống', N'ly', 15000, 7);
INSERT INTO Mon (TenMon, MoTa, Gia, DanhMucID) VALUES (N'Trà sữa thái', N'ly', 15000, 7);

--KhoNguyenLieu
INSERT INTO KhoNguyenLieu (TenNguyenLieu, DonVi, GiaTheoDonVi, SoLuongTon) VALUES (N'Cà phê', N'gói', 10000, 50);
INSERT INTO KhoNguyenLieu (TenNguyenLieu, DonVi, GiaTheoDonVi, SoLuongTon) VALUES (N'Sữa', N'lít', 30000, 100);
INSERT INTO KhoNguyenLieu (TenNguyenLieu, DonVi, GiaTheoDonVi, SoLuongTon) VALUES (N'Đường', N'kg', 15000, 200);
INSERT INTO KhoNguyenLieu (TenNguyenLieu, DonVi, GiaTheoDonVi, SoLuongTon) VALUES (N'Chanh', N'quả', 5000, 80);
INSERT INTO KhoNguyenLieu (TenNguyenLieu, DonVi, GiaTheoDonVi, SoLuongTon) VALUES (N'Trà', N'gói', 20000, 30);
INSERT INTO KhoNguyenLieu (TenNguyenLieu, DonVi, GiaTheoDonVi, SoLuongTon) VALUES (N'Milo', N'hộp', 25000, 40);
INSERT INTO KhoNguyenLieu (TenNguyenLieu, DonVi, GiaTheoDonVi, SoLuongTon) VALUES (N'Dâu', N'kg', 40000, 60);
INSERT INTO KhoNguyenLieu (TenNguyenLieu, DonVi, GiaTheoDonVi, SoLuongTon) VALUES (N'Bơ', N'kg', 50000, 70);
INSERT INTO KhoNguyenLieu (TenNguyenLieu, DonVi, GiaTheoDonVi, SoLuongTon) VALUES (N'Mãng cầu', N'kg', 35000, 55);
INSERT INTO KhoNguyenLieu (TenNguyenLieu, DonVi, GiaTheoDonVi, SoLuongTon) VALUES (N'Đá', N'kg', 2000, 500);
--NhaCungCap
INSERT INTO NhaCungCap  VALUES (N'Công ty ABC', N'123 Đường ABC, Thành phố XYZ, Điện thoại: 123456789');
INSERT INTO NhaCungCap  VALUES (N'Công ty XYZ', N'456 Đường XYZ, Thành phố ABC, Điện thoại: 987654321');
--Ban
INSERT INTO Ban (TenBan, SoGhe, TrangThai) VALUES (N'Bàn 1', 4, N'Còn trống');
INSERT INTO Ban (TenBan, SoGhe, TrangThai) VALUES (N'Bàn 2', 6, N'Còn trống');
INSERT INTO Ban (TenBan, SoGhe, TrangThai) VALUES (N'Bàn 3', 2, N'Còn trống');
INSERT INTO Ban (TenBan, SoGhe, TrangThai) VALUES (N'Bàn 4', 4, N'Còn trống');
INSERT INTO Ban (TenBan, SoGhe, TrangThai) VALUES (N'Bàn 5', 6, N'Còn trống');
INSERT INTO Ban (TenBan, SoGhe, TrangThai) VALUES (N'Bàn 6', 2, N'Còn trống');

--tài khoản
INSERT INTO TaiKhoan (NhanVienID, TenTaiKhoan, MatKhau) 
VALUES (1, 'admin', '1');
INSERT INTO TaiKhoan (NhanVienID, TenTaiKhoan, MatKhau) 
VALUES (2, 'nv2', '1');




select * from KhoNguyenLieu
select* from Mon
select * from Mon_NguyenLieu
select * from DanhMuc
select * from DatHang
select * from KhoNguyenLieu
select * from NhapKho


-- Thêm dữ liệu vào bảng NhậpKho
-- Lần 1
INSERT INTO NhapKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, NhaCungCapID, TenNhaCungCap, SoLuongNhap, GiaNhap, NgayNhap) 
VALUES (1, N'Trần Đình Long', 1, N'Cà phê', 1, N'Công ty ABC', 20, 9500, '2024-05-28');

-- Lần 2
INSERT INTO NhapKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, NhaCungCapID, TenNhaCungCap, SoLuongNhap, GiaNhap, NgayNhap) 
VALUES (1, N'Trần Đình Long', 2, N'Sữa', 1, N'Công ty ABC', 50, 28000, '2024-05-28');

-- Lần 3
INSERT INTO NhapKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, NhaCungCapID, TenNhaCungCap, SoLuongNhap, GiaNhap, NgayNhap) 
VALUES (1, N'Trần Đình Long', 3, N'Đường', 1, N'Công ty ABC', 100, 13500, '2024-05-28');

-- Lần 4
INSERT INTO NhapKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, NhaCungCapID, TenNhaCungCap, SoLuongNhap, GiaNhap, NgayNhap) 
VALUES (1, N'Trần Đình Long', 4, N'Chanh', 1, N'Công ty ABC', 30, 4000, '2024-05-28');

-- Lần 5
INSERT INTO NhapKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, NhaCungCapID, TenNhaCungCap, SoLuongNhap, GiaNhap, NgayNhap) 
VALUES (1, N'Trần Đình Long', 5, N'Trà', 1, N'Công ty ABC', 15, 19000, '2024-05-28');

-- Lần 6
INSERT INTO NhapKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, NhaCungCapID, TenNhaCungCap, SoLuongNhap, GiaNhap, NgayNhap) 
VALUES (1, N'Trần Đình Long', 6, N'Milo', 1, N'Công ty ABC', 10, 22500, '2024-05-28');

-- Lần 7
INSERT INTO NhapKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, NhaCungCapID, TenNhaCungCap, SoLuongNhap, GiaNhap, NgayNhap) 
VALUES (1, N'Trần Đình Long', 7, N'Dâu', 1, N'Công ty ABC', 25, 35000, '2024-05-28');

-- Lần 8
INSERT INTO NhapKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, NhaCungCapID, TenNhaCungCap, SoLuongNhap, GiaNhap, NgayNhap) 
VALUES (1, N'Trần Đình Long', 8, N'Bơ', 1, N'Công ty ABC', 20, 45000, '2024-05-28');

-- Lần 9
INSERT INTO NhapKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, NhaCungCapID, TenNhaCungCap, SoLuongNhap, GiaNhap, NgayNhap) 
VALUES (1, N'Trần Đình Long', 9, N'Mãng cầu', 1, N'Công ty ABC', 20, 31500, '2024-05-28');

-- Lần 10
INSERT INTO NhapKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, NhaCungCapID, TenNhaCungCap, SoLuongNhap, GiaNhap, NgayNhap) 
VALUES (1, N'Trần Đình Long', 10, N'Đá', 1, N'Công ty ABC', 100, 1500, '2024-05-28');


-- Thêm dữ liệu vào bảng XuatKho
-- Lần 1
INSERT INTO XuatKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, SoLuongXuat, NgayXuat) 
VALUES (1, N'Trần Đình Long', 1, N'Cà phê', 10, '2024-05-28');

-- Lần 2
INSERT INTO XuatKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, SoLuongXuat, NgayXuat) 
VALUES (1, N'Trần Đình Long', 2, N'Sữa', 20, '2024-05-28');

-- Lần 3
INSERT INTO XuatKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, SoLuongXuat, NgayXuat) 
VALUES (1, N'Trần Đình Long', 3, N'Đường', 30, '2024-05-28');

-- Lần 4
INSERT INTO XuatKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, SoLuongXuat, NgayXuat) 
VALUES (1, N'Trần Đình Long', 4, N'Chanh', 5, '2024-05-28');

-- Lần 5
INSERT INTO XuatKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, SoLuongXuat, NgayXuat) 
VALUES (1, N'Trần Đình Long', 5, N'Trà', 10, '2024-05-28');

-- Lần 6
INSERT INTO XuatKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, SoLuongXuat, NgayXuat) 
VALUES (1, N'Trần Đình Long', 6, N'Milo', 8, '2024-05-28');

-- Lần 7
INSERT INTO XuatKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, SoLuongXuat, NgayXuat) 
VALUES (1, N'Trần Đình Long', 7, N'Dâu', 15, '2024-05-28');

-- Lần 8
INSERT INTO XuatKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, SoLuongXuat, NgayXuat) 
VALUES (1, N'Trần Đình Long', 8, N'Bơ', 10, '2024-05-28');

-- Lần 9
INSERT INTO XuatKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, SoLuongXuat, NgayXuat) 
VALUES (1, N'Trần Đình Long', 9, N'Mãng cầu', 12, '2024-05-28');

-- Lần 10
INSERT INTO XuatKho (NhanVienID, TenNhanVien, NguyenLieuID, TenNguyenLieu, SoLuongXuat, NgayXuat) 
VALUES (1, N'Trần Đình Long', 10, N'Đá', 50, '2024-05-28');

 
CREATE TRIGGER trg_update_so_luong_sau_insert
ON NhapKho
AFTER INSERT
AS
BEGIN
    DECLARE @so_luong INT;

    -- Lấy số lượng của nguyên liệu từ bảng KhoNguyenLieu
    SELECT @so_luong = SoLuongTon
    FROM KhoNguyenLieu
    WHERE NguyenLieuID IN (SELECT NguyenLieuID FROM inserted);

    -- Cập nhật số lượng trong bảng KhoNguyenLieu
    UPDATE KhoNguyenLieu
    SET SoLuongTon = SoLuongTon - (SELECT SoLuongNhap FROM inserted WHERE KhoNguyenLieu.NguyenLieuID = inserted.NguyenLieuID)
    WHERE NguyenLieuID IN (SELECT NguyenLieuID FROM inserted);
END;

//CREATE TRIGGER UpdateKhoNguyenLieu
ON XuatKho
AFTER INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;
    
    DECLARE @NguyenLieuID INT;
    DECLARE @SoLuongXuat INT;
    
    -- Lấy thông tin về nguyên liệu và số lượng xuất từ bảng Inserted
    SELECT @NguyenLieuID = i.NguyenLieuID, @SoLuongXuat = i.SoLuongXuat
    FROM Inserted i;
    
    -- Cập nhật số lượng tồn trong bảng KhoNguyenLieu
    UPDATE KhoNguyenLieu
    SET SoLuongTon = SoLuongTon - @SoLuongXuat
    WHERE NguyenLieuID = @NguyenLieuID;
END;
