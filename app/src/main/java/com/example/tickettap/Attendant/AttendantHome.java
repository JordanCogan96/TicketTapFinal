package com.example.tickettap.Attendant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tickettap.NFC.NFCSenderActivity;
import com.example.tickettap.R;
import com.example.tickettap.ResetPasswordActivity;

public class AttendantHome extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendant_home);

        findViewById(R.id.changePwordBtn).setOnClickListener(this);
        findViewById(R.id.viewPrivBtn).setOnClickListener(this);
        findViewById(R.id.nfcBtn).setOnClickListener(this);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.changePwordBtn:
                startActivity(new Intent(this, ResetPasswordActivity.class));
                break;
            case R.id.viewPrivBtn:

                startActivity(new Intent(this, AttendantPriv.class));
                break;
            case R.id.nfcBtn:

                startActivity(new Intent(this, NFCSenderActivity.class));
                break;

        }
    }

}
