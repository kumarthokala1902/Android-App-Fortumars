package com.fortumars.mart.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fortumars.mart.R;
import com.fortumars.mart.adapter.CartAdapter;
import com.fortumars.mart.data.CartManager;
import java.util.Locale;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnCartChangedListener {
    private RecyclerView rvCartItems;
    private TextView tvTotalAmount, tvEmptyCart;
    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rvCartItems = findViewById(R.id.rvCartItems);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        tvEmptyCart = findViewById(R.id.tvEmptyCart);

        findViewById(R.id.btnCheckout).setOnClickListener(v -> {
            if (CartManager.getInstance().getCartItems().isEmpty()) {
                Toast.makeText(this, "Your cart is empty", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_LONG).show();
                CartManager.getInstance().getCartItems().clear();
                onCartChanged();
            }
        });

        setupRecyclerView();
        updateUI();
    }

    private void setupRecyclerView() {
        adapter = new CartAdapter(this, CartManager.getInstance().getCartItems(), this);
        rvCartItems.setLayoutManager(new LinearLayoutManager(this));
        rvCartItems.setAdapter(adapter);
    }

    private void updateUI() {
        double total = CartManager.getInstance().getTotalPrice();
        tvTotalAmount.setText(String.format(Locale.getDefault(), "$%.2f", total));

        if (CartManager.getInstance().getCartItems().isEmpty()) {
            tvEmptyCart.setVisibility(View.VISIBLE);
            rvCartItems.setVisibility(View.GONE);
        } else {
            tvEmptyCart.setVisibility(View.GONE);
            rvCartItems.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCartChanged() {
        updateUI();
    }
}
