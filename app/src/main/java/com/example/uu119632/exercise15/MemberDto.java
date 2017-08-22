package com.example.uu119632.exercise15;

/**
 * MemberDtoクラス
 *
 * @author :ryo.yamada
 * @since :1.0 :2017/08/23
 */
class MemberDto {

    private int id;
    private String name;
    private String gender;
    private String mailAddress;
    private String mailMagazine;

    /**
     * 空のコンストラクタ
     */
    MemberDto() {
    }

    /**
     * IDを取得する
     *
     * @return id
     */
    int getId() {
        return id;
    }

    /**
     * IDを設定する
     *
     * @param id id
     */
    void setId(int id) {
        this.id = id;
    }

    /**
     * 名前を取得する
     *
     * @return name
     */
    String getName() {
        return name;
    }

    /**
     * 名前を設定する。
     *
     * @param name name
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * 性別を取得する
     *
     * @return gender
     */
    String getGender() {
        return gender;
    }

    /**
     * 性別を設定する
     *
     * @param gender gender
     */
    void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * メールアドレスを取得する
     *
     * @return mailAddress
     */
    String getMailAddress() {
        return mailAddress;
    }

    /**
     * メールアドレスを設定する
     *
     * @param mailAddress mailAddress
     */
    void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    /**
     * メールマガジンの受け取り可否を取得する
     *
     * @return mailMagazine
     */
    String getMailMagazine() {
        return mailMagazine;
    }

    /**
     * メールマガジンの受け取り可否を設定する
     *
     * @param mailMagazine mailMagazine
     */
    void setMailMagazine(String mailMagazine) {
        this.mailMagazine = mailMagazine;
    }
}
