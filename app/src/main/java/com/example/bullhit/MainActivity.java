package com.example.bullhit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bullhit.Bullhit;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    View newitem;
    Button Guess;
    LinearLayout newLayout, main;
    Integer counterturn = 1;
    EditText Result;
    Bullhit hit;
    TextView numtV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newLayout = findViewById(R.id.inputLayout);
        main = findViewById(R.id.main);
        hit = new Bullhit();
        Guess = newLayout.findViewById(R.id.inputB);
        Result = newLayout.findViewById(R.id.inputNumET);
        Guess.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == Guess) {
            String rsl = Result.getText().toString();
            if (rsl.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else if (rsl.length() != 4) {
                Toast.makeText(MainActivity.this, "Please enter a 4 digit number", Toast.LENGTH_SHORT).show();
            }
            else if(rsl.charAt(0) == rsl.charAt(1) || rsl.charAt(0) == rsl.charAt(2) || rsl.charAt(0) == rsl.charAt(3) ||
                    rsl.charAt(1) == rsl.charAt(2) ||  rsl.charAt(3) == rsl.charAt(2) ||  rsl.charAt(3) == rsl.charAt(1)){
                Toast.makeText(MainActivity.this, "Please enter 4 diffrent numbers", Toast.LENGTH_SHORT).show();
            }
            else {

                newLayout.removeView(Result);
                newLayout.removeView(Guess);
                newitem = getLayoutInflater().inflate(R.layout.newitem, null);
                String num = Result.getText().toString();
                numtV = new TextView(newitem.findViewById(R.id.numTv).getContext());
                numtV.setText(num);
                Result.setText("");
                newLayout.addView(numtV);

                for (int i=0; i<hit.Bull(num); i++){
                    ImageView imageView = new ImageView(newitem.findViewById(R.id.imageView).getContext());
                    imageView.setAdjustViewBounds(true);
                    imageView.setMaxHeight(100);
                    imageView.setMaxWidth(100);
                    imageView.setImageResource(R.drawable.red);
                    newLayout.addView(imageView);
                }
                for (int j=0; j<hit.Hit(num); j++){
                    ImageView imageView = new ImageView(newitem.findViewById(R.id.imageView).getContext());
                    imageView.setAdjustViewBounds(true);
                    imageView.setMaxHeight(100);
                    imageView.setMaxWidth(100);
                    imageView.setImageResource(R.drawable.purple);
                    newLayout.addView(imageView);
                }

                newLayout.addView(newitem);
                newLayout = new LinearLayout(main.findViewById(R.id.inputLayout).getContext());

                if (hit.Bull(num)<4 && counterturn<8) {
                        newLayout.addView(Result);
                        newLayout.addView(Guess);
                        main.addView(newLayout);
                }
                else if (hit.Bull(num)==4){
                    numtV = new TextView(newitem.findViewById(R.id.numTv).getContext());
                    numtV.setText("You Win! after " + counterturn + " guesses" );
                    newLayout.addView(numtV);
                    main.addView(newLayout);
                }
                else if(counterturn==8&& hit.Bull(num) <4) {
                    newLayout = new LinearLayout(main.findViewById(R.id.inputLayout).getContext());
                    numtV = new TextView(newitem.findViewById(R.id.numTv).getContext());
                    numtV.setText("You Lose :( " + "\n" + "The num is: "+hit.numberH()+ " ");
                    newLayout.addView(numtV);
                    main.addView(newLayout);
                }
                counterturn++;
            }
        }
    }
}

