package cr424s.dangmaihuyentrang.studentmanager;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private String hoTen, maSV, soDienThoai, email, idKhoa;
    private String ngaySinh, gioiTinh, soThich;

    // Constructor sửa thứ tự để khớp với data cứng
    public SinhVien(String hoTen, String maSV, String soDienThoai, String email,
                    String ngaySinh, String gioiTinh, String soThich, String idKhoa) {
        this.hoTen = hoTen;
        this.maSV = maSV;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.soThich = soThich;
        this.idKhoa = idKhoa;
    }

    public SinhVien() {
    }

    // Getter và Setter
    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public String getMaSV() { return maSV; }
    public void setMaSV(String maSV) { this.maSV = maSV; }
    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getIdKhoa() { return idKhoa; }
    public void setIdKhoa(String idKhoa) { this.idKhoa = idKhoa; }
    public String getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(String ngaySinh) { this.ngaySinh = ngaySinh; }
    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }
    public String getSoThich() { return soThich; }
    public void setSoThich(String soThich) { this.soThich = soThich; }

    @Override
    public String toString() {
        return "SinhVien{" +
                "hoTen='" + hoTen + '\'' +
                ", maSV='" + maSV + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", email='" + email + '\'' +
                ", ngaySinh='" + ngaySinh + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", soThich='" + soThich + '\'' +
                ", idKhoa='" + idKhoa + '\'' +
                '}';
    }
}
