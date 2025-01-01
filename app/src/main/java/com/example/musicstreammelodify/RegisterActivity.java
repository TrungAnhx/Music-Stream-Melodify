package com.example.musicstreammelodify;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button registerButton;

    private MusicDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        dbHelper = new MusicDatabaseHelper(this);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        registerButton = findViewById(R.id.registerButton);

        // Xử lý khi người dùng nhấn nút đăng ký
        registerButton.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Kiểm tra nếu các trường nhập liệu trống
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please enter both email and password.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra nếu email đã tồn tại trong cơ sở dữ liệu
        if (dbHelper.checkEmailExists(email)) {
            Toast.makeText(RegisterActivity.this, "Email already exists!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lưu người dùng vào cơ sở dữ liệu
        dbHelper.addUser(email, password);
        Toast.makeText(RegisterActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
