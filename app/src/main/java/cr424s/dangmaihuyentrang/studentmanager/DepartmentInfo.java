package cr424s.dangmaihuyentrang.studentmanager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import cr424s.dangmaihuyentrang.studentmanager.database.SinhVienDB;


public class DepartmentInfo  extends  AppCompatActivity {
    EditText edtMaKhoa, edtTenKhoa, edtDiaChi, edtSdt;
Button btnCapNhat, btnXoa;
Department dept;
SinhVienDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_info);

        edtMaKhoa = findViewById(R.id.edtMaKhoa);
        edtTenKhoa = findViewById(R.id.edtTenKhoa);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtSdt = findViewById(R.id.edtSdt);
        btnCapNhat = findViewById(R.id.btnCapNhat);
        btnXoa = findViewById(R.id.btnXoa);

        db = new SinhVienDB(this, "QLKhoa", null, 1);

        // Nhận dữ liệu từ Intent
        dept = (Department) getIntent().getSerializableExtra("k_khoa");
        if (dept != null) {
            edtMaKhoa.setText(dept.getMaKhoa());
            edtTenKhoa.setText(dept.getTenKhoa());
            edtDiaChi.setText(dept.getDiaChi());
            edtSdt.setText(dept.getSdt());
        }

        // Cập nhật
        btnCapNhat.setOnClickListener(v -> {
            String ma = edtMaKhoa.getText().toString().trim();
            String ten = edtTenKhoa.getText().toString().trim();
            String diaChi = edtDiaChi.getText().toString().trim();
            String sdt = edtSdt.getText().toString().trim();

            if (ma.isEmpty() || ten.isEmpty() || diaChi.isEmpty() || sdt.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            dept.setMaKhoa(ma);
            dept.setTenKhoa(ten);
            dept.setDiaChi(diaChi);
            dept.setSdt(sdt);

            long kq = db.capNhatKhoa(dept);
            if (kq > 0) {
                Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                Intent result = new Intent();
                setResult(RESULT_OK, result);
                finish();
            } else {
                Toast.makeText(this, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
            }
        });

        // Xóa khoa
        btnXoa.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Xác nhận")
                    .setMessage("Bạn có chắc chắn muốn xóa khoa này?")
                    .setPositiveButton("Có", (dialog, which) -> {
                        db.xoaKhoa(dept.getMaKhoa());
                        Toast.makeText(this, "Đã xóa khoa!", Toast.LENGTH_SHORT).show();
                        Intent result = new Intent();
                        setResult(RESULT_OK, result);
                        finish();
                    })
                    .setNegativeButton("Không", null)
                    .show();
        });
    }

}
