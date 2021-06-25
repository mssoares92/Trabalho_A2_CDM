package soares.matheus.appdevelopment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DevDAO {

    public static void inserir(Dev dev, Context context){
        ContentValues valores = new ContentValues();
        valores.put("nome",dev.nome);
        valores.put("email",dev.email);
        valores.put("descr",dev.descr);
        valores.put("development",dev.getDevelopment());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("dev",null,valores);
    }

    public static void editar(Dev dev, Context context){
        ContentValues valores = new ContentValues();
        valores.put("nome",dev.nome);
        valores.put("email",dev.email);
        valores.put("descr",dev.descr);
        valores.put("development",dev.getDevelopment());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.update("dev",valores," id = " + dev.id,null);
    }



    public static void excluir(int id,Context context){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("dev"," id = " + id,null);
    }

    public static List<Dev> getDev(Context context){
        List<Dev> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nome, email, descr, development FROM dev ORDER BY nome",null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                Dev dev = new Dev();
                dev.id = cursor.getInt(0);
                dev.nome = cursor.getString(1);
                dev.email = cursor.getString(2);
                dev.descr = cursor.getString(3);
                dev.setDevelopment(cursor.getString(4));
                lista.add(dev);
            }while(cursor.moveToNext());
        }
        return lista;
    }

    public static Dev getDevsById(Context context, int id){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nome, email, descr, development FROM dev WHERE id = " + id,null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
                Dev dev = new Dev();
                dev.id = cursor.getInt(0);
                dev.nome = cursor.getString(1);
                dev.email = cursor.getString(2);
                dev.descr = cursor.getString(3);
                dev.setDevelopment(cursor.getString(4));
                return dev;
        }else{
            return null;
        }
    }
}
