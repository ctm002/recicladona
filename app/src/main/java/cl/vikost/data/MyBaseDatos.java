package cl.vikost.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyBaseDatos extends SQLiteOpenHelper {

    private static final String DONATIVOS_TABLE_CREATE = "CREATE TABLE donativos(_id INTEGER PRIMARY KEY AUTOINCREMENT, _usuario TEXT, _producto TEXT, _domicilio TEXT, _fecha TEXT)";
    private static final String USUARIOS_TABLE_CREATE  = "CREATE TABLE usuarios(_id INTEGER PRIMARY KEY AUTOINCREMENT, _username TEXT, _password TEXT)";
    private static final String POSTULANTES_TABLE_CREATE  = "CREATE TABLE postulantes(_id INTEGER PRIMARY KEY AUTOINCREMENT, _username TEXT, _fecha TEXT, _estado INTEGER, _donativo INTEGER)";


    private static final String DB_NAME = "cl.vikost.data.sqlite";
    private static final int DB_VERSION = 1;

    public MyBaseDatos(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DONATIVOS_TABLE_CREATE);
        db.execSQL(USUARIOS_TABLE_CREATE);
        db.execSQL(POSTULANTES_TABLE_CREATE);
        deleteDataBase(db);
        loadDataBase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void deleteDataBase(SQLiteDatabase db) {
        String sql = "delete FROM donativos;";
        db.execSQL(sql);
        sql = "delete FROM usuarios;";
        db.execSQL(sql);
        sql = "delete FROM postulantes;";
        db.execSQL(sql);
    }

    public void loadDataBase(SQLiteDatabase db){
        loadUsuarios(db);
        loadDonativos(db);
    }

    private void loadDonativos(SQLiteDatabase db) {
        String sql = "INSERT INTO Donativos(_usuario, _producto, _domicilio, _fecha)VALUES('ctapia', 'cocina usada', 'Pelequen 1277, Pudahuel, Santiago', '2020-12-19');";
        db.execSQL(sql);
        sql = "INSERT INTO Donativos(_usuario, _producto, _domicilio, _fecha)VALUES('ctapia', 'notebook', 'Pelequen 1277, Pudahuel, Santiago', '2020-12-19');";
        db.execSQL(sql);
        sql = "INSERT INTO Donativos(_usuario, _producto, _domicilio, _fecha)VALUES('dtapia', 'cama de dos plazas', 'Cerro Catedral 9523, Pudahuel, Santiago', '2020-12-19');";
        db.execSQL(sql);
        sql = "INSERT INTO Donativos(_usuario, _producto, _domicilio, _fecha)VALUES('dtapia', 'mesa para seis personas', 'San francisco 9003, Pudahuel, Santiago', '2020-12-19');";
        db.execSQL(sql);
    }

    private void loadUsuarios(SQLiteDatabase db) {
        String sql = "INSERT INTO Usuarios(_username, _password)VALUES('ctapia', '123');";
        db.execSQL(sql);
        sql = "INSERT INTO Usuarios(_username, _password)VALUES('dtapia', '1234');";
        db.execSQL(sql);
        sql = "INSERT INTO Usuarios(_username, _password)VALUES('vmolina', '12345');";
        db.execSQL(sql);
    }

}
