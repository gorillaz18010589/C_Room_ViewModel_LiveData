package com.example.c_room_viewmodel_livedata;

/*
*在DAO（數據訪問對象）中，可以使用SQL語句進行對數據庫的操作和將這些語句與Java中方法關聯，調用編譯器會檢查SQL語句並通過註解生成對應的查詢語句，例如@Insert。
注意：
1，DAO必現是抽像類或接口
2，所有的查詢語句必須在單獨的線程裡面執行。
*
* */

/*
 * @Dao:DAO化,可以適用SQL語法,並且自動編譯檢查是否正確
 * @Insert:會將所有的參數加入到DB
 * @Update:
 * @Delete:刪除指定的資料
 * @Query:每個查詢都會再編譯期間,判斷是否正確
 * */

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


//1.UserDao宣告@Dao
@Dao
public interface UserDao { //必須是接口

    //2.將指定Users加入到DB的接口
    @Insert()
    void insertUsers(Users users);

    @Query("SELECT * FROM users")
    LiveData<List<Users>> getAllUsers();


}
