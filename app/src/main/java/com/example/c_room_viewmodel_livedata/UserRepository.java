package com.example.c_room_viewmodel_livedata;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Database;

import java.util.List;

public class UserRepository {
    private UserDataBase userDataBase;
    private UserDao userDao;
    private LiveData<List<Users>> userLists; //Db裡所有users的資料


    //1.建構式取得userDataBase , userDao ,userLists ,物件或資料
    public UserRepository(Application application) {
        userDataBase = UserDataBase.getDataBase(application); //取得DataBase的物件實體
        userDao = userDataBase.userDao(); //取得DataBase物件實體裡面的抽象UserDao接口
        userLists = userDao.getAllUsers(); //取得UserDao接口裡面的,取得User表裡的所有資料用Query方式
    }

    //2.取得UserList資料,給ViewModel類別直接取得資料
    public LiveData<List<Users>> getUserLists() {
        Log.v(MainActivity.TAG,"UserRepository -> getUserLists:" );
        return userDao.getAllUsers();
    }

    //3.執行序方法去Insert使用者類別
    public void insertUsers(final Users users) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                userDao.insertUsers(users);
                Log.v(MainActivity.TAG,"UserRepository -> doInBackground:" + users.getUsername());
                return null;
            }
        }.execute();
    }
}
