package io.app.skillbit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import io.app.skillbit.databinding.ActivityDashBoardBinding;

public class DashBoard extends AppCompatActivity {
 private ActivityDashBoardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDashBoardBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        binding.profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoard.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

        binding.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.gotoDashBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoard.this,JoinTeamActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoard.this,JoinTeamActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent=new Intent(DashBoard.this,SignIn.class);
              startActivity(intent);
              finish();
            }
        });
    }
}