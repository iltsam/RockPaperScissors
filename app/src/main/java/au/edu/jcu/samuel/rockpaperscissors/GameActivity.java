package au.edu.jcu.samuel.rockpaperscissors;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GameActivity extends Activity implements SensorEventListener {
    public String gameType = "single_player";
    public String oponentChoice;
    public TextView winnerText;
    public Button opponentButton;
    public Button playerButton;
    public Button disableButton;
    public TextView movesView;
    public int movesCounter;

    private SensorManager sensorMan;
    private Sensor accelerometer;

    public float[] mGravity;
    public float mAccel;
    public float mAccelCurrent;
    public float mAccelLast;
    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        opponentButton = (Button)findViewById(R.id.spockOpponent);
        playerButton = (Button)findViewById(R.id.spockPlayer);
        winnerText = (TextView)findViewById(R.id.winnerView);
        movesView = (TextView) findViewById(R.id.movesView);
        movesCounter = 0;
        Log.v("onCreate", "Oncreate");

        // Setup sensor
        sensorMan = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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

    public void gameButtonClicked (View view) {
        movesCounter += 1;
        movesView.setText("" + movesCounter);
        playerButton.setBackgroundColor(getResources().getColor(R.color.grey));
        playerButton.setTextColor(getResources().getColor(R.color.black));
        if (gameType == "single_player") {
            oponentChoice = aiOpponentChoice();
        }
        switch (view.getId()) {
            case R.id.spockPlayer:
                playerButton = (Button) findViewById(R.id.spockPlayer);
                playerButton.setBackgroundColor(getResources().getColor(R.color.blue));
                playerButton.setTextColor(getResources().getColor(R.color.white));
                if (oponentChoice == "scissors" || oponentChoice == "rock") {
                    winnerText.setText("You Win!");
                } else if (oponentChoice == "paper" || oponentChoice == "lizard") {
                    winnerText.setText("You Lose!");
                } else {
                    winnerText.setText("Play Again!");
                }
                Log.v("Spock Button", "Spock button pressed: " + aiOpponentChoice());
                break;
            case R.id.lizardPlayer:
                playerButton = (Button) findViewById(R.id.lizardPlayer);
                playerButton.setBackgroundColor(getResources().getColor(R.color.blue));
                playerButton.setTextColor(getResources().getColor(R.color.white));
                if (oponentChoice == "spock" || oponentChoice == "paper") {
                    winnerText.setText("You Win!");
                } else if (oponentChoice == "rock" || oponentChoice == "scissors") {
                    winnerText.setText("You Lose!");
                } else {
                    winnerText.setText("Play Again!");
                }
                Log.v("Lizard Button", "Lizard Button Pressed" + aiOpponentChoice());
                break;
            case R.id.rockPlayer:
                playerButton = (Button) findViewById(R.id.rockPlayer);
                playerButton.setBackgroundColor(getResources().getColor(R.color.blue));
                playerButton.setTextColor(getResources().getColor(R.color.white));
                if (oponentChoice == "scissors" || oponentChoice == "lizard") {
                    winnerText.setText("You Win!");
                } else if (oponentChoice == "paper" || oponentChoice == "spock") {
                    winnerText.setText("You Lose!");
                } else {
                    winnerText.setText("Play Again!");
                }
                Log.v("Rock players", "Rock button pressed" + aiOpponentChoice());
                break;
            case R.id.scissorsPlayer:
                playerButton = (Button) findViewById(R.id.scissorsPlayer);
                playerButton.setBackgroundColor(getResources().getColor(R.color.blue));
                playerButton.setTextColor(getResources().getColor(R.color.white));
                if (oponentChoice == "paper" || oponentChoice == "lizard") {
                    winnerText.setText("You Win!");
                } else if (oponentChoice == "spock" || oponentChoice == "rock") {
                    winnerText.setText("You Lose!");
                } else {
                    winnerText.setText("Play Again!");
                }
                Log.v("Scissors Player", "Scissors button pressed" + aiOpponentChoice());
                break;
            case R.id.paperPlayer:
                playerButton = (Button) findViewById(R.id.paperPlayer);
                playerButton.setBackgroundColor(getResources().getColor(R.color.blue));
                playerButton.setTextColor(getResources().getColor(R.color.white));
                if (oponentChoice == "rock" || oponentChoice == "spock") {
                    winnerText.setText("You Win!");
                } else if (oponentChoice == "lizard" || oponentChoice == "scissors") {
                    winnerText.setText("You Lose!");
                } else {
                    winnerText.setText("Play Again!");
                }
                Log.v("Paper Player", "Paper button pressed" + aiOpponentChoice());
                break;
        }
        if (winnerText.getText() == "You Win!") {
            playerWins();
        }


    }

    public String aiOpponentChoice() {
        opponentButton.setTextColor(getResources().getColor(R.color.black));
        opponentButton.setBackgroundColor(getResources().getColor(R.color.grey));
        Random rand = new Random();
        int test = (rand.nextInt(5) + 1);
        switch (test) {
            case 1:
                opponentButton = (Button) findViewById(R.id.spockOpponent);
                opponentButton.setBackgroundColor(getResources().getColor(R.color.blue));
                opponentButton.setTextColor(getResources().getColor(R.color.white));
                return "spock";
            case 2:
                opponentButton = (Button) findViewById(R.id.rockOpponent);
                opponentButton.setBackgroundColor(getResources().getColor(R.color.blue));
                opponentButton.setTextColor(getResources().getColor(R.color.white));
                return "rock";
            case 3:
                opponentButton = (Button) findViewById(R.id.paperOpponent);
                opponentButton.setBackgroundColor(getResources().getColor(R.color.blue));
                opponentButton.setTextColor(getResources().getColor(R.color.white));
                return "paper";
            case 4:
                opponentButton = (Button) findViewById(R.id.scissorsOpponent);
                opponentButton.setBackgroundColor(getResources().getColor(R.color.blue));
                opponentButton.setTextColor(getResources().getColor(R.color.white));
                return "scissors";
            case 5:
                opponentButton = (Button) findViewById(R.id.lizardOpponent);
                opponentButton.setBackgroundColor(getResources().getColor(R.color.blue));
                opponentButton.setTextColor(getResources().getColor(R.color.white));
                return "lizard";
        }
        Log.v("Random number", "Random num = " + test);
        return "Switch did not work.";
    }

    public void playerWins () {
        disableButton = (Button) findViewById(R.id.spockPlayer);
        disableButton.setEnabled(false);
        disableButton = (Button) findViewById(R.id.lizardPlayer);
        disableButton.setEnabled(false);
        disableButton = (Button) findViewById(R.id.rockPlayer);
        disableButton.setEnabled(false);
        disableButton = (Button) findViewById(R.id.scissorsPlayer);
        disableButton.setEnabled(false);
        disableButton = (Button) findViewById(R.id.paperPlayer);
        disableButton.setEnabled(false);
        Button playAgainButton = (Button)findViewById(R.id.playAgainButton);
        Button addToHighscoresButton = (Button) findViewById(R.id.addToHighscoresButton);
        playAgainButton.setVisibility(View.VISIBLE);
        addToHighscoresButton.setVisibility(View.VISIBLE);

    }

    public void playAgain (View view) {
        recreate();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v("SensorData", "In onresume");
        sensorMan.registerListener(GameActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorMan.unregisterListener(GameActivity.this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.v("Sensor", "sensor Changed");
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            Log.v("Sensor", "Inside of sensor type = accel");
            mGravity = event.values.clone();
            // Shake detection
            float x = mGravity[0];
            float y = mGravity[1];
            float z = mGravity[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = FloatMath.sqrt(x * x + y * y + z * z);
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            Log.v("SensorData", "mAccel = " + mAccel);
            // Make this higher or lower according to how much
            // motion you want to detect
            if(mAccel > 10){
                sensorMan.unregisterListener(GameActivity.this);
                sensorControler();
                Log.v("SensorData", "Detected Accel");
                registerSensor();
            }
        }

    }

    // Used to register the sensor after 5 seconds - This is to prevent shaking and triggering the sensor call 10100010 times
    public void registerSensor() {
        if (winnerText.getText() != "You Win!") {
            Runnable task = new Runnable() {
                public void run() {
                    sensorMan.registerListener(GameActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                }
            };
            worker.schedule(task, 2, TimeUnit.SECONDS);
        } else {
            Log.v("registerSensor", "Player has won - Game Done");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // required method
        Log.v("SensorData", "onAccuracyChanged...");
    }

    public void sensorControler() {
        Random rand = new Random();
        int test = (rand.nextInt(5) + 1);
        switch (test) {
            case 1:
                gameButtonClicked(findViewById(R.id.rockPlayer));
                break;
            case 2:
                gameButtonClicked(findViewById(R.id.paperPlayer));
                break;
            case 3:
                gameButtonClicked(findViewById(R.id.scissorsPlayer));
                break;
            case 4:
                gameButtonClicked(findViewById(R.id.lizardPlayer));
                break;
            case 5:
                gameButtonClicked(findViewById(R.id.spockPlayer));
                break;
        }
    }

    public void addToHighscores (View view) {
        // Start new add to highscores activity
        Intent intent = new Intent(this, AddToHighscores.class);
        intent.putExtra("playerMoves", movesCounter);
        startActivityForResult(intent, 1);
    }
}
