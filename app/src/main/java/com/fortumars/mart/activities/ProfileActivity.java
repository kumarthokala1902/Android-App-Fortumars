package com.fortumars.mart.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.fortumars.mart.R;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private TextView tvName, tvEmail;
    private ImageView ivProfile;
    private View cvProfileImage;
    private MaterialButton btnAdminPanel;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        ivProfile = findViewById(R.id.ivProfile);
        cvProfileImage = findViewById(R.id.cvProfileImage);
        btnAdminPanel = findViewById(R.id.btnAdminPanel);

        loadUserData();
        checkAdminAccess();
        startEntranceAnimations();

        btnAdminPanel.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, AdminActivity.class));
        });

        findViewById(R.id.btnLogout).setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void checkAdminAccess() {
        if (mAuth.getCurrentUser() != null && "admin@gmail.com".equals(mAuth.getCurrentUser().getEmail())) {
            btnAdminPanel.setVisibility(View.VISIBLE);
        } else {
            btnAdminPanel.setVisibility(View.GONE);
        }
    }

    private void startEntranceAnimations() {
        Animation scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        cvProfileImage.startAnimation(scaleUp);

        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        tvName.startAnimation(slideUp);
        tvEmail.startAnimation(slideUp);
        if (btnAdminPanel.getVisibility() == View.VISIBLE) {
            btnAdminPanel.startAnimation(slideUp);
        }
        findViewById(R.id.btnLogout).startAnimation(slideUp);
    }

    private void loadUserData() {
        if (mAuth.getCurrentUser() == null) return;
        
        String userId = mAuth.getCurrentUser().getUid();
        String userEmail = mAuth.getCurrentUser().getEmail();

        tvEmail.setText(userEmail != null ? userEmail : "Loading...");

        Glide.with(this)
                .load("https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?auto=format&fit=crop&q=80&w=400")
                .transition(DrawableTransitionOptions.withCrossFade())
                .circleCrop()
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image)
                .into(ivProfile);

        FirebaseDatabase.getInstance().getReference("Users").child(userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String name = snapshot.child("name").getValue(String.class);
                            String email = snapshot.child("email").getValue(String.class);
                            
                            tvName.setText(name != null ? name : (email != null ? email.split("@")[0] : "User"));
                            if (email != null) {
                                tvEmail.setText(email);
                            }
                        } else if (userEmail != null) {
                            tvName.setText(userEmail.split("@")[0]);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ProfileActivity.this, "Failed to load profile", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
