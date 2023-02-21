package tuntph15511.fpoly.assignment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import tuntph15511.fpoly.assignment.dao.LopDAO;
import tuntph15511.fpoly.assignment.model.Lop;

public class MainActivity extends AppCompatActivity {
    private Button btnAddClass, btnDanhSach, btnQLSV;
    private LopDAO lopDAO;
    private Lop lop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddClass = findViewById(R.id.btnAddClass);
        btnDanhSach = findViewById(R.id.btnDanhSach);
        btnQLSV = findViewById(R.id.btnQLSV);

        btnAddClass.setOnClickListener(view -> {
            openDialog(view);
        });
        btnDanhSach.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, DanhSachLopActivity.class));

        });
        btnQLSV.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, QuanLySV_Activity.class));

        });

    }

    public void openDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialog = LayoutInflater.from(this).inflate(R.layout.dialog_add_class, null);
        builder.setView(dialog);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


        EditText edMaLop = dialog.findViewById(R.id.malopTxt);
        EditText edTenLop = dialog.findViewById(R.id.tenlopTxt);
        Button btnClear = dialog.findViewById(R.id.btnClear);
        Button btnThemLop = dialog.findViewById(R.id.btnThemLopMoi);

        btnThemLop.setOnClickListener(view1 -> {
            lop = new Lop();
            String maLop = edMaLop.getText().toString();
            String tenLop = edTenLop.getText().toString();

            lop.setMaLop(maLop);
            lop.setTenLop(tenLop);
            lopDAO = new LopDAO(this);

            if (edMaLop.getText().length() == 0 || edTenLop.getText().length() == 0) {
                Toast.makeText(getApplicationContext(), "Không để trống thông tin", Toast.LENGTH_SHORT).show();

            } else {
                if (lopDAO.insertLop(lop) > 0) {
                    Toast.makeText(this, "Thêm lớp thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Thêm lớp thất bại", Toast.LENGTH_SHORT).show();

                }
            }


        });


        btnClear.setOnClickListener(view1 -> {
            edMaLop.setText("");
            edTenLop.setText("");
        });
    }
}

