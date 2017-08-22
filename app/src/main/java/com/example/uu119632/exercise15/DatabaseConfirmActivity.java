package com.example.uu119632.exercise15;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * 確認画面
 *
 * @author :ryo.yamada
 * @since :1.0 :2017/08/23
 */
public class DatabaseConfirmActivity extends AppCompatActivity {

    private static final String ID_KEY = "ID";

    /**
     * onCreate
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        TextView textName = (TextView) findViewById(R.id.text_name);
        TextView textGender = (TextView) findViewById(R.id.text_gender);
        TextView textMailAddress = (TextView) findViewById(R.id.text_mail);
        TextView textMagazine = (TextView) findViewById(R.id.text_mail_magazine);

        MemberDatabaseHelper memberDatabaseHelper = new MemberDatabaseHelper(this);
        SQLiteDatabase db = memberDatabaseHelper.getReadableDatabase();

        final MemberDao memberDao = new MemberDao(db);
        MemberDto memberDto = new MemberDto();

        for (MemberDto dto : memberDao.findAll()) {
//            memberDto = dto;
            Log.d("size", "userID:" + dto.getId());
            Log.d("size", "userName:" + dto.getName());
        }
        memberDto = memberDao.findById(memberDao.findAll().size());
        textName.setText(memberDto.getName());
        textGender.setText(memberDto.getGender());
        textMailAddress.setText(memberDto.getMailAddress());
        textMagazine.setText(memberDto.getMailMagazine());

        Log.d("size", "DBSize:" + String.valueOf(memberDao.findAll().size()));

        // 編集ボタン押下時の処理
        final MemberDto finalMemberDto = memberDto;
        findViewById(R.id.button_edit).setOnClickListener(new View.OnClickListener() {

            /**
             * 編集画面に画面遷移
             * @param view view
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DatabaseConfirmActivity.this, DatabaseEditActivity.class);
                intent.putExtra(ID_KEY, finalMemberDto.getId());
                startActivity(intent);
            }
        });
    }
}
