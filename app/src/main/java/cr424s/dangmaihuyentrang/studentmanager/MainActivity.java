package cr424s.dangmaihuyentrang.studentmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import cr424s.dangmaihuyentrang.studentmanager.database.SinhVienDB;

public class MainActivity extends AppCompatActivity {

    ArrayList<SinhVien> danhSachSinhVien;
    SinhVienAdapter sinhVienAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Khởi tạo database (dù chưa dùng dữ liệu từ DB, chỉ để chuẩn bị)
        SinhVienDB sinhVienDB = new SinhVienDB(this);

        // Dữ liệu cứng
        danhSachSinhVien = new ArrayList<>();
        ListView lv1 = findViewById(R.id.lv1);

        String[][] data = {
                {"Phan Đặng Phương Anh", "283383", "0903849383", "phuonganh@gmail.com", "02/11/2003", "Nữ", "Đọc sách, Âm nhạc", "Công Nghệ Phần Mềm"},
                {"Hồ Thị Thảo Vy", "283399", "0912345678", "thaovy@gmail.com", "12/06/2003", "Nữ", "Thể thao, Chơi game", "Khoa Học Máy Tính"},
                {"Đặng Mai Huyền Trang", "283800", "0987654321", "huyentrang@gmail.com", "22/09/2003", "Nữ", "Âm nhạc, Đọc sách", "Big Data"},
                {"Nguyễn Thị Lệ Na", "123456", "0948474755", "lena@gmail.com", "15/03/2004", "Nữ", "Chơi game, Đọc sách", "Trí Tuệ Nhân Tạo"},
                {"Văn Hồng Anh", "393844", "0395555127", "honganh@gmail.com", "09/07/2003", "Nam", "Thể thao, Âm nhạc", "Khoa Học Máy Tính"},
                {"Mai Ánh Sáng", "455555", "0983736633", "anhsang@gmail.com", "11/01/2004", "Nam", "Chơi game, Âm nhạc", "Big Data"},
                {"Diệp Hồng Phi", "908776", "0948447345", "hongphi@gmail.com", "05/10/2003", "Nam", "Thể thao, Đọc sách", "Trí Tuệ Nhân Tạo"},
                {"Trần Khánh Hòa", "567890", "0971122334", "khanhhoa@gmail.com", "17/02/2004", "Nữ", "Du lịch, Đọc sách", "Công Nghệ Phần Mềm"},
                {"Lê Minh Quân", "445566", "0905123456", "minhquan@gmail.com", "25/05/2003", "Nam", "Chạy bộ, Chơi game", "Hệ Thống Thông Tin"},
                {"Ngô Thị Thanh Hà", "889977", "0936667788", "thanhha@gmail.com", "30/08/2003", "Nữ", "Nghe nhạc, Nấu ăn", "Mạng Máy Tính"},
                {"Phạm Quốc Bảo", "334455", "0988776655", "quocbao@gmail.com", "10/12/2003", "Nam", "Đọc truyện, Lập trình", "An Toàn Thông Tin"},
                {"Đỗ Hoàng Tú", "778899", "0918999000", "hoangtudl@gmail.com", "21/04/2004", "Nam", "Âm nhạc, Nghiên cứu AI", "Khoa Học Dữ Liệu"}
        };

        for (String[] sv : data) {
            danhSachSinhVien.add(new SinhVien(
                    sv[0],  // Họ và tên
                    sv[1],  // Mã SV
                    sv[2],  // Số điện thoại
                    sv[3],  // Email
                    sv[4],  // Ngày sinh
                    sv[5],  // Giới tính
                    sv[6],  // Sở thích
                    sv[7]   // Khoa
            ));
        }

        // Thiết lập adapter
        sinhVienAdapter = new SinhVienAdapter(MainActivity.this, R.layout.cell_student_layout, danhSachSinhVien);
        lv1.setAdapter(sinhVienAdapter);

        // Bắt sự kiện click vào item
        lv1.setOnItemClickListener((parent, view, position, id) -> {
            SinhVien sinhVien = (SinhVien) sinhVienAdapter.getItem(position);
            Intent intent = new Intent(MainActivity.this, StudentInfoActivity.class);
            intent.putExtra("sv0", sinhVien);
            startActivity(intent);

            Toast.makeText(MainActivity.this,
                    "Bạn chọn: " + sinhVien.getHoTen(),
                    Toast.LENGTH_SHORT).show();
        });
    }
}
