package com.mobifest.balloonsmash.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mobifest.balloonsmash.R;

import java.util.Random;

public class MainActivity extends Activity {
    static int count = 0;

    private Boolean image_1_trap = false;
    private Boolean image_2_trap = false;
    private Boolean image_3_trap = false;

    private Boolean exit = false;

    ImageView imageButton_1;
    ImageView imageButton_2;
    ImageView imageButton_3;

    final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton_1 = (ImageView) findViewById(R.id.image_balloon_1);
        imageButton_2 = (ImageView) findViewById(R.id.image_balloon_2);
        imageButton_3 = (ImageView) findViewById(R.id.image_balloon_3);

        animation(imageButton_1, random.nextInt(20000) + 15000);
        animation(imageButton_2, random.nextInt(20000) + 15000);
        animation(imageButton_3, random.nextInt(20000) + 15000);

        imageButton_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!image_1_trap) {
                    imageButton_1.setVisibility(View.INVISIBLE);
                    add_counter();

                } else {
                    subtract_counter();
                }
            }
        });

        imageButton_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!image_2_trap) {
                    imageButton_2.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, "Good Job! Score is : " + count, Toast.LENGTH_SHORT).show();
                    add_counter();
                } else {
                    subtract_counter();
                }
            }
        });

        imageButton_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!image_3_trap) {
                    imageButton_3.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, "Good Job! Score is : " + count, Toast.LENGTH_SHORT).show();
                    add_counter();
                } else {
                    subtract_counter();
                }
            }
        });
    }

    private void animation(ImageView imageView, long duration) {
        /*moveDownToUpRight = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -10f);
        moveDownToUpRight.setDuration(duration);
        moveDownToUpRight.setFillAfter(true);
        imageView.startAnimation(moveDownToUpRight);*/
        imageView.animate().translationY(-750).setDuration(duration).start();
    }

    private void add_counter() {
        count++;
        if (count > 20) {
            Toast.makeText(this, "Congratulations! Ready for Next Level?", Toast.LENGTH_LONG).show();
            secondLevel();
        }
    }

    private void subtract_counter() {
        count--;
        Toast.makeText(this, "Oops! You lost a point", Toast.LENGTH_SHORT).show();
    }

    public void secondLevel() {

        count = 0;

    }

    public void onBackPressed() {
        if (exit) {
            finish();
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }

}
