package com.example.c_room_viewmodel_livedata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/*
 *
 * @Entity:設定為表,裡面的屬性都會變成欄位
 * @Entity(tableName = "user_table"):設定表明稱為
 * @PrimaryKey(autoGenerate = true):設定主建,並且自動產生
 * @ColumnInfo(name = "tit_le"):可以改變欄位名屬性名稱,如果沒寫,預設維銓小寫
 * @Ignore:可以排除此屬性,讓屬性部會變成欄位
 * */
/*A.特性:
* 1，一個實體對象代表數據表中的一行，一個實體類代表一張數據表。
 2，實體中的成員變量都是數據表中的列。
 3，一個Java類定義成實體只要加上實體註解就可以了。
* */
/*B.定義主建
 *每一個實體至少定義一個主鍵（主鍵），哪怕實體中只有一個變量也指向這個變量定義主體鍵，* 在房間數據庫中使用註解@PrimaryKey來定義主鍵，
 * @ PrimaryKey的使用方式有兩種一種是 在類變量前面加，如果主鍵比較複雜可以加在@Entity註解的後面。
 * */

@Entity(tableName = "users") //設定表明稱
public class Users {

    @PrimaryKey(autoGenerate = true)//設置是否使ID自動累加
    private int id;

    @ColumnInfo(name = "username")
    private  String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
