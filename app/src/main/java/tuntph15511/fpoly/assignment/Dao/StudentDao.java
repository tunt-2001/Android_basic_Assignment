package tuntph15511.fpoly.assignment.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tuntph15511.fpoly.assignment.Database.MyHelper;
import tuntph15511.fpoly.assignment.Model.Student;

public class StudentDao {
    private SQLiteDatabase sqLiteDatabase;
    private MyHelper myHelper;

    public StudentDao(Context context) {
        myHelper = new MyHelper(context);
    }

    public List<Student> getListAllStudent() {
        List<Student> list = new ArrayList<>();
        sqLiteDatabase = myHelper.getReadableDatabase();
        String sql = "select * from Student";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Student student = new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setBirth(cursor.getString(2));
                student.setPhone(cursor.getString(3));
                student.setNameClassroom(cursor.getString(4));

                list.add(student);
                cursor.moveToNext();
            }
        }
        sqLiteDatabase.close();
        cursor.close();
        return list;
    }

    public boolean addStudent(Student student){
        sqLiteDatabase = myHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nameStudent" , student.getName());
        contentValues.put("birth" , student.getBirth());
        contentValues.put("phone" , student.getPhone());
        contentValues.put("nameClassroom" , student.getNameClassroom());
        long insert = sqLiteDatabase.insert(MyHelper.TABLE_NAME_STUDENT, null, contentValues);
        if(insert <=0){
            return false;
        }
        return true;
    }

    public boolean deleteStudent(int id){
        sqLiteDatabase = myHelper.getWritableDatabase();
        return sqLiteDatabase.delete(MyHelper.TABLE_NAME_STUDENT , "idStudent=?" , new String[]{String.valueOf(id)}) > 0;
    }

    public boolean editStudent(Student student){
        sqLiteDatabase = myHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nameStudent" , student.getName());
        contentValues.put("birth" , student.getBirth());
        contentValues.put("phone" , student.getPhone());
        contentValues.put("nameClassroom" , student.getNameClassroom());
        long insert = sqLiteDatabase.update(MyHelper.TABLE_NAME_STUDENT,  contentValues , "idStudent=?" , new String[]{String.valueOf(student.getId())});
        if(insert <=0){
            return false;
        }
        return true;
    }

}
