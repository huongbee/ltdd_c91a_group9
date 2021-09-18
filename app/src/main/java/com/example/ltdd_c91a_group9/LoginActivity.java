package com.example.ltdd_c91a_group9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ltdd_c91a_group9.constants.AppCache;
import com.example.ltdd_c91a_group9.constants.CartSingleton;
import com.example.ltdd_c91a_group9.models.Response;
import com.example.ltdd_c91a_group9.models.User;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText mTxtInputEmail,mTxtInputPassword;
    Button mBtnLogin;
    TextView mTvSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTxtInputEmail = findViewById(R.id.textInputEmail);
        mTxtInputPassword = findViewById(R.id.textInputPassword);
        mBtnLogin = findViewById(R.id.buttonLogin);
        mTvSignUp = findViewById(R.id.textViewSignUp);


        mTvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response response = null;
                if (AppCache.checkFileExists(LoginActivity.this)){
                   response = CartSingleton.getInstance().tranFormStringToResponseData(AppCache.readFile(LoginActivity.this));
                }

                String email = mTxtInputEmail.getText().toString();
                String password = mTxtInputPassword.getText().toString();

                if (response != null){
                    if (email.equals(response.getUser().getEmail()) && password.equals(response.getUser().getPassword())){
                        response.getUser().setLogin(true);
                        String data = CartSingleton.getInstance().createJson(response.getUser(),response.getProducts()).toString();
                        AppCache.updateFile(LoginActivity.this,data);
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,ProductListActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Email hoặc password chưa chính xác", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Tài khoản chưa tồn tại", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}