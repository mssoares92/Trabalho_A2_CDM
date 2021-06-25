package soares.matheus.appdevelopment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final int VERSAO = 2;
    private static final String NOME = "AppDev";


    public Banco(Context context) {
        super(context, NOME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS Dev (" +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT , " +
                " email TEXT ," +
                "descr TEXT ," +
                " development TEXT )");

        db.execSQL("CREATE TABLE IF NOT EXISTS linguagens (" +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table dev");
        onCreate(db);

    }
}
