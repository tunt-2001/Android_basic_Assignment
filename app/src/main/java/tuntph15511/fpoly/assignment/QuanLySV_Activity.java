package tuntph15511.fpoly.assignment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import tuntph15511.fpoly.assignment.adapter.SinhVienAdapter;
import tuntph15511.fpoly.assignment.dao.LopDAO;
import tuntph15511.fpoly.assignment.dao.SinhVienDAO;
import tuntph15511.fpoly.assignment.model.Lop;
import tuntph15511.fpoly.assignment.model.SinhVien;

public class QuanLySV_Activity extends AppCompatActivity {

    private LopDAO lopDAO;
    private SinhVienDAO sinhVienDAO;
    private SinhVien sinhVien;
    private SinhVienAdapter sinhVienAdapter;
    private ArrayList<SinhVien> sinhVienArrayList = new ArrayList<>();

    private Spinner spListClass;
    private EditText edTenSV, edNgaySinh,edMaSV;
    private Button btnThemSV;
    private ListView listSinhVien;

   // int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_sv);

        lopDAO = new LopDAO(this);
        sinhVienDAO = new SinhVienDAO(this);

        spListClass = findViewById(R.id.spListClass);
        //edMaSV = findViewById(R.id.edMaSV);
        edTenSV = findViewById(R.id.edTenSV);
        edNgaySinh = findViewById(R.id.edNgaySinh);
        btnThemSV = findViewById(R.id.btnThemSV);
        listSinhVien = findViewById(R.id.listSinhVien);



        List<Lop> lopList = lopDAO.getAll();
        List<HashMap<String,String >> list = new ArrayList<>();
        for (Lop lop : lopList){
            HashMap<String ,String > hashMap = new HashMap<>();
            hashMap.put("tenLop",lop.getTenLop());
            list.add(hashMap);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.item_spinner_class,
                new String[]{"tenLop"},new int[]{R.id.tvTenLopSP});
        spListClass.setAdapter(adapter);

        capNhatLV();

        btnThemSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,String> hashMap =(HashMap<String, String>) spListClass.getSelectedItem();

                sinhVien = new SinhVien();

               // String maSV = edMaSV.getText().toString();
                String tenSV= edTenSV.getText().toString();
                String ngaySinh = edNgaySinh.getText().toString();

                //sinhVien.setMaSV(maSV);
                sinhVien.setTenSV(tenSV);
                sinhVien.setNgaySinh(ngaySinh);

                if (validate() > 0){
                    if (sinhVienDAO.insertSV(sinhVien) > 0){
                        Toast.makeText(getApplicationContext(), "Thêm sinh viên thành công", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(getApplicationContext(), "Thêm sinh viên thất bại", Toast.LENGTH_SHORT).show();

                    }
                    capNhatLV();
                }


            }
        });

        listSinhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                DeleteSVDialog(sinhVienArrayList.get(position));
                return true;
            }
        });
//        listSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                SinhVien sinhVien = new SinhVien();
//                edTenSV.setText(sinhVien.getTenSV());
//                edNgaySinh.setText(sinhVien.getNgaySinh());
//
//            }
//        });


    }
    public void DeleteSVDialog(SinhVien obj){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bạn chắc chắn muốn xóa?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(sinhVienDAO.deleteSV(obj) > 0){
                    Toast.makeText(getApplicationContext(),
                            "Xóa thành công",Toast.LENGTH_LONG).show();
                    sinhVienArrayList.remove(obj);
                    sinhVienAdapter.setData(sinhVienArrayList);
                }
                else
                    Toast.makeText(getApplicationContext(),
                            "Xóa thất bại",Toast.LENGTH_LONG).show();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }

    public int validate(){
        String datePattern = "(0[1-9]|[12][0-9]|[3][01])/(0[1-9]|1[012])/[0-9]{4}";

        int check = 1;
        if (edTenSV.getText().length() == 0 || edNgaySinh.getText().length() == 0){
            Toast.makeText(getApplicationContext(), "Không để trống thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        if (!edNgaySinh.getText().toString().trim().matches(datePattern)){
            Toast.makeText(getApplicationContext(), "Sai định dạng ngày sinh", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
    public void capNhatLV(){
        sinhVienArrayList = (ArrayList<SinhVien>) sinhVienDAO.getAll();
        sinhVienAdapter = new SinhVienAdapter(this);
        sinhVienAdapter.setData(sinhVienArrayList);
        listSinhVien.setAdapter(sinhVienAdapter);
    }
}