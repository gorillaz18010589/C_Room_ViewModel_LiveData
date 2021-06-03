package com.example.c_room_viewmodel_livedata;
//4.建立@Database 繼承RoomDatabase :創建數據庫,裡面包含DAO,Entity

//創建Room database包括三個步驟：
// 1、創建繼承RoomDatabase的抽像類。
// 2、在繼承的類前使用註解@Database。
// 3申明數據庫結構的Entity，並且設置數據庫的版本號。
// 4.宣告抽象類別UserDao
// 5.創建UserDatabase物件實體


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//2、在繼承的類前使用註解@Database。 -> 3申明數據庫結構的Entity，並且設置數據庫的版本號。
//@Database(1.entities:要加入到資料庫的bean, 2.version:版本數 3.是否要匯出表格)
@Database(entities = Users.class, version = 1, exportSchema = false)
public abstract class UserDataBase extends RoomDatabase { //1、創建繼承RoomDatabase的抽像類。


    // 4.宣告抽象類別UserDao,讓別人去LiveData那邊去時做出來
    public abstract UserDao userDao();

    public static UserDataBase instance;

    //5.創建UserDatabase物件實體
    public static UserDataBase getDataBase(Context context) {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context, //1.cntent
                            UserDataBase.class, //2.要創建的資料庫類別
                            "user.db" //3.資料庫檔案名稱
                    ).build(); //回傳擬建好的資料庫
                }
            }
        }
        return instance;
    }


}
