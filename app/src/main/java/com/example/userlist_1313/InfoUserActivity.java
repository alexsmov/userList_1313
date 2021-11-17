package com.example.userlist_1313;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoUserActivity extends AppCompatActivity {
    TextView fioTextView;
    TextView phoneTextView;
    Button editUserBtn;
    Button deleteUserBtn;
    Users users;
    User user;
    int userPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);

        fioTextView = findViewById(R.id.fioTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        editUserBtn = findViewById(R.id.editUserBtn);
        deleteUserBtn = findViewById(R.id.deleteUserBtn);
        editUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoUserActivity.this, FormUserActivity.class);
                intent.putExtra("userPosition", userPosition);
                startActivity(intent);
            }
        });
        deleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                users.deleteUser(user.getUuid());
                onBackPressed();
            }
        });
    }
    @Override
    public void onResume(){
        super.onResume();
        userPosition = getIntent().getIntExtra("userPosition", 0);
        users = new Users(InfoUserActivity.this);
        user = users.getUserList().get(0);
        String fio = user.getUserLastName()+" "+user.getUserName();
        fioTextView.setText(fio);
        phoneTextView.setText(user.getPhone());
    }
}
