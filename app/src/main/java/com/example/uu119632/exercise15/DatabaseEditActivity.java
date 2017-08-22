package com.example.uu119632.exercise15;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * 編集画面
 *
 * @author :ryo.yamada
 * @since :1.0 :2017/08/23
 */
public class DatabaseEditActivity extends AppCompatActivity {

    private static final String NOT_RECEIVED_MESSAGE = "受けとらない";
    private static final String RECEIVED_MESSAGE = "受けとる";
    private static final String ERROR_MESSAGE = "エラーが発生しました。";
    private static final String ID_KEY = "ID";
    private static final int DEFAULT_VALUE = -1;

    private String gender;
    private String received;

    /**
     * onCreate
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        MemberDatabaseHelper memberDatabaseHelper = new MemberDatabaseHelper(this);
        final SQLiteDatabase db = memberDatabaseHelper.getWritableDatabase();

        final MemberDao memberDao = new MemberDao(db);
        final MemberDto memberDto = new MemberDto();

        final EditText editName = (EditText) findViewById(R.id.edit_name);
        RadioGroup radioGender = (RadioGroup) findViewById(R.id.radio_gender);
        final EditText editMailAddress = (EditText) findViewById(R.id.edit_mail);
        CheckBox checkMagazine = (CheckBox) findViewById(R.id.checkbox);

        // 性別選択
        radioGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             * チェックされたradioButtonのTextを変数に格納
             *
             * @param radioGroup radioGroup
             * @param id radioButtonのId
             */
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                RadioButton rb = (RadioButton) findViewById(id);
                gender = rb.getText().toString();
            }
        });

        // メルマガの受け取り可否選択
        checkMagazine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            /**
             * checkFlgで変数に格納する文字列を選択
             *
             * @param compoundButton compoundButton
             * @param checkFlg checkFlg
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checkFlg) {
                if (checkFlg) {
                    received = RECEIVED_MESSAGE;
                } else {
                    received = NOT_RECEIVED_MESSAGE;
                }
            }
        });

        // 確定ボタン押下時の処理
        findViewById(R.id.button_determine).setOnClickListener(new View.OnClickListener() {
            /**
             * Preferenceに保存し、確認画面に画面遷移
             * @param view view
             */
            @Override
            public void onClick(View view) {
                memberDto.setName(editName.getText().toString());
                memberDto.setGender(gender);
                memberDto.setMailAddress(editMailAddress.getText().toString());
                memberDto.setMailMagazine(received);

                //トランザクション開始
                db.beginTransaction();

                int tmpMemberId = getIntent().getIntExtra(ID_KEY, DEFAULT_VALUE);
                try {
                    if (tmpMemberId != -1) {
                        memberDao.update(tmpMemberId, memberDto);

                    } else {
                        memberDao.insert(memberDto);
                    }
                    // トランザクション終了
                    db.setTransactionSuccessful();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
                } finally {
                    db.endTransaction();
                }
                db.close();

                Intent intent = new Intent(DatabaseEditActivity.this, DatabaseConfirmActivity.class);
                intent.putExtra(ID_KEY, memberDto.getId());
                startActivity(intent);
            }
        });
    }
}
