package com.example.tickettap.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tickettap.DisplayData.DisplayUser;
import com.example.tickettap.NFC.NFCSenderActivity;
import com.example.tickettap.R;

public class AdminPanelActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        findViewById(R.id.deleteUserBtn).setOnClickListener(this);
        findViewById(R.id.createUserBtn).setOnClickListener(this);
        findViewById(R.id.editBtn).setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createUserBtn:

                startActivity(new Intent(this, CreateNewUserActivity.class));
                break;

            case R.id.deleteUserBtn:
                startActivity(new Intent(this, DisplayUser.class));
                break;

            case R.id.editBtn:
                startActivity(new Intent(this, NFCSenderActivity.class));
                break;
        }
    }


}
