package cr424s.dangmaihuyentrang.studentmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        SinhVienDB sinhVienDB = new SinhVienDB(MainActivity.this,"SinhVienDB",null,1);


        danhSachSinhVien = new ArrayList<>();


        ListView lv1 = findViewById(R.id.lv1);

        String[][] data = {
                {"Phan Đặng Phương Anh", "283383", "0903849383", "phuonganh@gmail.com", "02/11/2003", "Nữ", "Đọc sách, Âm nhạc", "1"},
                {"Hồ Thị Thảo Vy", "283399", "0912345678", "thaovy@gmail.com", "12/06/2003", "Nữ", "Thể thao, Chơi game", "2"},
                {"Đặng Mai Huyền Trang", "283800", "0987654321", "huyentrang@gmail.com", "22/09/2003", "Nữ", "Âm nhạc, Đọc sách", "3"},
                {"Nguyễn Thị Lệ Na", "123456", "0948474755", "lena@gmail.com", "15/03/2004", "Nữ", "Chơi game, Đọc sách", "4"},
                {"Văn Hồng Anh", "393844", "0395555127", "honganh@gmail.com", "09/07/2003", "Nam", "Thể thao, Âm nhạc", "2"},
                {"Mai Ánh Sáng", "455555", "0983736633", "anhsang@gmail.com", "11/01/2004", "Nam", "Chơi game, Âm nhạc", "3"},
                {"Diệp Hồng Phi", "908776", "0948447345", "hongphi@gmail.com", "05/10/2003", "Nam", "Thể thao, Đọc sách", "4"},
                {"Trần Khánh Hòa", "567890", "0971122334", "khanhhoa@gmail.com", "17/02/2004", "Nữ", "Du lịch, Đọc sách", "1"},
                {"Lê Minh Quân", "445566", "0905123456", "minhquan@gmail.com", "25/05/2003", "Nam", "Chạy bộ, Chơi game", "5"},
                {"Ngô Thị Thanh Hà", "889977", "0936667788", "thanhha@gmail.com", "30/08/2003", "Nữ", "Nghe nhạc, Nấu ăn", "6"},
                {"Phạm Quốc Bảo", "334455", "0988776655", "quocbao@gmail.com", "10/12/2003", "Nam", "Đọc truyện, Lập trình", "7"},
                {"Đỗ Hoàng Tú", "778899", "0918999000", "hoangtudl@gmail.com", "21/04/2004", "Nam", "Âm nhạc, Nghiên cứu AI", "8"}
        };


        for (String[] sv : data) {
            danhSachSinhVien.add(new SinhVien(
                    sv[0],  // Họ và tên
                    sv[2],  // Mã SV
                    sv[1],  // Số điện thoại
                    sv[6],  // Email
                    sv[7] ,  // Ngày sinh
                    sv[4],  // Giới tính
                    sv[5],  // Sở thích
                    sv[3]  // Khoa
            ));
        }
        sinhVienAdapter = new SinhVienAdapter(MainActivity.this,R.layout.cell_student_layout,danhSachSinhVien);
        lv1.setAdapter(sinhVienAdapter);

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
