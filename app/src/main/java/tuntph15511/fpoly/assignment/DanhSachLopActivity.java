package tuntph15511.fpoly.assignment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import tuntph15511.fpoly.assignment.adapter.LopAdapter;
import tuntph15511.fpoly.assignment.dao.LopDAO;
import tuntph15511.fpoly.assignment.model.Lop;

public class DanhSachLopActivity extends AppCompatActivity {
    private ListView lvClass;
    private LopAdapter lopAdapter;
    private ArrayList<Lop> arrayList = new ArrayList<>();
    private LopDAO lopDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_lop);
        lvClass = findViewById(R.id.listClasses);
        lopDAO = new LopDAO(this);
        lopAdapter = new LopAdapter(getApplicationContext());

        arrayList = (ArrayList<Lop>) lopDAO.getAll();
        lopAdapter.setData(arrayList);
        lvClass.setAdapter(lopAdapter);

        lvClass.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogEdit(arrayList.get(position));
                return true;
            }
        });

        lvClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DeleteDialog(arrayList.get(position));
            }
        });

    }
    public void showDialogEdit(Lop obj){
        Dialog dialogEdit=new Dialog(this);
        dialogEdit.setContentView(R.layout.dialog_edit_class);
        EditText edMaLop = dialogEdit.findViewById(R.id.malopUpdate);
        EditText edTenLop = dialogEdit.findViewById(R.id.tenlopUpdate);

        edMaLop.setText(obj.getMaLop());
        edTenLop.setText(obj.getTenLop());


        Button btnUpdate = dialogEdit.findViewById(R.id.btnUpdate);
        Button btnCancel = dialogEdit.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogEdit.dismiss();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lop newObj= new Lop();
                newObj.setIdLop(obj.getIdLop());
                newObj.setMaLop(edMaLop.getText().toString());
                newObj.setTenLop(edTenLop.getText().toString());
                if(lopDAO.updateLop(newObj) > 0){
                    Toast.makeText(getApplicationContext(),
                            "Update thành công",Toast.LENGTH_LONG).show();
                    arrayList = lopDAO.getAll();
                    lopAdapter.setData(arrayList);
                }
                else
                    Toast.makeText(getApplicationContext(),
                            "update thất bại",Toast.LENGTH_LONG).show();

                dialogEdit.dismiss();
            }
        });
        dialogEdit.show();
    }

    public void DeleteDialog(Lop obj){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bạn chắc chắn muốn xóa?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(lopDAO.deleteLop(obj) > 0){
                    Toast.makeText(getApplicationContext(),
                            "Xóa thành công",Toast.LENGTH_LONG).show();
                    arrayList.remove(obj);
                    lopAdapter.setData(arrayList);
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


}