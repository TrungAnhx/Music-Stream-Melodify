package com.example.musicstreammelodify;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView registerText;

    private MusicDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        dbHelper = new MusicDatabaseHelper(this);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        registerText = findViewById(R.id.registerText);

        // Đăng ký tài khoản mới
        registerText.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        loginButton.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Kiểm tra nếu các trường nhập liệu trống
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please enter both email and password.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra thông tin đăng nhập
        boolean isValidUser = dbHelper.checkUserCredentials(email, password);
        if (isValidUser) {
            Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

            // Lưu trạng thái đăng nhập vào SharedPreferences (để nhận diện khi mở lại ứng dụng)
            SharedPreferences prefs = getSharedPreferences("user_data", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("is_logged_in", true);
            editor.apply();

            // Chuyển sang màn hình PlaylistActivity sau khi đăng nhập thành công
            Intent intent = new Intent(LoginActivity.this, PlaylistActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
        }
    }
}
