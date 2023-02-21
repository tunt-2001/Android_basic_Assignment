package tuntph15511.fpoly.assignment.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tuntph15511.fpoly.assignment.database.DbHelper;
import tuntph15511.fpoly.assignment.model.Lop;

public class LopDAO {
    private SQLiteDatabase db;

    public LopDAO(Context mContext) {
        DbHelper dbHelper= new DbHelper(mContext);
        db= dbHelper.getWritableDatabase();
    }

    public long insertLop(Lop obj){
        ContentValues values= new ContentValues();
        values.put("maLop",obj.getMaLop());
        values.put("tenLop",obj.getTenLop());
        return db.insert("Lop",null,values);
    }

    public int updateLop(Lop obj){
        ContentValues values= new ContentValues();
        values.put("maLop",obj.getMaLop());
        values.put("tenLop",obj.getTenLop());
        return db.update("Lop",values,"maLop=?",
                new String[]{String.valueOf(obj.getMaLop())});

    }

    public int deleteLop(Lop obj){
        return db.delete("Lop","maLop=?",
                new String[]{String.valueOf(obj.getMaLop())});
    }

    public ArrayList<Lop> getAll(){
        String sql="SELECT * FROM Lop";
        return getData(sql);
    }

//    public Lop getObjecById(String id){
//        String sql="SELECT * FROM Lop WHERE idLop=?";
//        List<Lop> list = getData(sql,id);
//        return list.get(0);
//    }
    @SuppressLint("Range")
    private ArrayList<Lop> getData(String sql, String...SelectArgs){
        ArrayList<Lop> list=new ArrayList<>();
        Cursor c = db.rawQuery(sql,SelectArgs);
        while (c.moveToNext()){
            Lop object = new Lop();
            object.setMaLop(c.getString(c.getColumnIndex("maLop")));
            object.setTenLop(c.getString(c.getColumnIndex("tenLop")));
            list.add(object);
        }
        return list;
    }
}
