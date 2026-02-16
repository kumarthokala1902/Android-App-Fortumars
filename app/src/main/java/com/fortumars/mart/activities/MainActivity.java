package com.fortumars.mart.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.fortumars.mart.R;
import com.fortumars.mart.adapter.ProductAdapter;
import com.fortumars.mart.data.CartManager;
import com.fortumars.mart.data.ProductProvider;
import com.fortumars.mart.model.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements ProductAdapter.OnCartUpdateListener {
    private RecyclerView rvProducts;
    private ProductAdapter adapter;
    private List<Product> allProducts;
    private String selectedCategory = "All";
    private boolean isDarkMode = false;
    private AutoCompleteTextView etSearch;
    private TextView tvWelcome, tvCartBadge;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        allProducts = ProductProvider.getAllProducts();
        initViews();
        loadUserData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCartBadge();
    }

    private void initViews() {
        rvProducts = findViewById(R.id.rvProducts);
        etSearch = findViewById(R.id.etSearch);
        tvWelcome = findViewById(R.id.tvWelcome);
        tvCartBadge = findViewById(R.id.tvCartBadge);
        ChipGroup chipGroup = findViewById(R.id.chipGroupCategories);
        ImageButton btnCart = findViewById(R.id.btnCart);
        ImageButton btnProfile = findViewById(R.id.btnProfile);
        ImageButton btnTheme = findViewById(R.id.btnTheme);

        adapter = new ProductAdapter(this, new ArrayList<>(allProducts), this);
        rvProducts.setLayoutManager(new GridLayoutManager(this, 2));
        rvProducts.setAdapter(adapter);

        setupSearchSuggestions();

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        etSearch.setOnItemClickListener((parent, view, position, id) -> {
            String selected = (String) parent.getItemAtPosition(position);
            filter(selected);
            hideKeyboard();
        });

        chipGroup.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chip = findViewById(checkedId);
            if (chip != null) {
                selectedCategory = chip.getText().toString();
                filter(etSearch.getText().toString());
            }
        });

        btnCart.setOnClickListener(v -> startActivity(new Intent(this, CartActivity.class)));
        btnProfile.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));

        btnTheme.setOnClickListener(v -> {
            isDarkMode = !isDarkMode;
            AppCompatDelegate.setDefaultNightMode(isDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        });
        
        updateCartBadge();
    }

    public void updateCartBadge() {
        int count = CartManager.getInstance().getCartItems().size();
        if (count > 0) {
            tvCartBadge.setText(String.valueOf(count));
            tvCartBadge.setVisibility(View.VISIBLE);
        } else {
            tvCartBadge.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCartUpdated() {
        updateCartBadge();
    }

    private void loadUserData() {
        String userId = mAuth.getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference("Users").child(userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String name = snapshot.child("name").getValue(String.class);
                            tvWelcome.setText("Hello, " + name + "!");
                        }
                    }
                    @Override public void onCancelled(@NonNull DatabaseError error) {}
                });
    }

    private void setupSearchSuggestions() {
        if (allProducts == null) return;
        List<String> suggestions = allProducts.stream()
                .map(Product::getName)
                .collect(Collectors.toList());
        ArrayAdapter<String> suggestionAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, suggestions);
        etSearch.setAdapter(suggestionAdapter);
    }

    private void filter(String query) {
        if (allProducts == null) return;
        List<Product> filtered = allProducts.stream()
                .filter(p -> (selectedCategory.equals("All") || p.getCategory().equals(selectedCategory)))
                .filter(p -> p.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        adapter.setProducts(filtered);
        rvProducts.scheduleLayoutAnimation();
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null && getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
