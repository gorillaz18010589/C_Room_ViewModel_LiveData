package com.example.c_room_viewmodel_livedata;
//1.新增ROOM ,Mvvm Lib
//2.創建Users (Db中的表)
//3.UserDao interface (訪問DB的接口 類似insert)
//4.room database有繼承room database,且可以指定我創的user表
//5. UserRepository -> LiveData ,取得data
//6.創建ViewModel類別
//7.在activity裡取得ViewModel物件實體,使用取得資料並用observe監聽資料狀態變化
//8.刻畫面
//9.寫UserAdapter
//10.


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private UserAdapter userAdapter;
    private RecyclerView recyclerView;
    private Button btnNewUser;
    public final static String TAG ="hank";
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        //10.Adapter設定
        userAdapter = new UserAdapter();
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));//加上底線
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //7.取得ViewModel物件實體,使用取得資料並用observe監聽資料狀態變化
        userViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, new Observer<List<Users>>() {
            @Override
            public void onChanged(List<Users> users) {
                //11.當有Users資料變化且大於0時,新增Users並且設定Adapter
                Log.v(TAG,"onChanged -> users:" +users );
                if (users.size() > 0) {
                    recyclerView.setAdapter(userAdapter);
                    userAdapter.setData(users);
                    Log.v(TAG,"onChanged -> users:" + users.get(0).getUsername());
                }
            }
        });
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        btnNewUser = findViewById(R.id.btnNewUser);
        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser(MainActivity.this);
            }
        });
    }

    //12.新增彈窗User,使用者輸入儲存DB
    private void addUser(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View alertView = LayoutInflater.from(context).inflate(R.layout.alert_add_user, null);
        Button btnAddUser = alertView.findViewById(R.id.btnAddUser);
        EditText editUser = alertView.findViewById(R.id.editUser);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //你輸入的設定到UserName上,Users改變會呼叫
                Users users = new Users();
                users.setUsername(editUser.getText().toString());
                userViewModel.setUsers(users); //將你輸入的User Insert到DB
                Log.v(TAG, "addUser -> users:" + users.getUsername());
            }
        });

        builder.setView(alertView);
        alertDialog = builder.create();
        alertDialog.show();
    }
}