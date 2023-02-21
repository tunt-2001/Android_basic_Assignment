package tuntph15511.fpoly.assignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import tuntph15511.fpoly.assignment.R;
import tuntph15511.fpoly.assignment.model.Lop;

public class LopAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Lop> arrayList;



    public LopAdapter(Context context) {
        this.context = context;

    }
    public void setData(ArrayList<Lop> lopArrayList){
        arrayList= lopArrayList;
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
        MyViewHolder viewHolder;
        Lop myLop = arrayList.get(i);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null){
            view = inflater.inflate(R.layout.item_classes,null);
            viewHolder = new MyViewHolder();
            viewHolder.tvSTT = view.findViewById(R.id.txtSTT);
            viewHolder.tvMaLop = view.findViewById(R.id.txtMaLop);
            viewHolder.tvTenLop = view.findViewById(R.id.txtTenLop);

            view.setTag(viewHolder);

        }else {
            viewHolder =(MyViewHolder) view.getTag();
        }
        viewHolder.tvSTT.setText("" + (i+1));
        viewHolder.tvMaLop.setText(myLop.getMaLop());
        viewHolder.tvTenLop.setText(myLop.getTenLop());
        return view;
    }


    public final class MyViewHolder{
        private TextView tvSTT,tvMaLop,tvTenLop;
    }

}
