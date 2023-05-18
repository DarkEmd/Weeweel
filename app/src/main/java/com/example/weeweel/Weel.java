package com.example.weeweel;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Weel extends AppCompatActivity {
    private TextView tvResult;

    private TextView results;
    private ImageView rul;
    private Random random;
    private int old_deegre = 0;
    private int deegre = 0;
    private static final float FACTOR = 4.86f;
    private String[] numbers = {"32 GREEN","15 BLACK","19 GREEN","4 BLACK",
            "21 GREEN","2 BLACK","25 GREEN","17 BLACK", "34 GREEN",
            "6 BLACK","27 GREEN","13 BLACK","36 GREEN","11 BLACK","30 GREEN",
            "8 BLACK","23 GREEN","10 BLACK","5 GREEN","24 BLACK","16 GREEN","33 BLACK",
            "1 GREEN","20 BLACK","14 GREEN","31 BLACK","9 GREEN","22 BLACK","18 GREEN",
            "29 BLACK","7 GREEN","28 BLACK","12 GREEN","35 BLACK","3 GREEN","26 BLACK","EMPTINESS"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weel);
        init();
    }

    public void onClickStart(View view)
    {
        old_deegre = deegre % 360;
        deegre = random.nextInt(3600) + 720;
        RotateAnimation rotate = new RotateAnimation(old_deegre,deegre,RotateAnimation.RELATIVE_TO_SELF,
                0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        rotate.setDuration(3600);
        rotate.setFillAfter(true);
        rotate.setInterpolator(new DecelerateInterpolator());
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                tvResult.setText("");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tvResult.setText(getResult(360 - (deegre % 360)));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        rul.startAnimation(rotate);

    }
    private void init()
    {
        tvResult = findViewById(R.id.tvResult);
        rul = findViewById(R.id.rul);
        random = new Random();

    }
    private String getResult(int deegre)
    {
        String text = "";

        int factor_x = 1;
        int factor_y = 3;
        for(int i = 0;i < 37; i++){
            if(deegre >= (FACTOR * factor_x) && deegre < (FACTOR * factor_y))
            {
                text = numbers[i];
            }
            factor_x += 2;
            factor_y += 2;
        }
        if(deegre >= (FACTOR * 73) && deegre < 360 || deegre >= 0 && deegre < (FACTOR * 1)) {
            text = numbers[numbers.length - 1];

        }

        return text;
    }
}