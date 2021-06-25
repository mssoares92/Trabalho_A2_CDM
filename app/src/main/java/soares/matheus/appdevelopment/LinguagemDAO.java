package soares.matheus.appdevelopment;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class LinguagemDAO {

    public static void inserir(Linguagem linguagem, Context context){
        ContentValues valores = new ContentValues();
        valores.put("id",linguagem.id);
        valores.put("nome",linguagem.nome);


        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("linguagens",null,valores);
    }

    public static List<Linguagem> getLinguagem(Context context){
        List<Linguagem> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nome FROM linguagens ORDER BY nome",null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                Linguagem linguagem = new Linguagem();
                linguagem.id = cursor.getInt(0);
                linguagem.nome = cursor.getString(1);

                lista.add(linguagem);
            }while(cursor.moveToNext());
        }
        return lista;
    }


    public static List<Linguagem> getLinguagem2(Context context){
        List<Linguagem> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nome FROM linguagens ORDER BY nome",null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                Linguagem linguagem = new Linguagem();
                linguagem.nome = cursor.getString(1);

                lista.add(linguagem);
            }while(cursor.moveToNext());
        }
        return lista;
    }

}

