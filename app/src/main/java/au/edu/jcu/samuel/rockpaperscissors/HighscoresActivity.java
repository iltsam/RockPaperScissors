package au.edu.jcu.samuel.rockpaperscissors;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class HighscoresActivity extends ActionBarActivity {
    private ArrayAdapter<Player> players;
    private PlayersOpenHelper playersOpenHelper;
    private ListView highscoreListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        highscoreListView = (ListView) findViewById(R.id.highscoresList);
        playersOpenHelper = new PlayersOpenHelper(this);
        // Opens the database
        SQLiteDatabase db = playersOpenHelper.getReadableDatabase();

        // Creates the controller for the listView
        players = new ArrayAdapter<Player>(this, R.layout.custom_textview);
        highscoreListView.setAdapter(players);

        Cursor cursor = db.query(true, "Players", null, null, null, null, null,
                "moves ASC", null);
        while(cursor.moveToNext()){ 		// Iterates over every value returned from the query
            String n = cursor.getString(0);
            int m = cursor.getInt(1);
            Log.v("DataBase", String.format("Name: %s moves: %d", n, m));
            players.add(new Player(n, m));
        }
        db.close();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_highscores, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void returnHome (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
