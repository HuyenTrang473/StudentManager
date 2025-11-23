package cr424s.dangmaihuyentrang.studentmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GiaoDien extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_giao_dien);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnSV = findViewById(R.id.btnSV);
        Button btnKhoa = findViewById(R.id.btnKhoa);

        btnSV.setOnClickListener(v -> {
            Intent intent = new Intent(GiaoDien.this, MainActivity.class);
            startActivity(intent);
        });

        // btnKhoa hiện chưa được xử lý
    }
}
