package io.app.skillbit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import io.app.skillbit.databinding.ActivityJoinTeamBinding;

public class JoinTeamActivity extends AppCompatActivity {

    private ActivityJoinTeamBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityJoinTeamBinding.inflate(getLayoutInflater());
        View view= binding.getRoot();
        setContentView(view);

        binding.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(JoinTeamActivity.this,SignIn.class);
                startActivity(intent);
                finish();
            }
        });

        binding.pythonTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(JoinTeamActivity.this,DashBoard.class);
                startActivity(intent);
                finish();
            }
        });

        binding.danjoTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(JoinTeamActivity.this,DashBoard.class);
                startActivity(intent);
                finish();
            }
        });

        binding.dataScienceTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(JoinTeamActivity.this,DashBoard.class);
                startActivity(intent);
                finish();
            }
        });

        binding.machineLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(JoinTeamActivity.this,DashBoard.class);
                startActivity(intent);
                finish();
            }
        });
    }
}