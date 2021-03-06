package com.bosic.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;

/**
 * 计算器
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        TextView formula = (TextView) findViewById(R.id.formula_area);
        String content = formula.getText().toString();

        TextView result = (TextView) findViewById(R.id.result_area);

        switch (view.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_add:
            case R.id.btn_sub:
            case R.id.btn_mul:
            case R.id.btn_div:
            case R.id.btn_dot:
                Button btn = (Button) view;
                String added = btn.getText().toString();

                content += added;
                formula.setText(content);
                break;
            case R.id.btn_c:
                formula.setText("");
                result.setText("");
                break;
            case R.id.btn_del:
                if (content.length() > 0) {
                    content = content.substring(0, content.length() - 1);
                    formula.setText(content);
                }
                break;
            case R.id.btn_equ:
                try {
                    Symbols s = new Symbols();
                    double res = s.eval(content);
                    result.setText(String.valueOf(res));

                    Animation fadeIn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
                    result.startAnimation(fadeIn);

                    Animation fadeOut = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_out);
                    formula.startAnimation(fadeOut);
                    fadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            TextView formula = (TextView) findViewById(R.id.formula_area);
                            formula.setText("");
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                } catch (SyntaxException e) {
                    Toast.makeText(MainActivity.this, "错误！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
