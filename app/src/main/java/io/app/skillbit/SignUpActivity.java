package io.app.skillbit;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.app.skillbit.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private String emailPattern;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String confirmPassword;
    private String city;
    private String address;
    private String empId;
    private String designation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        emailPattern = "[a-zA-Z0-9._-]+@gmail+.com";

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.signUpbuttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = binding.editTextName.getText().toString().trim();
                email = binding.editTextEmail.getText().toString().trim();
                phone = binding.editTextPhone.getText().toString().trim();
                password = binding.editTextPassword.getText().toString().trim();
                confirmPassword = binding.editTextConfirmPassword.getText().toString().trim();
                city = binding.editTextCity.getText().toString().trim();
                address = binding.editTextAddress.getText().toString().trim();
                empId = binding.editTextEmployeeId.getText().toString().trim();
                designation = binding.editTextDesignation.getText().toString().trim();
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference().child("employee");

                if (name.length() < 3) {
                    Toast.makeText(SignUpActivity.this, "Enter name have at least 3 word", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty() || !email.matches(emailPattern)) {
                    Toast.makeText(SignUpActivity.this, "Enter valid Email Address", Toast.LENGTH_SHORT).show();
                } else if (phone.isEmpty() || phone.length() < 10) {
                    Toast.makeText(SignUpActivity.this, "Enter valid phone number", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 8 || !password.equals(confirmPassword)) {
                    Toast.makeText(SignUpActivity.this, "Enter Password correctly(length>6)", Toast.LENGTH_SHORT).show();
                } else if (city.length() < 3) {
                    Toast.makeText(SignUpActivity.this, "Enter City correctly(length>3)", Toast.LENGTH_SHORT).show();
                } else if (address.length() < 3) {
                    Toast.makeText(SignUpActivity.this, "Enter Address correctly(length>3)", Toast.LENGTH_SHORT).show();
                } else if (empId.length() < 4) {
                    Toast.makeText(SignUpActivity.this, "Enter empId correctly(length>4)", Toast.LENGTH_SHORT).show();
                } else if (designation.length() < 4) {
                    Toast.makeText(SignUpActivity.this, "Enter designation correctly(length>4)", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        writeNewUser(name, email, phone, password, city, address, empId, designation);
                        Intent intent = new Intent(SignUpActivity.this, OnboardingActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (Exception e) {
                        Log.d(TAG, "onClick: " + e.toString());
                    }
                }
            }
        });
    }

    public void writeNewUser(String name, String email, String phone, String password, String city, String address, String empId, String designation) {
        AppModel user = new AppModel(name, email, phone, password, city, address, empId, designation);

        myRef.child(empId).setValue(user);
    }

}