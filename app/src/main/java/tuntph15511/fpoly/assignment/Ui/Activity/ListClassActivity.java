package tuntph15511.fpoly.assignment.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import tuntph15511.fpoly.assignment.Dao.ClassroomDao;
import tuntph15511.fpoly.assignment.Model.Classroom;
import tuntph15511.fpoly.assignment.R;
import tuntph15511.fpoly.assignment.Ui.Adapter.ClassroomAdapter;

public class ListClassActivity extends AppCompatActivity implements ClassroomAdapter.Callback {
      private ListView listview;
      private ClassroomDao dao;
      private List<Classroom> data;
      private ClassroomAdapter adapter;
      @Override
      protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_list_class);

         getSupportActionBar().setTitle("Danh Sách Lớp");
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         getSupportActionBar().setHomeButtonEnabled(true);
         dao = new ClassroomDao(this);
         listview = (ListView) findViewById(R.id.listview);
      }

      @Override
      public boolean onSupportNavigateUp() {
         onBackPressed();
         return super.onSupportNavigateUp();
      }

      @Override
      protected void onResume() {
         super.onResume();
         showData();
      }

      public void showData(){
         data = dao.getListAllClassroom();
         adapter = new ClassroomAdapter(data , this, this);
         listview.setAdapter(adapter);
      }


      @Override
      public void delete(Classroom classroom) {
         dao.deleteClassroom(classroom.getId());
         Toast.makeText(this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
         showData();
      }
   }