package com.edumbrella.foodapp.OtherActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edumbrella.foodapp.PrefConfig;
import com.edumbrella.foodapp.R;
import com.edumbrella.foodapp.model.Popular;

import java.util.ArrayList;
import java.util.List;

public class DescriptionActivity extends AppCompatActivity {

    private ImageView image;
    private TextView name, price, description, badgeCounter;
    private Button addToCart;

    private List<Popular> cartDatas;

    MenuItem menuItem;

    int count = 0;
    int cartCount = 1;
    String n, p, d;
    int i;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        n = intent.getStringExtra("name");
        p = intent.getStringExtra("price");
        d = intent.getStringExtra("description");
        i = intent.getIntExtra("image", 0);

        count = PrefConfig.readCount(getApplicationContext());
        cartDatas = PrefConfig.readCartItems(getApplicationContext());
        if (cartDatas == null) {
            cartDatas = new ArrayList<>();
        }

        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);
        addToCart = findViewById(R.id.addToCart);

        image.setImageResource(i);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);

        name.setText(n);
        price.setText(p);
        description.setText(d);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                update();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.notification_menu, menu);
        menuItem = menu.findItem(R.id.nav_notification);
        if (count != 0) {
            menuItem.setActionView(R.layout.cart_notification_badge);

            View view = menuItem.getActionView();
            badgeCounter = view.findViewById(R.id.badgeCounter);
            badgeCounter.setText("" + count);

            FrameLayout frameLayout = view.findViewById(R.id.nav_notification_layout);
            frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent notificationIntent = new Intent(getApplicationContext(), CartActivity.class);
                    startActivity(notificationIntent);
                }
            });

        }

        return super.onCreateOptionsMenu(menu);
    }

    public void update() {


        menuItem.setActionView(R.layout.cart_notification_badge);

        View view = menuItem.getActionView();
        badgeCounter = view.findViewById(R.id.badgeCounter);
        count = count + 1;

        List<Popular> removeList = new ArrayList<>();

        for (Popular cartData : cartDatas) {
            if (n.trim().equals(cartData.getTitle().trim())) {
                cartCount = cartData.getCount() + 1;

                removeList.add(cartData);

                SharedPreferences sp = getSharedPreferences("cartItems", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();

                editor.clear();
                editor.commit();

            }
        }

        cartDatas.removeAll(removeList);


        Popular cartItems = new Popular(n, p, d, i, cartCount);
        cartDatas.add(cartItems);

        PrefConfig.writeCount(getApplicationContext(), count);
        PrefConfig.writeCartItems(getApplicationContext(), cartDatas);

        badgeCounter.setText("" + count);

        FrameLayout frameLayout = view.findViewById(R.id.nav_notification_layout);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent notificationIntent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(notificationIntent);
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getCurrentFocus() != null)
                    getCurrentFocus().clearFocus();
                finish();
                break;
            case R.id.nav_notification:
                Intent intent = new Intent(this, CartActivity.class);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}