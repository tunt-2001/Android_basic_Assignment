package tuntph15511.fpoly.assignment.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tuntph15511.fpoly.assignment.Dao.ClassroomDao;
import tuntph15511.fpoly.assignment.Database.MyHelper;
import tuntph15511.fpoly.assignment.Model.Classroom;
import tuntph15511.fpoly.assignment.R;

public class MainActivity extends AppCompatActivity {

        private Button addStudent;
        private Button showListClassroom;
        private Button managerStudent;
        private EditText codeClassroom;
        private EditText nameClassroom;
        private Button addClassroom;
        private Button clear;
        private ClassroomDao dao;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            addStudent = (Button) findViewById(R.id.addStudent);
            showListClassroom = (Button) findViewById(R.id.showListClassroom);
            managerStudent = (Button) findViewById(R.id.managerStudent);
            dao = new ClassroomDao(getApplicationContext());

            addStudent.setOnClickListener(view -> {
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_add_classroom);
                dialog.setCancelable(false);
                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (dialog != null && dialog.getWindow() != null) {
                    dialog.getWindow().setBackgrou_ndDrawable(new ColorDrawable(Color.TRANSPARENT));
                }
                codeClassroom = (EditText) dialog.findViewById(R.id.codeClassroom);
                nameClassroom = (EditText) dialog.findViewById(R.id.nameClassroom);
                addClassroom = (Button) dialog.findViewById(R.id.addClassroom);
                clear = (Button) dialog.findViewById(R.id.clear);

//                cancel = (Button) dialog.findViewById(R.id.cancel);

                addClassroom.setOnClickListener(v -> {
                    Classroom classroom = new Classroom();
                    String codeClass = codeClassroom.getText().toString();
                    String nameClass = nameClassroom.getText().toString();
                    classroom.setCodeClassroom(codeClass);
                    classroom.setNameClassroom(nameClass);

                    if (codeClass.equals("") && nameClass.equals("")) {
                        Toast.makeText(this, "khong duoc de trong", Toast.LENGTH_SHORT).show();
                    } else if (dao.addClassroom(classroom) == true){
                        Toast.makeText(this, "them thanh cong", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(this, "them that bai", Toast.LENGTH_SHORT).show();

                    }
                });
                clear.setOnClickListener(v -> {
                    codeClassroom.setText("");
                    nameClassroom.setText("");
                });

//                cancel.setOnClickListener(v -> {
//                    dialog.dismiss();
//                });
                dialog.show();
            });

            showListClassroom.setOnClickListener(v -> {
                startActivity(new Intent(MainActivity.this,ListClassActivity.class));
            });
            managerStudent.setOnClickListener(v -> {
                startActivity(new Intent(MainActivity.this, StudentActivity.class));
            });

        }

    }