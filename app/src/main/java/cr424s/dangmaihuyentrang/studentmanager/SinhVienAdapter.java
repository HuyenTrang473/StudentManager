package cr424s.dangmaihuyentrang.studentmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SinhVienAdapter extends BaseAdapter {
    Context context;
    int resourceLayout;
    List<SinhVien> danhSachSinhVien = new ArrayList<>();

    public SinhVienAdapter(Context context, int resourceLayout, List<SinhVien> danhSachSinhVien) {
        this.context = context;
        this.resourceLayout = resourceLayout;
        this.danhSachSinhVien = danhSachSinhVien;
    }

    @Override
    public int getCount() { return danhSachSinhVien.size(); }

    @Override
    public Object getItem(int position) { return danhSachSinhVien.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SinhVienView sinhVienView;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.cell_student_layout, null);
            sinhVienView = new SinhVienView();
            sinhVienView.tvHoTen = convertView.findViewById(R.id.tvHoTen);
            sinhVienView.tvSDT = convertView.findViewById(R.id.tvSDT);
            sinhVienView.tvMaSV = convertView.findViewById(R.id.tvMaSV);
            convertView.setTag(sinhVienView);
        } else {
            sinhVienView = (SinhVienView) convertView.getTag();
        }

        SinhVien sinhVien = danhSachSinhVien.get(position);
        sinhVienView.tvHoTen.setText(sinhVien.getHoTen());
        sinhVienView.tvMaSV.setText(sinhVien.getMaSV());
        sinhVienView.tvSDT.setText(sinhVien.getSoDienThoai());
        return convertView;
    }

    class SinhVienView {
        TextView tvHoTen;
        TextView tvSDT;
        TextView tvMaSV;
    }
}
