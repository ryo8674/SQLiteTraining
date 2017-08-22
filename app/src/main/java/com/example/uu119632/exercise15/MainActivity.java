package com.example.uu119632.exercise15;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * メイン画面
 *
 * @author :ryo.yamada
 * @since :1.0 :2017/08/23
 */
public class MainActivity extends AppCompatActivity {

    /**
     * onCreate
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 登録ボタン押下時の処理
        findViewById(R.id.button_register).setOnClickListener(new View.OnClickListener() {
            /**
             * 編集画面に画面遷移
             * @param view view
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DatabaseEditActivity.class);
                startActivity(intent);
            }
        });

        // 確認ボタン押下時の処理
        findViewById(R.id.button_confirm).setOnClickListener(new View.OnClickListener() {

            /**
             * 確認画面に画面遷移
             * @param view view
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DatabaseConfirmActivity.class);
                startActivity(intent);
            }
        });
    }
}
