package com.example.uu119632.exercise15;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * MemberDaoクラス
 *
 * @author :ryo.yamada
 * @since :1.0 :2017/08/23
 */
class MemberDao {

    private static final String SELECTION = "_id = ";
    private static final String TABLE_NAME = "member";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "seimei";
    private static final String COLUMN_GENDER = "seibetsu";
    private static final String COLUMN_MAIL_ADDRESS = "address";
    private static final String COLUMN_MAIL_MAGAZINE = "mailmaga";
    private static final String COLUMN_ADDRESS = "residence";
    private static final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_GENDER, COLUMN_MAIL_ADDRESS, COLUMN_MAIL_MAGAZINE, COLUMN_ADDRESS};

    private final SQLiteDatabase db;

    /**
     * コンストラクタ
     *
     * @param db db
     */
    MemberDao(SQLiteDatabase db) {
        this.db = db;
    }

    /**
     * 1レコード分のデータを挿入する
     *
     * @param member member
     */
    void insert(MemberDto member) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, member.getName());
        values.put(COLUMN_GENDER, member.getGender());
        values.put(COLUMN_MAIL_ADDRESS, member.getMailAddress());
        values.put(COLUMN_MAIL_MAGAZINE, member.getMailMagazine());
        values.put(COLUMN_ADDRESS, member.getAddress());

        db.insert(TABLE_NAME, null, values);
    }

    /**
     * 更新対象のレコードを更新する
     *
     * @param id     id
     * @param member member
     */
    void update(int id, MemberDto member) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, member.getName());
        values.put(COLUMN_GENDER, member.getGender());
        values.put(COLUMN_MAIL_ADDRESS, member.getMailAddress());
        values.put(COLUMN_MAIL_MAGAZINE, member.getMailMagazine());
        values.put(COLUMN_ADDRESS, member.getAddress());
        String whereClause = SELECTION + id;

        db.update(TABLE_NAME, values, whereClause, null);
    }

    /**
     * 全件検索を行う
     * IDで昇順ソート
     *
     * @return 全件検索結果のリスト
     */
    List<MemberDto> findAll() {
        List<MemberDto> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, COLUMNS, null, null, null, null, COLUMN_ID);

        while (cursor.moveToNext()) {
            MemberDto member = new MemberDto();
            member.setId(cursor.getInt(0));
            member.setName(cursor.getString(1));
            member.setGender(cursor.getString(2));
            member.setMailAddress(cursor.getString(3));
            member.setMailMagazine(cursor.getString(4));
            member.setAddress(cursor.getString(5));

            list.add(member);
        }
        return list;
    }

}
