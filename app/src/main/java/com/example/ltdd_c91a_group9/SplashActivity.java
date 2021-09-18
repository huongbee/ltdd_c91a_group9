package com.example.ltdd_c91a_group9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ltdd_c91a_group9.constants.AppCache;
import com.example.ltdd_c91a_group9.constants.CartSingleton;
import com.example.ltdd_c91a_group9.models.Response;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AppCache.checkFileExists(SplashActivity.this)){
                    Response response = CartSingleton.getInstance().tranFormStringToResponseData(AppCache.readFile(SplashActivity.this));
                    Intent intent;
                    if (response.getUser() != null && response.getUser().isLogin()){
                        intent = new Intent(SplashActivity.this, ProductListActivity.class);
                    }else{
                        intent = new Intent(SplashActivity.this, LoginActivity.class);
                    }
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        },4000);

    }
}