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
    private Context context;
    private int resourceLayout;
    private List<SinhVien> danhSachSinhVien = new ArrayList<>();

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
        SinhVienViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resourceLayout, parent, false);
            holder = new SinhVienViewHolder();
            holder.tvHoTen = convertView.findViewById(R.id.tvHoTen);
            holder.tvMaSV = convertView.findViewById(R.id.tvMaSV);
            holder.tvSDT = convertView.findViewById(R.id.tvSDT);
            convertView.setTag(holder);
        } else {
            holder = (SinhVienViewHolder) convertView.getTag();
        }

        SinhVien sinhVien = danhSachSinhVien.get(position);

        holder.tvHoTen.setText(sinhVien.getHoTen() != null ? sinhVien.getHoTen() : "");
        holder.tvMaSV.setText(sinhVien.getMaSV() != null ? sinhVien.getMaSV() : "");
        holder.tvSDT.setText(sinhVien.getSoDienThoai() != null ? sinhVien.getSoDienThoai() : "");

        return convertView;
    }

    // Optional: cập nhật dữ liệu
    public void updateData(List<SinhVien> newList) {
        danhSachSinhVien.clear();
        danhSachSinhVien.addAll(newList);
        notifyDataSetChanged();
    }

    private static class SinhVienViewHolder {
        TextView tvHoTen;
        TextView tvMaSV;
        TextView tvSDT;
    }
}
