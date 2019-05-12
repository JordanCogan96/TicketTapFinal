package com.example.tickettap.VIP;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tickettap.NFC.NFCSenderActivity;
import com.example.tickettap.R;
import com.example.tickettap.ResetPasswordActivity;

public class VipHome extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_home);

        findViewById(R.id.vipChangePwordBtn).setOnClickListener(this);
        findViewById(R.id.vipViewPrivBtn).setOnClickListener(this);
        findViewById(R.id.vipNfcBtn).setOnClickListener(this);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.vipChangePwordBtn:
                startActivity(new Intent(this, ResetPasswordActivity.class));
                break;
            case R.id.vipViewPrivBtn:

                startActivity(new Intent(this, VipPriv.class));
                break;
            case R.id.vipNfcBtn:

                startActivity(new Intent(this, NFCSenderActivity.class));
                break;

        }
    }

}
