package com.example.c_room_viewmodel_livedata;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//1.setData方法

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<Users> usersList;
    private Context mContext;


    //新增資料
    public void setData(List<Users> users) {
        this.usersList = users;
        notifyDataSetChanged();
        Log.v(MainActivity.TAG, "UserAdapter->setData() ");
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        Log.v(MainActivity.TAG, "onCreateViewHolder-> ");
        return new UserViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_users, parent, false));
    }

    @Override
    public void onBindViewHolder(UserAdapter.UserViewHolder holder, int position) {
        Users users = usersList.get(position);
        String userName = users.getUsername();
        holder.tvUsers.setText(userName);
        Log.v(MainActivity.TAG, "onBindViewHolder-> ");
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUsers;

        public UserViewHolder(View itemView) {
            super(itemView);
            tvUsers = itemView.findViewById(R.id.tvUserRow);
        }
    }
}
