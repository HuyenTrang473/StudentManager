package cr424s.dangmaihuyentrang.studentmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import cr424s.dangmaihuyentrang.studentmanager.Department;
import cr424s.dangmaihuyentrang.studentmanager.SinhVien;

public class SinhVienDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "QLSinhVien";
    private static final int DB_VERSION = 1;

    public SinhVienDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String departmentTable = "CREATE TABLE Department (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "department_name TEXT NOT NULL," +
                "office_location TEXT," +
                "phone_number TEXT" +
                ")";

        String studentTable = "CREATE TABLE Student (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "student_code TEXT NOT NULL UNIQUE," +
                "full_name TEXT NOT NULL," +
                "birth_date TEXT," +
                "phone_number TEXT," +
                "email TEXT," +
                "gender TEXT," +
                "hobbies TEXT," +
                "department_id INTEGER," +
                "FOREIGN KEY (department_id) REFERENCES Department(id)" +
                ")";

        db.execSQL(departmentTable);
        db.execSQL(studentTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Student");
        db.execSQL("DROP TABLE IF EXISTS Department");
        onCreate(db);
    }

    // ================= Department CRUD =================
    public long themKhoa(Department dep){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("department_name", dep.getName());
        values.put("office_location", dep.getDescription());
        values.put("phone_number", dep.getPhone());
        long res = db.insert("Department", null, values);
        db.close();
        return res;
    }

    public boolean suaKhoa(Department dep){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("office_location", dep.getDescription());
        values.put("phone_number", dep.getPhone());
        int updated = db.update("Department", values, "department_name = ?", new String[]{dep.getName()});
        db.close();
        return updated > 0;
    }

    public boolean xoaKhoaById(int id){
        SQLiteDatabase db = getWritableDatabase();
        int deleted = db.delete("Department", "id = ?", new String[]{String.valueOf(id)});
        db.close();
        return deleted > 0;
    }

    public boolean xoaKhoaByName(String name){
        SQLiteDatabase db = getWritableDatabase();
        int deleted = db.delete("Department", "department_name = ?", new String[]{name});
        db.close();
        return deleted > 0;
    }

    public List<Department> getAllDepartment(){
        List<Department> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.query("Department", null, null, null, null, null, "department_name ASC");
        if(cs.moveToFirst()){
            do {
                Department dep = new Department(
                        cs.getString(cs.getColumnIndexOrThrow("department_name")),
                        cs.getString(cs.getColumnIndexOrThrow("office_location")),
                        cs.getString(cs.getColumnIndexOrThrow("phone_number"))
                );
                list.add(dep);
            } while (cs.moveToNext());
        }
        cs.close();
        return list;
    }

    public List<Department> getDepartmentByName(String name){
        List<Department> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.query("Department", null, "department_name LIKE ?", new String[]{"%" + name + "%"}, null, null, "department_name ASC");
        if(cs.moveToFirst()){
            do {
                Department dep = new Department(
                        cs.getString(cs.getColumnIndexOrThrow("department_name")),
                        cs.getString(cs.getColumnIndexOrThrow("office_location")),
                        cs.getString(cs.getColumnIndexOrThrow("phone_number"))
                );
                list.add(dep);
            } while(cs.moveToNext());
        }
        cs.close();
        return list;
    }

    // ================= Student CRUD =================
    public long themSinhVien(SinhVien sv){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("student_code", sv.getMaSV());
        values.put("full_name", sv.getHoTen());
        values.put("birth_date", sv.getNgaySinh());
        values.put("phone_number", sv.getSoDienThoai());
        values.put("email", sv.getEmail());
        values.put("gender", sv.getGioiTinh());
        values.put("hobbies", sv.getSoThich());
        values.put("department_id", sv.getIdKhoa());
        long res = db.insert("Student", null, values);
        db.close();
        return res;
    }

    public boolean suaSinhVien(SinhVien sv){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("full_name", sv.getHoTen());
        values.put("birth_date", sv.getNgaySinh());
        values.put("phone_number", sv.getSoDienThoai());
        values.put("email", sv.getEmail());
        values.put("gender", sv.getGioiTinh());
        values.put("hobbies", sv.getSoThich());
        values.put("department_id", sv.getIdKhoa());
        int updated = db.update("Student", values, "student_code = ?", new String[]{sv.getMaSV()});
        db.close();
        return updated > 0;
    }

    public boolean xoaSinhVien(String maSV){
        SQLiteDatabase db = getWritableDatabase();
        int deleted = db.delete("Student", "student_code = ?", new String[]{maSV});
        db.close();
        return deleted > 0;
    }

    public List<SinhVien> getAllSinhVien(){
        List<SinhVien> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.query("Student", null, null, null, null, null, "full_name ASC");
        if(cs.moveToFirst()){
            do {
                SinhVien sv = new SinhVien(
                        cs.getString(cs.getColumnIndexOrThrow("full_name")),
                        cs.getString(cs.getColumnIndexOrThrow("student_code")),
                        cs.getString(cs.getColumnIndexOrThrow("phone_number")),
                        cs.getString(cs.getColumnIndexOrThrow("email")),
                        cs.getString(cs.getColumnIndexOrThrow("department_id")),
                        cs.getString(cs.getColumnIndexOrThrow("birth_date")),
                        cs.getString(cs.getColumnIndexOrThrow("gender")),
                        cs.getString(cs.getColumnIndexOrThrow("hobbies"))
                );
                list.add(sv);
            } while(cs.moveToNext());
        }
        cs.close();
        return list;
    }

}
