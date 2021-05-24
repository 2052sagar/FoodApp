package com.edumbrella.foodapp.OtherActivities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.edumbrella.foodapp.Adapter.CartItemsAdapter;
import com.edumbrella.foodapp.Adapter.PopularAdapter;
import com.edumbrella.foodapp.MainActivity;
import com.edumbrella.foodapp.PrefConfig;
import com.edumbrella.foodapp.R;
import com.edumbrella.foodapp.model.Popular;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private List<Popular> cartDatas;

    private RecyclerView recyclerViewCartItems;
    private CartItemsAdapter cartItemsAdapter;

    private Button checkoutBtn;
    private BottomSheetDialog bottomSheetDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Your Carts");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        checkoutBtn = findViewById(R.id.checkoutBtn);

        cartDatas = PrefConfig.readCartItems(getApplicationContext());
        if (cartDatas == null)
            cartDatas = new ArrayList<>();

        showPopularProducts();

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(CartActivity.this, "Successfully Completed", Toast.LENGTH_SHORT).show();

                // this code is for removing all items after order
                SharedPreferences sharedpreferences = getSharedPreferences("cartItems", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.clear();
                editor.commit();

                Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(homeIntent);

//                bottomSheetDialog = new BottomSheetDialog(CartActivity.this, R.style.BottomSheetDialogTheme);
//
//               View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_bottom_sheet, (LinearLayout) findViewById(R.id.bottomSheetContainer));

//                bottomSheetView.findViewById(R.id.payBillBtn).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(CartActivity.this, "Payment Success", Toast.LENGTH_SHORT).show();
//                        bottomSheetDialog.dismiss();
//                    }
//                });
//
//                bottomSheetDialog.setContentView(bottomSheetView);
//                bottomSheetDialog.show();
                //Toast.makeText(CartActivity.this, "working", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void showPopularProducts() {

        recyclerViewCartItems = findViewById(R.id.recyclerViewCartItems);
        recyclerViewCartItems.setHasFixedSize(true);
        recyclerViewCartItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        cartItemsAdapter = new CartItemsAdapter(this, cartDatas);
        recyclerViewCartItems.setAdapter(cartItemsAdapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getCurrentFocus() != null)
                    getCurrentFocus().clearFocus();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}