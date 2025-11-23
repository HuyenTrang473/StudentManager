package cr424s.dangmaihuyentrang.studentmanager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import cr424s.dangmaihuyentrang.studentmanager.database.SinhVienDB;

public class StudentInfoActivity extends AppCompatActivity {

    EditText edtTen, edtMSV, edtSdt, edtEmail, edtNgaySinh;
    RadioButton rbtNam, rbtNu;
    CheckBox checkNhac, checkDoc, checkTheThao, checkXem;
    Spinner spKhoa;
    Button btnUpdate, btnDelete;

    String[] KHOA = {
            "Công Nghệ Phần Mềm",
            "Khoa Học Máy Tính",
            "Big Data",
            "Trí Tuệ Nhân Tạo",
            "Hệ Thống Thông Tin",
            "Mạng Máy Tính",
            "An Toàn Thông Tin",
            "Khoa Học Dữ Liệu",
            "Kỹ Thuật Phần Mềm",
            "Công Nghệ Thông Tin Tổng Hợp"
    };

    SinhVien sv;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_info);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtTen = findViewById(R.id.edtHoTen);
        edtMSV = findViewById(R.id.edtMaSV);
        edtSdt = findViewById(R.id.edtSDT);
        edtEmail = findViewById(R.id.edtEmail);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);

        rbtNam = findViewById(R.id.rdNam);
        rbtNu = findViewById(R.id.rdNu);

        checkNhac = findViewById(R.id.checkNhac);
        checkDoc = findViewById(R.id.checkDoc);
        checkTheThao = findViewById(R.id.checkTheThao);
        checkXem = findViewById(R.id.checkXem);

        spKhoa = findViewById(R.id.spinner2);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, KHOA);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKhoa.setAdapter(adapter);

        sv = (SinhVien) getIntent().getSerializableExtra("sv0");

        if (sv != null) {
            loadDataToView(sv);
        }

        btnUpdate.setOnClickListener(v -> updateStudent());
        btnDelete.setOnClickListener(v -> deleteStudent());
    }

    private void loadDataToView(SinhVien sv) {
        edtTen.setText(sv.getHoTen());
        edtMSV.setText(sv.getMaSV());
        edtSdt.setText(sv.getSoDienThoai());
        edtEmail.setText(sv.getEmail());
        edtNgaySinh.setText(sv.getNgaySinh());

        for (int i = 0; i < KHOA.length; i++) {
            if (KHOA[i].equals(sv.getIdKhoa())) {
                spKhoa.setSelection(i);
                break;
            }
        }

        if ("Nam".equalsIgnoreCase(sv.getGioiTinh())) rbtNam.setChecked(true);
        else rbtNu.setChecked(true);

        String st = sv.getSoThich() == null ? "" : sv.getSoThich();
        checkNhac.setChecked(st.contains("Âm nhạc"));
        checkDoc.setChecked(st.contains("Đọc sách"));
        checkTheThao.setChecked(st.contains("Thể thao"));
        checkXem.setChecked(st.contains("Chơi game"));
    }

    private void updateStudent() {
        SinhVienDB db = new SinhVienDB(this);

        String ma = edtMSV.getText().toString().trim();
        if (ma.isEmpty()) {
            Toast.makeText(this, "Mã sinh viên trống!", Toast.LENGTH_SHORT).show();
            return;
        }

        String ten = edtTen.getText().toString().trim();
        String sdt = edtSdt.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String ngaySinh = edtNgaySinh.getText().toString().trim();
        String gioiTinh = rbtNam.isChecked() ? "Nam" : "Nữ";
        String khoa = spKhoa.getSelectedItem().toString();

        String st = "";
        if (checkNhac.isChecked()) st += "Âm nhạc, ";
        if (checkDoc.isChecked()) st += "Đọc sách, ";
        if (checkTheThao.isChecked()) st += "Thể thao, ";
        if (checkXem.isChecked()) st += "Chơi game, ";
        if (st.endsWith(", ")) st = st.substring(0, st.length() - 2);

        SinhVien newSV = new SinhVien(ma, ten, sdt, email, khoa, ngaySinh, gioiTinh, st);

        db.suaSinhVien(newSV);

        Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        intent.putExtra("sv_updated", newSV);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void deleteStudent() {
        SinhVienDB db = new SinhVienDB(this);
        String ma = edtMSV.getText().toString().trim();

        if (ma.isEmpty()) {
            Toast.makeText(this, "Mã sinh viên không được trống!", Toast.LENGTH_SHORT).show();
            return;
        }

        db.xoaSinhVien(ma);

        Toast.makeText(this, "Xóa thành công!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        intent.putExtra("sv_deleted", ma);
        setResult(RESULT_OK, intent);
        finish();
    }
}
