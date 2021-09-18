package com.example.ltdd_c91a_group9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ltdd_c91a_group9.R;
import com.example.ltdd_c91a_group9.constants.AppCache;
import com.example.ltdd_c91a_group9.constants.CartSingleton;
import com.example.ltdd_c91a_group9.models.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {


    TextInputEditText mTxtInputNewEmail, mTxtInputNewPassWord, mTxtInputConfirmPassword;
    Button mBtnSignUp;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mTxtInputNewEmail = findViewById(R.id.textInputNewEmail);
        mTxtInputNewPassWord = findViewById(R.id.textInputNewPassword);
        mTxtInputConfirmPassword = findViewById(R.id.textConfirmPassWord);
        mBtnSignUp = findViewById(R.id.buttonSignUp);
        mToolbar = findViewById(R.id.toolbarRegister);


        setSupportActionBar(mToolbar);

        mToolbar.setNavigationIcon(R.drawable.ic_back);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newEmail = mTxtInputNewEmail.getText().toString();
                String newPassword = mTxtInputNewPassWord.getText().toString();
                String confirmPassword = mTxtInputConfirmPassword.getText().toString();

                if (!newEmail.isEmpty() && !newPassword.isEmpty() && !confirmPassword.isEmpty()) {
                    if (newPassword.equals(confirmPassword)) {

                        if (AppCache.checkFileExists(RegisterActivity.this)) {
                            AppCache.deleteFile(RegisterActivity.this);
                        }
                        AppCache.createFile(CartSingleton.getInstance().createJson(new User(newEmail, newPassword, false), new ArrayList<>()).toString(), RegisterActivity.this);
                        Toast.makeText(RegisterActivity.this, "Register success", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(RegisterActivity.this, "Confirm password không chính xác", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}