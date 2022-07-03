package app.melhoroftheworld.agenda.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import app.melhoroftheworld.agenda.R;
import app.melhoroftheworld.agenda.nota.Nota;
import app.melhoroftheworld.agenda.evento.Evento;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "agenda";
    private static final String TABLE_NOTA = "nota";
    private static final String TABLE_EVENTO = "evento";

    private static final String CREATE_TABLE_NOTA = "CREATE TABLE " + TABLE_NOTA + " ("+
        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        "nome VARCHAR(100), " +
        "conteudo VARCHAR(500));";

    private static final String CREATE_TABLE_EVENTO = "CREATE TABLE " + TABLE_EVENTO + " ("+
        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        "nome VARCHAR(100), " +
        "descricao VARCHAR(300), " +
        "dataHora DATETIME);";

    private static final String DROP_TABLE_NOTA = "DROP TABLE IF EXISTS " + TABLE_NOTA;
    private static final String DROP_TABLE_EVENTO = "DROP TABLE IF EXISTS " + TABLE_EVENTO;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NOTA);
        db.execSQL(CREATE_TABLE_EVENTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_NOTA);
        db.execSQL(DROP_TABLE_EVENTO);
        onCreate(db);
    }

    // Nota
    public long createNota (Nota n) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", n.getNome());
        cv.put("conteudo", n.getConteudo());
        long id = db.insert(TABLE_NOTA, null, cv);
        db.close();
        return id;
    }
    public long updateNota (Nota n) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", n.getNome());
        cv.put("conteudo", n.getConteudo());
        long id = db.update(TABLE_NOTA, cv,
                "_id = ?", new String[]{String.valueOf(n.getId())});
        db.close();
        return id;
    }

    public long deleteNota (Nota n) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_NOTA, "_id = ?",
                new String[]{String.valueOf(n.getId())});
        db.close();
        return id;
    }

    public void getAllNota (Context context, ListView lv){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "conteudo"};
        Cursor data = db.query(TABLE_NOTA, columns, null, null,
                null, null, "_id");
        int[] to = {R.id.textViewIdListarNota, R.id.textViewNomeListarNota,
                R.id.textViewConteudoListarNota};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.nota_item_list_view, data, columns, to,0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public Nota getByIdNota (int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "conteudo"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_NOTA, columns, "_id = ?", args,
                null, null, null);
        data.moveToFirst();
        Nota n = new Nota();
        n.setId(data.getInt(0));
        n.setNome(data.getString(1));
        n.setConteudo(data.getString(2));
        data.close();
        db.close();
        return n;
    }
    // Nota

    // Evento
    public long createEvento (Evento e){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =  new ContentValues();
        cv.put("nome", e.getNome());
        cv.put("descricao", e.getDescricao());
        cv.put("dataHora", e.getDataHora());
        long id = db.insert(TABLE_EVENTO, null, cv);
        db.close();
        return id;
    }

    public long updateEvento (Evento e){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =  new ContentValues();
        cv.put("nome", e.getNome());
        cv.put("descricao", e.getDescricao());
        cv.put("dataHora", e.getDataHora());
        long id = db.update(TABLE_EVENTO, cv,
                "_id = ?", new String[]{String.valueOf(e.getId())});
        db.close();
        return id;
    }

    public long deleteEvento (Evento e) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_EVENTO, "_id = ?",
                new String[]{String.valueOf(e.getId())});
        db.close();
        return id;
    }

    public void getAllEvento (Context context, ListView lv){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "descricao", "dataHora"};
        Cursor data = db.query(TABLE_EVENTO, columns, null, null,
                null, null, "_id");
        int[] to = {R.id.textViewIdListarEvento, R.id.textViewNomeListarEvento,
                R.id.textViewDescricaoListarEvento};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.evento_item_list_view, data, columns, to,0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public Evento getByIdEvento(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "descricao", "dataHora"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_EVENTO, columns, "_id = ?", args,
                null, null, null);
        data.moveToFirst();
        Evento e = new Evento();
        e.setId(data.getInt(0));
        e.setNome(data.getString(1));
        e.setDescricao(data.getString(2));
        e.setDataHora(data.getString(3));
        data.close();
        db.close();
        return e;
    }
    // Nota
}
