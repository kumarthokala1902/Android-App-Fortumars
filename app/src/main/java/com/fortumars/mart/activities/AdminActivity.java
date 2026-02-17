package com.fortumars.mart.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.fortumars.mart.R;
import com.fortumars.mart.data.ProductProvider;
import com.fortumars.mart.model.Product;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminActivity extends AppCompatActivity {
    private TextInputEditText etName, etPrice, etDescription, etImage, etRating;
    private AutoCompleteTextView spinnerCategory;
    private MaterialButton btnAddProduct, btnSyncDefault;
    private ImageButton btnRefresh, btnHome, btnLogout;
    private ImageView ivPreview;
    private TextView tvStats, tvRecentProduct;
    private DatabaseReference productsRef;
    private View containerAdmin, cardForm;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        productsRef = FirebaseDatabase.getInstance().getReference("Products");
        initViews();
        setupImagePreview();
        updateDatabaseStats();
        startEntranceAnimations();
    }

    private void initViews() {
        containerAdmin = findViewById(R.id.containerAdmin);
        cardForm = findViewById(R.id.cardForm);
        etName = findViewById(R.id.etProductName);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        etPrice = findViewById(R.id.etProductPrice);
        etDescription = findViewById(R.id.etProductDescription);
        etImage = findViewById(R.id.etProductImage);
        etRating = findViewById(R.id.etProductRating);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        btnSyncDefault = findViewById(R.id.btnSyncDefault);
        btnRefresh = findViewById(R.id.btnRefresh);
        btnHome = findViewById(R.id.btnHome);
        btnLogout = findViewById(R.id.btnLogout);
        ivPreview = findViewById(R.id.ivPreview);
        tvStats = findViewById(R.id.tvStats);
        tvRecentProduct = findViewById(R.id.tvRecentProduct);

        String[] categories = {"Electronics", "Men's Clothing", "Women's Clothing", "Home & Kitchen", "Books", "Sports & Fitness", "Beauty & Personal Care", "Toys & Games"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, categories);
        spinnerCategory.setAdapter(adapter);

        btnAddProduct.setOnClickListener(v -> {
            v.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100).withEndAction(() -> {
                v.animate().scaleX(1f).scaleY(1f).setDuration(100).start();
                validateAndAddProduct();
            }).start();
        });

        btnSyncDefault.setOnClickListener(v -> syncDefaultProducts());

        btnRefresh.setOnClickListener(v -> {
            clearFields();
            Toast.makeText(this, "Refreshed", Toast.LENGTH_SHORT).show();
        });

        btnHome.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void setupImagePreview() {
        etImage.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                String url = s.toString().trim();
                if (!url.isEmpty()) {
                    Glide.with(AdminActivity.this)
                            .load(url)
                            .placeholder(android.R.drawable.ic_menu_gallery)
                            .error(android.R.drawable.ic_menu_report_image)
                            .into(ivPreview);
                }
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void updateDatabaseStats() {
        productsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long count = snapshot.getChildrenCount();
                tvStats.setText(String.format("%d Products Live in Store", count));
            }
            @Override public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    private void startEntranceAnimations() {
        containerAdmin.setAlpha(0f);
        containerAdmin.setTranslationY(50f);
        containerAdmin.animate().alpha(1f).translationY(0f).setDuration(600).setInterpolator(new DecelerateInterpolator()).start();

        cardForm.setScaleX(0.9f);
        cardForm.setScaleY(0.9f);
        cardForm.animate().scaleX(1f).scaleY(1f).setDuration(800).setInterpolator(new OvershootInterpolator()).start();
    }

    private void clearFields() {
        etName.setText("");
        spinnerCategory.setText("");
        etPrice.setText("");
        etDescription.setText("");
        etImage.setText("");
        etRating.setText("4.5");
        ivPreview.setImageResource(android.R.drawable.ic_menu_gallery);
        etName.requestFocus();
        
        // Reset errors
        etName.setError(null);
        etPrice.setError(null);
        etDescription.setError(null);
        etImage.setError(null);
        spinnerCategory.setError(null);
    }

    private void syncDefaultProducts() {
        List<Product> defaultProducts = ProductProvider.getAllProducts();
        Map<String, Object> updates = new HashMap<>();
        for (Product p : defaultProducts) {
            updates.put(p.getId(), p);
        }

        btnSyncDefault.setEnabled(false);
        productsRef.updateChildren(updates).addOnCompleteListener(task -> {
            btnSyncDefault.setEnabled(true);
            if (task.isSuccessful()) {
                new MaterialAlertDialogBuilder(AdminActivity.this)
                        .setTitle("Sync Complete")
                        .setMessage("Initial product catalog has been successfully synced.")
                        .setPositiveButton("Done", null)
                        .show();
            } else {
                String error = (task.getException() != null) ? task.getException().getMessage() : "Unknown error";
                Toast.makeText(AdminActivity.this, "Sync Failed: " + error, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void validateAndAddProduct() {
        boolean isValid = true;
        
        String name = etName.getText().toString().trim();
        String category = spinnerCategory.getText().toString().trim();
        String priceStr = etPrice.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String image = etImage.getText().toString().trim();
        String ratingStr = etRating.getText().toString().trim();

        if (name.isEmpty()) { etName.setError("Name required"); isValid = false; }
        if (category.isEmpty()) { spinnerCategory.setError("Category required"); isValid = false; }
        if (priceStr.isEmpty()) { etPrice.setError("Price required"); isValid = false; }
        if (description.isEmpty()) { etDescription.setError("Description required"); isValid = false; }
        if (image.isEmpty()) { etImage.setError("Image URL required"); isValid = false; }

        if (!isValid) return;

        try {
            double price = Double.parseDouble(priceStr);
            float rating = Float.parseFloat(ratingStr);

            hideKeyboard();
            btnAddProduct.setEnabled(false);
            btnAddProduct.setText("PUBLISHING...");

            String id = productsRef.push().getKey();
            if (id == null) {
                btnAddProduct.setEnabled(true);
                btnAddProduct.setText("PUBLISH PRODUCT");
                Toast.makeText(this, "Failed to generate product ID", Toast.LENGTH_SHORT).show();
                return;
            }

            Product product = new Product(id, name, category, price, description, image, rating);

            productsRef.child(id).setValue(product).addOnCompleteListener(task -> {
                if (!isFinishing()) {
                    btnAddProduct.setEnabled(true);
                    btnAddProduct.setText("PUBLISH PRODUCT");
                    if (task.isSuccessful()) {
                        showSuccessDialog(name, category);
                        updateRecentItemView(name, category, price);
                    } else {
                        String error = (task.getException() != null) ? task.getException().getMessage() : "Unknown error";
                        Toast.makeText(AdminActivity.this, "Database Error: " + error, Toast.LENGTH_LONG).show();
                    }
                }
            }).addOnFailureListener(e -> {
                if (!isFinishing()) {
                    btnAddProduct.setEnabled(true);
                    btnAddProduct.setText("PUBLISH PRODUCT");
                    Toast.makeText(AdminActivity.this, "Publish Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid price or rating format", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateRecentItemView(String name, String category, double price) {
        tvRecentProduct.setText(String.format("Latest: %s (%s) - $%.2f", name, category, price));
        tvRecentProduct.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        tvRecentProduct.setAlpha(1.0f);
    }

    private void showSuccessDialog(String name, String category) {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Published Successfully!")
                .setMessage(String.format("%s is now live in the %s section.", name, category))
                .setPositiveButton("Add Another", (dialog, which) -> clearFields())
                .setCancelable(false)
                .show();
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
