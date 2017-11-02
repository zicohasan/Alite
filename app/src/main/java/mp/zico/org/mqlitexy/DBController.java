package mp.zico.org.mqlitexy;

/**
 * Created by zico on 16/10/2017.
 */

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBController  extends SQLiteOpenHelper {

    public DBController(Context applicationcontext) {
        super(applicationcontext, "mltxy.db", null, 1);
    }
    //Creates Table
    @Override
    public void onCreate(SQLiteDatabase database) {
        String query;
        query = "CREATE TABLE chart ( X INTEGER PRIMARY KEY, Y INTEGER)";
        database.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS chart";
        database.execSQL(query);
        onCreate(database);
    }



    public void deleteTable(SQLiteDatabase database) {
        String query;
        query = "DELETE FROM chart";
        database.execSQL(query);
    }

    /**
     * Inserts User into SQLite DB
     * @param queryValues
     */
    public void insertUser(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("X", queryValues.get("userId"));
        values.put("Y", queryValues.get("userName"));
       // database.insert("chart", null, values);
        String query = "INSERT OR REPLACE INTO chart (X, Y) VALUES ("+(queryValues.get("userId")+","
                +queryValues.get("userName")+");");

        database.execSQL(query);
        database.close();
    }

    public void insertUsers(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Y", queryValues.get("userName"));
//        values.put("udpateStatus", "no");
        // database.insert("chart", null, values);
        database.insert("chart", null, values);
        database.close();
    }
/*
    public void insertUser(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("X", queryValues.get("X"));
        values.put("Y", queryValues.get("Y"));
        database.insert("users", null, values);
        database.close();
    }*/

    /**
     * Get list of Users from SQLite DB as Array List
     * @return
     */
    public ArrayList<HashMap<String, String>> getAllUsers() {
        ArrayList<HashMap<String, String>> usersList;
        usersList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM chart";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("userId", cursor.getString(0));
                map.put("userName", cursor.getString(1));
                usersList.add(map);
            } while (cursor.moveToNext());
        }
        database.close();
        return usersList;
    }

}
