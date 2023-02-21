package tuntph15511.fpoly.assignment.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import tuntph15511.fpoly.assignment.R;
import tuntph15511.fpoly.assignment.model.SinhVien;

public class SinhVienAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SinhVien> arrayList;
  //  private SinhVienDAO sinhVienDAO;

    public SinhVienAdapter(Context context) {
        this.context = context;
    }
    public void setData(ArrayList<SinhVien> sinhVienArrayList){
        arrayList = sinhVienArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (arrayList != null)
            return arrayList.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolderSinhVien viewHolder;
        LayoutInflater inflater =((Activity) context).getLayoutInflater();

        if (view == null){
            view = inflater.inflate(R.layout.item_sinh_vien,null);
            viewHolder = new ViewHolderSinhVien();
            viewHolder.tvSttSV = view.findViewById(R.id.tvSttSV);
           // viewHolder.tvMaSV = view.findViewById(R.id.tvMaSV);
            viewHolder.tvTenSV = view.findViewById(R.id.tvTenSV);
            viewHolder.tvNgaySinh = view.findViewById(R.id.tvNgaySinh);

            view.setTag(viewHolder);

        }else {
            viewHolder =(ViewHolderSinhVien) view.getTag();
        }
        viewHolder.tvSttSV.setText("" + (i+1));
       // viewHolder.tvMaSV.setText(arrayList.get(i).getMaSV());
        viewHolder.tvTenSV.setText(arrayList.get(i).getTenSV());
        viewHolder.tvNgaySinh.setText(arrayList.get(i).getNgaySinh());

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });

        return view;
    }
    public final class ViewHolderSinhVien{
        private TextView tvSttSV,tvMaSV,tvTenSV,tvNgaySinh;
    }
}
