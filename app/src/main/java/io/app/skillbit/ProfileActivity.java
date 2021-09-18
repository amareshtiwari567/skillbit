package io.app.skillbit;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;

import java.net.URI;
import java.util.Set;

import io.app.skillbit.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private String empId;
    private String name;
    private String city;
    private  String address;
    private String designation;
    private String email;
    private Uri profilephoto;
    private Uri defaultProfilePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityProfileBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        SharedPreferences sharedPreferences=getSharedPreferences("His",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent imageIntent=result.getData();
                            Uri imageUri=imageIntent.getData();
                            editor.putString("profileImage", imageUri.toString());
                            editor.apply();
                            Glide
                                    .with(ProfileActivity.this)
                                    .load(imageUri)
                                    .centerCrop()
                                    .into(binding.profileImage);
                            Log.d(TAG, "onActivityResult: "+imageUri);

                        }
                    }
                });


        empId=sharedPreferences.getString("empId","Ved");
        email=sharedPreferences.getString("email","Ved");
        city=sharedPreferences.getString("city","Ved");
        address=sharedPreferences.getString("address","Ved");
        designation=sharedPreferences.getString("designation","Ved");
        name=sharedPreferences.getString("name","Ved");
        defaultProfilePhoto= Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.avenger);
        profilephoto=Uri.parse(sharedPreferences.getString("profileUri",defaultProfilePhoto.toString()));


        binding.profileName.setText(name);
        binding.profileEmail.setText(email);
        binding.profileCity.setText(city);
        binding.profileAddress.setText(address);
        binding.profileDesignation.setText(designation);
        binding.profileEmpId.setText(empId);
        Glide
                .with(ProfileActivity.this)
                .load(profilephoto)
                .centerCrop()
                .into(binding.profileImage);

        binding.profilebuttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProfileActivity.this,OnboardingActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.butttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(ProfileActivity.this,JoinTeamActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                someActivityResultLauncher.launch(intent);
            }
        });
    }
}