package tuntph15511.fpoly.assignment.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tuntph15511.fpoly.assignment.database.DbHelper;
import tuntph15511.fpoly.assignment.model.SinhVien;

public class SinhVienDAO {
    private SQLiteDatabase db;

    public SinhVienDAO(Context mContext) {
        DbHelper dbHelper= new DbHelper(mContext);
        db= dbHelper.getWritableDatabase();
    }

    public long insertSV(SinhVien obj){
        ContentValues values= new ContentValues();
        values.put("maSV",obj.getTenSV());
        values.put("tenSV",obj.getTenSV());
        values.put("ngaySinh",obj.getNgaySinh());
        values.put("maLop",obj.getMaLop());
        return db.insert("SinhVien",null,values);
    }

    public int updateSV(SinhVien obj){
        ContentValues values= new ContentValues();
        values.put("maSV",obj.getTenSV());
        values.put("tenSV",obj.getTenSV());
        values.put("ngaySinh",obj.getNgaySinh());
        values.put("maLop",obj.getMaLop());
        return db.update("SinhVien",values,"maSV=?",
                new String[]{String.valueOf(obj.getMaSV())});

    }

    public int deleteSV(SinhVien obj){
        return db.delete("SinhVien","maSV=?",
                new String[]{String.valueOf(obj.getMaSV())});
    }

    public List<SinhVien> getAll(){
        String sql="SELECT * FROM SinhVien";
        return getData(sql);
    }

//    public SinhVien getObjecById(String id){
//        String sql="SELECT * FROM SinhVien WHERE idSV=?";
//        List<SinhVien> list = getData(sql,id);
//        return list.get(0);
//    }
    @SuppressLint("Range")
    private List<SinhVien> getData(String sql, String...SelectArgs){
        List<SinhVien> list=new ArrayList<>();
        Cursor c = db.rawQuery(sql,SelectArgs);
        while (c.moveToNext()){
            SinhVien object = new SinhVien();
            object.setMaSV(c.getString(c.getColumnIndex("maSV")));
            object.setTenSV(c.getString(c.getColumnIndex("tenSV")));
            object.setNgaySinh(c.getString(c.getColumnIndex("ngaySinh")));
            object.setMaLop(c.getString(c.getColumnIndex("maLop")));

            list.add(object);
        }
        return list;
    }
}
