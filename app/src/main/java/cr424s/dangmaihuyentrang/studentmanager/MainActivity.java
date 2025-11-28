package cr424s.dangmaihuyentrang.studentmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import cr424s.dangmaihuyentrang.studentmanager.database.SinhVienDB;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText edtTimKiem;
    private ArrayList<SinhVien> danhSachSinhVien;
    private SinhVienAdapter sinhVienAdapter;
    private SinhVienDB db;

    private ActivityResultLauncher<Intent> addStudentLauncher;
    private ActivityResultLauncher<Intent> updateStudentLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // EdgeToEdge padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);
        edtTimKiem = findViewById(R.id.edtTimKiem);
        db = new SinhVienDB(this, "QLSinhVien", null, 1);

        // Lấy dữ liệu từ database
        danhSachSinhVien = new ArrayList<>(db.getAllSinhVien());

        // Nếu database rỗng, thêm dữ liệu mặc định
        if (danhSachSinhVien.isEmpty()) {
            khoiTaoDuLieu();
            danhSachSinhVien = new ArrayList<>(db.getAllSinhVien());
        }

        // Tạo adapter
        sinhVienAdapter = new SinhVienAdapter(this, R.layout.cell_student_layout, danhSachSinhVien);
        listView.setAdapter(sinhVienAdapter);

        // Launcher thêm sinh viên
        addStudentLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        SinhVien newSV = (SinhVien) result.getData().getSerializableExtra("newSV");
                        if (newSV != null) {
                            danhSachSinhVien.add(newSV);
                            sinhVienAdapter.refresh();
                            edtTimKiem.getText().clear();
                        }
                    }
                }
        );

        // Launcher cập nhật sinh viên
        updateStudentLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        // Load lại dữ liệu từ DB
                        danhSachSinhVien.clear();
                        danhSachSinhVien.addAll(db.getAllSinhVien());
                        sinhVienAdapter.refresh();
                    }
                }
        );

        // Click vào 1 sinh viên -> mở StudentInforActivity
        listView.setOnItemClickListener((parent, view, position, id) -> {
            SinhVien sv = danhSachSinhVien.get(position);
            Intent intent = new Intent(MainActivity.this, StudentInfoActivity.class);
            intent.putExtra("k_sinhvien", sv);
            updateStudentLauncher.launch(intent);
        });

        // Search filter
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sinhVienAdapter.filter(s.toString().toLowerCase().trim());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemThemMoiSV) {
            Intent intent = new Intent(MainActivity.this, cr424s.dangmaihuyentrang.studentmanager.AddStudentActivity.class);
            addStudentLauncher.launch(intent);
        }
        else if(item.getItemId() == R.id.itemXoaTatCa) {
            new android.app.AlertDialog.Builder(this)
                    .setTitle("Xác nhận")
                    .setMessage("Bạn có chắc chắn muốn xóa tất cả sinh viên?")
                    .setPositiveButton("Có", (dialog, which) -> {
                        db.xoaTatCaSinhVien();        // Xóa toàn bộ trong DB
                        danhSachSinhVien.clear();    // Xóa danh sách trong MainActivity
                        sinhVienAdapter.refresh();   // Cập nhật adapter
                        Toast.makeText(MainActivity.this, "Đã xóa tất cả sinh viên!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Không", null)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }
    // Thêm dữ liệu mặc định vào DB
    private void khoiTaoDuLieu() {
        SinhVien[] defaultSVs = new SinhVien[]{
                new SinhVien("SV001", "Nguyễn Nhật Minh", "0911111111", "minhn01@gmail.com", "03/01/2003", "Công Nghệ Phần Mềm", "Nam", "Bóng đá"),
                new SinhVien("SV002", "Trần Thảo Vy", "0922222222", "vyt02@gmail.com", "15/02/2003", "Khoa Học Máy Tính", "Nữ", "Nghe nhạc"),
                new SinhVien("SV003", "Lê Quốc Hùng", "0933333333", "hunglq03@gmail.com", "09/03/2002", "An Toàn Thông Tin", "Nam", "Chơi game"),
                new SinhVien("SV004", "Phạm Mỹ Duyên", "0944444444", "duyenpm04@gmail.com", "28/04/2003", "Mạng Máy Tính", "Nữ", "Xem phim"),
                new SinhVien("SV005", "Hoàng Anh Tuấn", "0955555555", "tuanha05@gmail.com", "21/05/2002", "Hệ Thống Thông Tin", "Nam", "Đọc sách"),
                new SinhVien("SV006", "Võ Gia Hân", "0966666666", "hanvg06@gmail.com", "12/06/2003", "Big Data", "Nữ", "Du lịch"),
                new SinhVien("SV007", "Ngô Trọng Phúc", "0977777777", "phucnt07@gmail.com", "27/07/2003", "Trí Tuệ Nhân Tạo", "Nam", "Bơi lội"),
                new SinhVien("SV008", "Đỗ Mai Linh", "0988888888", "linhdm08@gmail.com", "16/08/2002", "Khoa Học Máy Tính", "Nữ", "Chụp ảnh"),
                new SinhVien("SV009", "Huỳnh Thanh Tâm", "0999999999", "tamht09@gmail.com", "07/09/2003", "Công Nghệ Phần Mềm", "Nam", "Âm nhạc"),
                new SinhVien("SV010", "Phan Khánh Chi", "0900000000", "chipk10@gmail.com", "10/10/2002", "An Toàn Thông Tin", "Nữ", "Nấu ăn")
        };

        for (SinhVien sv : defaultSVs) {
            db.themSV(sv);
        }
    }
}