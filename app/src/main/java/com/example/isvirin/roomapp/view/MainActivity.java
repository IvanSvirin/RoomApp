package com.example.isvirin.roomapp.view;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.isvirin.roomapp.R;
import com.example.isvirin.roomapp.RoomApp;
import com.example.isvirin.roomapp.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<User> users = new ArrayList<>();
    public static ArrayList<User> usersForList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateUsers();
        writeUsers();
        showUsersList();

    }

    private void showUsersList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                usersForList = (ArrayList<User>) RoomApp.db.userDao().getAll();
            }
        }).start();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment, new UserListFragment()).commit();
    }

    private void writeUsers() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RoomApp.db.userDao().insertAll(users);
            }
        }).start();
    }

    private void generateUsers() {
        for (int i = 0; i < 100; i++) {
            users.add(new User("first_name_" + i, "last_name_" + i));
        }
    }
}
