package comtic.tac.toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 0 -> Player O
    // 1 -> Player X
    int currentPlayer = 0;

    // 0 -> blank
    // 1 -> O
    // 2 -> X
    int gridArray[] = {0,0,0,0,0,0,0,0,0};
    int winIndex[][] = {{0,1,2}, {3,4,5}, {6,7,8},
                        {0,3,6}, {1,4,7}, {2,5,8},
                        {0,4,8}, {2,4,6}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedCell = Integer.parseInt(img.getTag().toString());

        // check if grid contains value
        if(gridArray[tappedCell]!=0) {
            Toast.makeText(this, "Already Contains Input", Toast.LENGTH_SHORT).show();
            return;
        }

        // Player O's turn
        if(currentPlayer == 0){
            img.setImageResource(R.drawable.o);
            currentPlayer = 1;
            gridArray[tappedCell]=1;

        }
        // Player X's turn
        else {
            img.setImageResource(R.drawable.x);
            currentPlayer = 0;
            gridArray[tappedCell]=2;
        }
    }
}