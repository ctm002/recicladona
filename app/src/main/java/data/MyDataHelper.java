package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataHelper extends SQLiteOpenHelper {

    private static final String DONATIVOS_TABLE_CREATE = "CREATE TABLE donativos(_id INTEGER PRIMARY KEY AUTOINCREMENT, _usuario TEXT, _producto TEXT)";
    private static final String USUARIOS_TABLE_CREATE  = "CREATE TABLE usuarios(_id INTEGER PRIMARY KEY AUTOINCREMENT, _username TEXT, _password TEXT)";


    private static final String DB_NAME = "data.sqlite";
    private static final int DB_VERSION = 1;

    public MyDataHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DONATIVOS_TABLE_CREATE);
        db.execSQL(USUARIOS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
