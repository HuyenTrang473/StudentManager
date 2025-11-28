package cr424s.dangmaihuyentrang.studentmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

import cr424s.dangmaihuyentrang.studentmanager.database.SinhVienDB;

public class ListDepartment extends AppCompatActivity {


    ListView lvKhoa;
    TextView edtTKKhoa;
    SinhVienDB db;
    private DepartmentAdapter adapter;
    private ArrayList<Department> danhSachKhoa;
    ActivityResultLauncher<Intent> addDepartmentLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_department);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            MaterialToolbar toolbar = findViewById(R.id.topAppBar);
            setSupportActionBar(toolbar);
            return insets;
        });

        lvKhoa = findViewById(R.id.lvKhoa);
        edtTKKhoa = findViewById(R.id.edtTimKhoa);
        db = new SinhVienDB(this, "QLKhoa", null, 1);

        danhSachKhoa = new ArrayList<>(db.getAllKhoa());
        if (danhSachKhoa.isEmpty()) {
            khoiTaoDuLieu();
            danhSachKhoa = new ArrayList<>(db.getAllKhoa());
        }

        adapter = new DepartmentAdapter(this, R.layout.cell_department_layout, danhSachKhoa);
        lvKhoa.setAdapter(adapter);
        edtTKKhoa = findViewById(R.id.edtTimKhoa);
        edtTKKhoa.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        lvKhoa = findViewById(R.id.lvKhoa);
        db = new SinhVienDB(this, "QLKhoa", null, 1);
        danhSachKhoa = new ArrayList<>(db.getAllKhoa());
        if (danhSachKhoa.isEmpty()) {
            khoiTaoDuLieu();
            danhSachKhoa = new ArrayList<>(db.getAllKhoa());
        }
        addDepartmentLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Toast.makeText(this, "Đã thêm khoa mới!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_khoa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemTMKhoa) {
            Intent intent = new Intent(ListDepartment.this, AddDepartment.class);
            addDepartmentLauncher.launch(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void khoiTaoDuLieu() {
        Department[] defaultDepartments = new Department[]{
                new Department("CNTT", "Công Nghệ Thông Tin", "Tầng 2, Tòa A1", "0901112233"),
                new Department("KHMT", "Khoa Học Máy Tính", "Tầng 4, Tòa A2", "0902223344"),
                new Department("BD", "Khoa Dữ Liệu & Big Data", "Tầng 5, Tòa B1", "0903334455"),
                new Department("AI", "Khoa Trí Tuệ Nhân Tạo", "Tầng 6, Tòa B2", "0904445566"),
                new Department("HTTT", "Hệ Thống Thông Tin", "Tầng 3, Tòa C1", "0905556677"),
                new Department("MMT", "Mạng Máy Tính & Truyền Thông", "Tầng 7, Tòa C2", "0906667788"),
                new Department("ATTT", "An Toàn Thông Tin", "Tầng 1, Tòa D1", "0907778899")
        };

        for (Department d : defaultDepartments) {
            db.themKhoa(d);
        }
    }
}