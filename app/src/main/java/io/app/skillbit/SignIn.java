package io.app.skillbit;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.app.skillbit.databinding.ActivitySignInBinding;

public class SignIn extends AppCompatActivity {

    private DatabaseReference myRef;
    private ActivitySignInBinding binding;
    private String empId;
    private String password;
    private Uri defaultProfilePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.butttonSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                empId = binding.signInEmpId.getText().toString().trim();
                password = binding.signInPassword.getText().toString().trim();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                if (empId.length() < 4) {
                    Toast.makeText(SignIn.this, "Enter correct empId ", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 8) {
                    Toast.makeText(SignIn.this, "Enter correct password ", Toast.LENGTH_SHORT).show();
                } else {

                    myRef = database.getReference().child("employee").child(empId);
                    Log.d(TAG, "onClick: "+myRef.toString());
                        SharedPreferences sharedPreferences = getSharedPreferences("His", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {

                                if(!task.isSuccessful()){
                                    Toast.makeText(SignIn.this, "No Detail Maa chodao", Toast.LENGTH_SHORT).show();
                                }
                                try {
                                    AppModel empDetail = task.getResult().getValue(AppModel.class);
                                    String name = empDetail.getName();
                                    String city = empDetail.getCity();
                                    String address = empDetail.getAddress();
                                    String designation = empDetail.getDesignation();
                                    String email = empDetail.getEmail();
                                    String empIdCheck = empDetail.getEmpId();
                                    String passwordCheck = empDetail.getPassword();
                                    if(passwordCheck.equals(password)) {
                                        defaultProfilePhoto = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.avenger);
                                        editor.putString("empId", empId);
                                        editor.putString("name", name);
                                        editor.putString("email", email);
                                        editor.putString("city", city);
                                        editor.putString("address", address);
                                        editor.putString("designation", designation);
                                        editor.putString("profileImage", defaultProfilePhoto.toString());
                                        editor.apply();
                                        Intent intent = new Intent(SignIn.this, DashBoard.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(SignIn.this, "No Detail Maa chodao", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    Log.d(TAG, "onComplete: " + e.toString());
                                    Toast.makeText(SignIn.this, "No Detail Maa chodao", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });


                }
            }
        });

        binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}