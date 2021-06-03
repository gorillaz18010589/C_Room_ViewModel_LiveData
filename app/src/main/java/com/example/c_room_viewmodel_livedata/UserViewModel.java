package com.example.c_room_viewmodel_livedata;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;


import java.util.List;

//1.繼承AndroidViewModel : 可取得全域變數application
public class UserViewModel extends AndroidViewModel {

    //2.宣告userRepository物件,liveDataUserLists
    UserRepository userRepository;
    LiveData<List<Users>> liveDataUserLists;

    //3.取得userRepository,liveDataUserLists的資料
    public UserViewModel( Application application) {
        super(application);
        this.userRepository = new UserRepository(application);
        this.liveDataUserLists = userRepository.getUserLists();
    }

    //3.取得所有使用者方法
    public LiveData<List<Users>> getAllUsers() {
        Log.v(MainActivity.TAG,"UserViewModel.getAllUsers:" + userRepository.getUserLists());
        return userRepository.getUserLists();
    }

    //4.新增使用者到Db方法
    public void setUsers(Users users) {
        userRepository.insertUsers(users);
        Log.v(MainActivity.TAG,"UserViewModel.insertUsers:" + users.getUsername());
    }


}
