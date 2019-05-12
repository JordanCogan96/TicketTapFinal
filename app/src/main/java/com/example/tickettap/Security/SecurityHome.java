package com.example.tickettap.Security;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tickettap.NFC.NFCSenderActivity;
import com.example.tickettap.R;
import com.example.tickettap.ResetPasswordActivity;

public class SecurityHome extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_home);

        findViewById(R.id.secChangePwordBtn).setOnClickListener(this);
        findViewById(R.id.secViewPrivBtn).setOnClickListener(this);
        findViewById(R.id.secNfcBtn).setOnClickListener(this);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.secChangePwordBtn:
                startActivity(new Intent(this, ResetPasswordActivity.class));
                break;
            case R.id.secViewPrivBtn:
                startActivity(new Intent(this, SecurityPriv.class));
                break;
            case R.id.secNfcBtn:
                startActivity(new Intent(this, NFCSenderActivity.class));
                break;

        }
    }

}
