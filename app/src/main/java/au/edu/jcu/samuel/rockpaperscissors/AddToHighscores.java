package au.edu.jcu.samuel.rockpaperscissors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class AddToHighscores extends ActionBarActivity {
    public int playerMoves;
    public TextView movesView;
    public EditText playerName;
    public Button addButton;
    private ArrayAdapter<Player> players;
    private PlayersOpenHelper playersOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_highscores);
        playerMoves = getIntent().getIntExtra("playerMoves", 0);
        movesView = (TextView)findViewById(R.id.movesView);
        movesView.setText("" + playerMoves);
        playerName = (EditText) findViewById(R.id.nameText);
        addButton = (Button) findViewById(R.id.addToHighscoresButton);

        playersOpenHelper = new PlayersOpenHelper(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_to_highscores, menu);
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

    public void addToHighscores (View view) {
        SQLiteDatabase db = playersOpenHelper.getReadableDatabase();// Opens the database
        String name = playerName.getText().toString();
        if (name != "") {
            db.execSQL(String.format("INSERT OR IGNORE INTO Players(name) VALUES ('%s');", name)); // Used to make sure the name exists in the database
            db.execSQL(String.format("UPDATE Players SET moves = '%d' WHERE name = '%s';", playerMoves, name)); // updates the previously inserted name
        }
        Cursor cursor = db.query(true, "Players", null, null, null, null, null,
                null, null);
        while(cursor.moveToNext()){ 		// Iterates over every value returned from the query
            String n = cursor.getString(0);
            int m = cursor.getInt(1);
            Log.v("DataBase", String.format("Name: %s moves: %d", n, m));
        }
        db.close();
    }
}
