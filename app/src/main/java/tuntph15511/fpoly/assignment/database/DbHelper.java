package tuntph15511.fpoly.assignment.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="QLSV";
    public static final int DB_VERSION= 4;
    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createtableLop="CREATE TABLE Lop(" +
                "maLop TEXT  NOT NULL," +
                "tenLop TEXT NOT NULL)";
        sqLiteDatabase.execSQL(createtableLop);

        String createtableSinhVien="CREATE TABLE SinhVien(" +
                "maSV TEXT PRIMARY KEY NOT NULL," +
                "tenSV TEXT NOT NULL," +
                "ngaySinh TEXT NOT NULL," +
                "maLop TEXT REFERENCES Lop(maLop))";
        sqLiteDatabase.execSQL(createtableSinhVien);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String updateTableLop="DROP TABLE IF EXISTS Lop";
        sqLiteDatabase.execSQL(updateTableLop);
        onCreate(sqLiteDatabase);
        String updateTableSinhVien="DROP TABLE IF EXISTS SinhVien";
        sqLiteDatabase.execSQL(updateTableSinhVien);
        onCreate(sqLiteDatabase);
    }
}
