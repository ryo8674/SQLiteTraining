package com.example.uu119632.exercise15;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLiteOpenHelper継承クラス
 *
 * @author :ryo.yamada
 * @since :1.0 :2017/08/23
 */
class MemberDatabaseHelper extends SQLiteOpenHelper {

    /**
     * DB名
     */
    private static final String DB_NAME = "member_db";

    /**
     * DB Version
     */
    private static final int DB_VERSION = 1;

    /**
     * テーブルを作成するSQL
     */
    private static final String CREATE_TABLE_SQL = "create table member"
            + "(_id integer primary key,"
            + "seimei text not null,"
            + "seibetsu text not null,"
            + "address text not null,"
            + "mailmaga text)";

    /**
     * テーブルを削除するSQL
     */
    private static final String DROP_TABLE_SQL = "drop table if exists member";

//    private static final String ALTER_COLUMN_SQL = "alter table member add mailmaga text";

    /**
     * コンストラクタ
     *
     * @param context context
     */
    MemberDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * DBを作成するメソッド
     *
     * @param sqLiteDatabase sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);
    }

    /**
     * データベースが既に存在していて、
     * そのデータベースのバージョンがコンストラクタで指定されたバージョンと異なる時に呼ばれる
     *
     * @param sqLiteDatabase sqLiteDatabase
     * @param oldVersion     oldVersion
     * @param newVersion     newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (newVersion != oldVersion) {
            sqLiteDatabase.execSQL(DROP_TABLE_SQL);
            onCreate(sqLiteDatabase);
//            sqLiteDatabase.execSQL(ALTER_COLUMN_SQL);
        }
    }
}
