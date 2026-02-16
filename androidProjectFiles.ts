
import { ProjectNode } from './types';

export const ANDROID_PROJECT: ProjectNode[] = [
  {
    name: 'app',
    type: 'folder',
    children: [
      {
        name: 'src',
        type: 'folder',
        children: [
          {
            name: 'main',
            type: 'folder',
            children: [
              {
                name: 'AndroidManifest.xml',
                type: 'file',
                language: 'xml',
                path: 'app/src/main/AndroidManifest.xml',
                content: `<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.fortumars.mart">

    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="Mart Pro"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.MaterialComponents.DayNight.NoActionBar">
        <activity
            android:name=".activities.MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>`
              },
              {
                name: 'java',
                type: 'folder',
                children: [
                  {
                    name: 'com.fortumars.mart',
                    type: 'folder',
                    children: [
                      {
                        name: 'activities',
                        type: 'folder',
                        children: [
                          {
                            name: 'MainActivity.java',
                            type: 'file',
                            language: 'java',
                            path: 'app/src/main/java/com/fortumars/mart/activities/MainActivity.java',
                            content: `package com.fortumars.mart.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.fortumars.mart.R;
import com.fortumars.mart.adapter.ProductAdapter;
import com.fortumars.mart.data.ProductProvider;
import com.fortumars.mart.model.Product;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private List<Product> allProducts;
    private ProductAdapter adapter;
    private String currentCategory = "All";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allProducts = ProductProvider.getAllProducts();
        setupRecyclerView();
        setupCategoryChips();
        setupSearch();
    }

    private void setupRecyclerView() {
        RecyclerView rv = findViewById(R.id.rvProducts);
        adapter = new ProductAdapter(this, new ArrayList<>(allProducts));
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        rv.setAdapter(adapter);
    }

    private void setupCategoryChips() {
        ChipGroup group = findViewById(R.id.chipGroupCategories);
        group.setOnCheckedChangeListener((g, id) -> {
            Chip chip = findViewById(id);
            currentCategory = (chip != null) ? chip.getText().toString() : "All";
            filter();
        });
    }

    private void setupSearch() {
        TextInputEditText etSearch = findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int a, int b, int c) {}
            @Override public void onTextChanged(CharSequence s, int a, int b, int c) { filter(); }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void filter() {
        TextInputEditText etSearch = findViewById(R.id.etSearch);
        String query = etSearch.getText().toString().toLowerCase();
        List<Product> filtered = allProducts.stream()
            .filter(p -> (currentCategory.equals("All") || p.getCategory().equals(currentCategory)))
            .filter(p -> p.getName().toLowerCase().contains(query))
            .collect(Collectors.toList());
        adapter.setProducts(filtered);
    }
}`
                          }
                        ]
                      },
                      {
                        name: 'model',
                        type: 'folder',
                        children: [
                          {
                            name: 'Product.java',
                            type: 'file',
                            language: 'java',
                            path: 'app/src/main/java/com/fortumars/mart/model/Product.java',
                            content: `package com.fortumars.mart.model;

public class Product {
    private int id;
    private String name, category, description, imageUrl;
    private double price;
    private float rating;

    public Product(int id, String name, String category, double price, String description, String imageUrl, float rating) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.rating = rating;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public float getRating() { return rating; }
}`
                          }
                        ]
                      },
                      {
                        name: 'adapter',
                        type: 'folder',
                        children: [
                          {
                            name: 'ProductAdapter.java',
                            type: 'file',
                            language: 'java',
                            path: 'app/src/main/java/com/fortumars/mart/adapter/ProductAdapter.java',
                            content: `package com.fortumars.mart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.fortumars.mart.R;
import com.fortumars.mart.model.Product;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> list;
    private Context context;

    public ProductAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
    }

    public void setProducts(List<Product> newList) {
        this.list = newList;
        notifyDataSetChanged();
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup p, int t) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product, p, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int pos) {
        Product p = list.get(pos);
        h.name.setText(p.getName());
        h.price.setText("$" + p.getPrice());
        Glide.with(context).load(p.getImageUrl()).into(h.img);
    }

    @Override public int getItemCount() { return list.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img; TextView name, price;
        ViewHolder(View v) { super(v);
            img = v.findViewById(R.id.productImg);
            name = v.findViewById(R.id.productName);
            price = v.findViewById(R.id.productPrice);
        }
    }
}`
                          }
                        ]
                      },
                      {
                        name: 'data',
                        type: 'folder',
                        children: [
                          {
                            name: 'ProductProvider.java',
                            type: 'file',
                            language: 'java',
                            path: 'app/src/main/java/com/fortumars/mart/data/ProductProvider.java',
                            content: `package com.fortumars.mart.data;

import com.fortumars.mart.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductProvider {
    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        // Electronics
        products.add(new Product(101, "iPhone 15 Pro", "Electronics", 999.0, "Titanium design.", "https://images.unsplash.com/photo-1695048133142-1a20484d2569", 4.8f));
        products.add(new Product(102, "Sony WH-1000XM5", "Electronics", 348.0, "Noise cancelling.", "https://images.unsplash.com/photo-1670054700055-662580556f2e", 4.9f));

        // Clothing
        products.add(new Product(201, "Oxford Shirt", "Men's Clothing", 35.0, "Classic fit.", "https://images.unsplash.com/photo-1596755094514-f87e34085b2c", 4.3f));
        products.add(new Product(301, "Floral Dress", "Women's Clothing", 45.0, "Summer style.", "https://images.unsplash.com/photo-1572804013309-59a88b7e92f1", 4.6f));

        return products;
    }
}`
                          }
                        ]
                      }
                    ]
                  }
                ]
              },
              {
                name: 'res',
                type: 'folder',
                children: [
                  {
                    name: 'layout',
                    type: 'folder',
                    children: [
                      {
                        name: 'activity_main.xml',
                        type: 'file',
                        language: 'xml',
                        path: 'app/src/main/res/layout/activity_main.xml',
                        content: `<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:background="#F5F5F5">

    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="16dp"
        style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox">
        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/etSearch"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Search store..." />
    </com.google.android.material.textfield.TextInputLayout>

    <HorizontalScrollView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:scrollbars="none">
        <com.google.android.material.chip.ChipGroup
            android:id="@+id/chipGroupCategories"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:paddingStart="16dp"
            android:paddingEnd="16dp"
            app:singleSelection="true">
            <com.google.android.material.chip.Chip android:id="@+id/chipAll" android:layout_width="wrap_content" android:layout_height="wrap_content" android:text="All" android:checked="true" />
            <com.google.android.material.chip.Chip android:id="@+id/chipElectronics" android:layout_width="wrap_content" android:layout_height="wrap_content" android:text="Electronics" />
            <com.google.android.material.chip.Chip android:id="@+id/chipMens" android:layout_width="wrap_content" android:layout_height="wrap_content" android:text="Men's Clothing" />
            <com.google.android.material.chip.Chip android:id="@+id/chipWomens" android:layout_width="wrap_content" android:layout_height="wrap_content" android:text="Women's Clothing" />
        </com.google.android.material.chip.ChipGroup>
    </HorizontalScrollView>

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/rvProducts"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="8dp" />
</LinearLayout>`
                      },
                      {
                        name: 'item_product.xml',
                        type: 'file',
                        language: 'xml',
                        path: 'app/src/main/res/layout/item_product.xml',
                        content: `<?xml version="1.0" encoding="utf-8"?>
<com.google.android.material.card.MaterialCardView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="8dp"
    app:cardCornerRadius="12dp"
    app:cardElevation="4dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <ImageView
            android:id="@+id/productImg"
            android:layout_width="match_parent"
            android:layout_height="140dp"
            android:scaleType="centerCrop" />

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:padding="12dp"
            android:orientation="vertical">
            <TextView
                android:id="@+id/productName"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:textStyle="bold"
                android:textSize="14sp" />
            <TextView
                android:id="@+id/productPrice"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:textColor="@color/design_default_color_primary"
                android:textStyle="bold"
                android:layout_marginTop="4dp" />
        </LinearLayout>
    </LinearLayout>
</com.google.android.material.card.MaterialCardView>`
                      }
                    ]
                  }
                ]
              }
            ]
          }
        ]
      }
    ]
  }
];
