package tuntph15511.fpoly.assignment.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import tuntph15511.fpoly.assignment.Database.MyHelper;
import tuntph15511.fpoly.assignment.Model.Classroom;

public class ClassroomDao {
    private SQLiteDatabase sqLiteDatabase;
    private MyHelper myHelper;


    public ClassroomDao(Context context) {
        myHelper = new MyHelper(context);
    }

    public List<Classroom> getListAllClassroom() {
        List<Classroom> list = new ArrayList<>();
        sqLiteDatabase = myHelper.getReadableDatabase();
        String sql = "select * from Classroom";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (((Cursor) cursor).moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Classroom student = new Classroom();
                student.setId(cursor.getInt(0));
                student.setCodeClassroom(cursor.getString(1));
                student.setNameClassroom(cursor.getString(2));
                list.add(student);
                cursor.moveToNext();
            }
        }
        sqLiteDatabase.close();
        cursor.close();
        return list;
    }

    public boolean addClassroom(Classroom student) {
        sqLiteDatabase = myHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("codeClassroom", student.getCodeClassroom());
        contentValues.put("nameClassroom", student.getNameClassroom());
        long insert = sqLiteDatabase.insert(MyHelper.TABLE_NAME_CLASSROOM, null, contentValues);
        if (insert <= 0) {
            return false;
        }
        return true;
    }

    public boolean deleteClassroom(int id) {
        sqLiteDatabase = myHelper.getWritableDatabase();
        return sqLiteDatabase.delete(MyHelper.TABLE_NAME_CLASSROOM, "idClassroom=?", new String[]{String.valueOf(id)}) > 0;
    }


    public boolean editClassroom(Classroom student) {
        sqLiteDatabase = myHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("codeClassroom", student.getCodeClassroom());
        contentValues.put("nameClassroom", student.getNameClassroom());
        long insert = sqLiteDatabase.update(MyHelper.TABLE_NAME_CLASSROOM, contentValues, "idClassroom=?", new String[]{String.valueOf(student.getId())});
        if (insert <= 0) {
            return false;
        }
        return true;
    }

    public List<String> getListNameClassroom() {
        List<String> list = new ArrayList<>();
        sqLiteDatabase = myHelper.getReadableDatabase();
        String sql = "select * from Classroom";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                list.add(cursor.getString(2));
                cursor.moveToNext();
            }
        }
        sqLiteDatabase.close();
        cursor.close();
        return list;
    }




}
