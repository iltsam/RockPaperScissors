package au.edu.jcu.samuel.rockpaperscissors;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class GameActivity extends ActionBarActivity {
    public String gameType = "single_player";
    public String oponentChoice;
    public TextView winnerText;
    public Button opponentButton;
    public Button playerButton;
    public Button disableButton;
    public TextView movesView;
    public int movesCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        opponentButton = (Button)findViewById(R.id.spockOpponent);
        playerButton = (Button)findViewById(R.id.spockPlayer);
        winnerText = (TextView)findViewById(R.id.winnerView);
        movesView = (TextView) findViewById(R.id.movesView);
        movesCounter = 0;
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
    }
}
