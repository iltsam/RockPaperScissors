package au.edu.jcu.samuel.rockpaperscissors;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlayersOpenHelper extends SQLiteOpenHelper {
    public PlayersOpenHelper(Context context) {
        // Creates a database called HighscoresData
        super(context, "HighscoresData", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Sets up the database when called.
        setup(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        setup(db);

    }

    // Sets up the tables.
    private void setup(SQLiteDatabase database) {
        database.execSQL("DROP TABLE IF EXISTS Players;"); // Drops the table if it already exists
        database.execSQL("CREATE TABLE Players(name TEXT PRIMARY KEY UNIQUE, moves INT);"); // Creates the table with 2 columns, name and moves
        // Inserts default values for the players to compete with
        database.execSQL("INSERT INTO Players(name, moves) VALUES ('Sam', '2');");
        database.execSQL("INSERT INTO Players(name, moves) VALUES ('Josh', '3');");
        database.execSQL("INSERT INTO Players(name, moves) VALUES ('Moe', '4');");
    }
}
