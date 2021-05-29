package comtic.tac.toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 0 -> Player O
    // 1 -> Player X
    int currentPlayer = 0;
    int playCount=0;

    // 0 -> blank
    // 1 -> O
    // 2 -> X
    int gridArray[] = {0,0,0,0,0,0,0,0,0};
    int winIndex[][] = {{0,1,2}, {3,4,5}, {6,7,8},
                        {0,3,6}, {1,4,7}, {2,5,8},
                        {0,4,8}, {2,4,6}};
    TextView status;
    boolean gameActiveStatus=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        status = findViewById(R.id.messageBox);
        status.setText("Player O's turn. Tap to play");
    }

    public void playerTap(View view) {
        if(gameActiveStatus==false) {
            resetGame();
            return;
        }

        ImageView img = (ImageView) view;
        int tappedCell = Integer.parseInt(img.getTag().toString());

        // check if grid contains value
        if(gridArray[tappedCell]!=0) {
            Toast.makeText(this, "Already Contains Input", Toast.LENGTH_SHORT).show();
            return;
        }

        img.setTranslationY(-1000f);
        // Player O's turn
        if(currentPlayer == 0){
            playCount++;
            img.setImageResource(R.drawable.o);
            currentPlayer = 1;
            gridArray[tappedCell]=1;
            status.setText("Player X's turn. Tap to play");
            if(checkWin() == false)
            checkDraw();
        }
        // Player X's turn
        else {
            playCount++;
            img.setImageResource(R.drawable.x);
            currentPlayer = 0;
            gridArray[tappedCell]=2;
            status.setText("Player O's turn. Tap to play again");
            if(checkWin()==false)
            checkDraw();
        }

        img.animate().translationYBy(1000f).setDuration(300);
    }

    private void checkDraw() {
        if(playCount==9) {
            status.setText("Game Draw !!! Tap to Play again");
            gameActiveStatus=false;
        }

    }

    private void resetGame() {
        gameActiveStatus=true;
        currentPlayer = 0;
        playCount=0;
        status.setText("Player O's turn. Tap to play again");
        for(int  i=0; i<9; i++) {
            gridArray[i]=0;
        }
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);
    }

    private boolean checkWin() {
        for(int winPos[] : winIndex) {
            if(gridArray[winPos[0]]==gridArray[winPos[1]] && gridArray[winPos[1]]==gridArray[winPos[2]] ) {
                if(gridArray[winPos[0]]==1) {
                    status.setText("Player O Wins !!! \n Tap to Reset");
                    gameActiveStatus=false;
                    return true;
                }
                else {
                    if(gridArray[winPos[0]]==2){
                        status.setText("Player X Wins !!! \n Tap to Reset");
                        gameActiveStatus=false;
                        return true;
                    }
                }
            }
        }
        return false;
    }
}