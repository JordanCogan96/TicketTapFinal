package com.example.tickettap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tickettap.Admin.AdminLoginActivity;
import com.example.tickettap.Attendant.AttendantLoginActivity;
import com.example.tickettap.Security.SecurityLoginActivity;
import com.example.tickettap.VIP.VipLoginActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.adminLoginBtn).setOnClickListener(this);
        findViewById(R.id.securityLoginBtn).setOnClickListener(this);
        findViewById(R.id.vipLoginBtn).setOnClickListener(this);
        findViewById(R.id.attendantLoginBtn).setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.adminLoginBtn:

                startActivity(new Intent(this, AdminLoginActivity.class));
                break;

            case R.id.securityLoginBtn:

                startActivity(new Intent(this, SecurityLoginActivity.class));
                break;

            case R.id.vipLoginBtn:

                startActivity(new Intent(this, VipLoginActivity.class));
                break;

            case R.id.attendantLoginBtn:

                startActivity(new Intent(this, AttendantLoginActivity.class));
                break;

        }
    }

}
