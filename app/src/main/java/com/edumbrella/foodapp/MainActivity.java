package com.edumbrella.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.edumbrella.foodapp.Adapter.AllItemsAdapter;
import com.edumbrella.foodapp.Adapter.PopularAdapter;
import com.edumbrella.foodapp.OtherActivities.CartActivity;
import com.edumbrella.foodapp.model.Popular;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewFlipper bannerImg;
    private RecyclerView recyclerView, recyclerViewAllItems;
    private PopularAdapter popularAdapter;
    private AllItemsAdapter allItemsAdapter;

    List<Popular> populars = new ArrayList<>();

    MenuItem menuItem;

    int count = 0;
    private TextView badgeCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bannerImg = findViewById(R.id.bannerImage);

        count = PrefConfig.readCount(getApplicationContext());

        populars.clear();
        populars.add(new Popular("MoMo", "Rs 300", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. ", R.drawable.momo,0));
        populars.add(new Popular("Chowmein", "Rs 200", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", R.drawable.chowmein,0));
        populars.add(new Popular("Chicken Tandoori", "Rs 700", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", R.drawable.chickentandoori,0));
        populars.add(new Popular("Burger", "Rs 250", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", R.drawable.bannerimg1,0));
        populars.add(new Popular("French Fries", "Rs 300", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", R.drawable.frenchfries,0));
        populars.add(new Popular("Pizza", "Rs 400", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", R.drawable.bannerimg2,0));
        populars.add(new Popular("Chicken Wings", "Rs 500", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", R.drawable.bannerimg3,0));

        int sliders[] = {
                R.drawable.bannerimg1,
                R.drawable.bannerimg2,
                R.drawable.bannerimg3
        };

        for (int slide: sliders) {
            changeBannerImage(slide);
        }

        showPopularProducts();
        showAllFoodItems();

    }

    public void showAllFoodItems() {

        recyclerViewAllItems = findViewById(R.id.recyclerViewAllItems);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerViewAllItems.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        allItemsAdapter = new AllItemsAdapter(this, populars);
        recyclerViewAllItems.setAdapter(allItemsAdapter);
    }


    public void showPopularProducts(){

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        popularAdapter = new PopularAdapter(this, populars);
        recyclerView.setAdapter(popularAdapter);


    }

    public void update() {
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

    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.notification_menu, menu);
        menuItem = menu.findItem(R.id.nav_notification);
        update();

        return super.onCreateOptionsMenu(menu);
    }


    public void changeBannerImage(int image){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(image);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        bannerImg.addView(imageView);
        bannerImg.setFlipInterval(6000);
        bannerImg.setAutoStart(true);
        bannerImg.setInAnimation(this, android.R.anim.fade_in);
        bannerImg.setOutAnimation(this, android.R.anim.fade_out);
    }
}