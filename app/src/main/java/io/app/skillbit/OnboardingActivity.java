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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.app.skillbit.databinding.ActivityOnboardingBinding;

public class OnboardingActivity extends AppCompatActivity {

    private ActivityOnboardingBinding binding;
    private DatabaseReference myRef;
    private String empId;
    private Uri defaultProfilePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        SharedPreferences sharedPreferences= getSharedPreferences("His",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();


        binding.butttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                empId = binding.onboardEmployeeId.getText().toString().trim();
                if (!empId.isEmpty()) {
                    myRef = database.getReference().child("employee").child(empId);
                    Log.d("TAG", "Firebase");

                    myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {

                            if(!task.isSuccessful()){
                                Toast.makeText(OnboardingActivity.this, "No Detail Maa chodao", Toast.LENGTH_SHORT).show();
                            }

                            try {
                                AppModel empDetail = task.getResult().getValue(AppModel.class);
                                 String name= empDetail.getName();
                                 String city= empDetail.getCity();
                                 String address=empDetail.getAddress();
                                 String designation=empDetail.getDesignation();
                                 String email=empDetail.getEmail();
                                defaultProfilePhoto= Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.avenger);
                                    editor.putString("empId",empId);
                                    editor.putString("name",name);
                                    editor.putString("email",email);
                                    editor.putString("city",city);
                                    editor.putString("address",address);
                                    editor.putString("designation",designation);
                                    editor.putString("profileImage",defaultProfilePhoto.toString());
                                    editor.apply();

                                 Intent intent=new Intent(OnboardingActivity.this,ProfileActivity.class);
                                 intent.putExtra("name",name);
                                intent.putExtra("city",city);
                                intent.putExtra("address",address);
                                intent.putExtra("designation",designation);
                                intent.putExtra("empId",empId);
                                intent.putExtra("email",email);
                                startActivity(intent);
                                finish();
                            } catch (Exception e) {
                                Toast.makeText(OnboardingActivity.this, "Fail to get data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(OnboardingActivity.this, "Enter the EmpId", Toast.LENGTH_SHORT).show();
                }


            }
        });


        binding.onboardbuttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OnboardingActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}