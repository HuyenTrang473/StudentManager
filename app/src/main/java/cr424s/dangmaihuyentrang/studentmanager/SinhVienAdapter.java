package cr424s.dangmaihuyentrang.studentmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {

    Context context;
    int resourceLayout;
    List<SinhVien> danhSachSinhVien;

    public SinhVienAdapter(Context context, int resourceLayout, List<SinhVien> danhSachSinhVien) {
        this.context = context;
        this.resourceLayout = resourceLayout;
        this.danhSachSinhVien = danhSachSinhVien;
    }

    @Override
    public int getCount() {
        return danhSachSinhVien.size();
    }

    @Override
    public Object getItem(int position) {
        return danhSachSinhVien.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SinhVienView sinhVienView;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resourceLayout, null);
            sinhVienView = new SinhVienView();
            sinhVienView.tvHoTen = convertView.findViewById(R.id.tvHoTen);
            sinhVienView.tvMaSV = convertView.findViewById(R.id.tvMaSV);
            sinhVienView.tvSDT = convertView.findViewById(R.id.tvSDT);
            convertView.setTag(sinhVienView);
        } else {
            sinhVienView = (SinhVienView) convertView.getTag();
        }

        SinhVien sv = danhSachSinhVien.get(position);
        sinhVienView.tvHoTen.setText(sv.getHoTen());
        sinhVienView.tvMaSV.setText(sv.getMaSV());
        sinhVienView.tvSDT.setText(sv.getSoDienThoai());

        return convertView;
    }

    class SinhVienView {
        TextView tvHoTen;
        TextView tvMaSV;
        TextView tvSDT;
    }
}
