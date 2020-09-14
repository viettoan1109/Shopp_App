package com.actvn.shopapp.views;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.actvn.shopapp.MainActivity;
import com.actvn.shopapp.R;
import com.actvn.shopapp.WellcomeActivity;
import com.actvn.shopapp.databinding.ActivityViewsSliderBinding;
import com.actvn.shopapp.roomdatabase.MyDatabase;

public class ViewsSliderActivity extends AppCompatActivity {

    private ViewsSliderAdapter mAdapter;
    private TextView[] dots;
    private int[] layouts;
    private ActivityViewsSliderBinding binding;
    public static MyDatabase myDatabase;
    TextView cartcount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isfirstrun", true);

        if (isFirstRun){
            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("isfirstrun", false).commit();
        } else {
            launchHomeScreen();
        }


        binding = ActivityViewsSliderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        myDatabase= Room.databaseBuilder(getApplicationContext(),MyDatabase.class,"My_Cart").allowMainThreadQueries().build();


    }
    private void updatacartcount() {
        if (cartcount==null)return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (myDatabase.cartDao().countCart()==0)
                    cartcount.setVisibility(View.INVISIBLE);
                else {
                    cartcount.setVisibility(View.VISIBLE);
                    cartcount.setText(String.valueOf(myDatabase.cartDao().countCart()));
                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        updatacartcount();
    }



    private void initView() {
        layouts = new int[]{
                R.layout.slide_one,
                R.layout.slide_two,
                R.layout.slide_three
                //R.layout.slide_four
                };
        cartcount = findViewById(R.id.txtcartcount);
        mAdapter = new ViewsSliderAdapter();
        binding.viewPager.setAdapter(mAdapter);
        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback);

        binding.btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchWellcomeScreen();
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    binding.viewPager.setCurrentItem(current);
                } else {
                    launchWellcomeScreen();
                }
            }
        });

        addBottomDots(0);
    }

    /*private void showMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.pager_transformers, popup.getMenu());
        popup.show();
    }*/


    private int getItem(int i){
        return binding.viewPager.getCurrentItem() + 1;
    }


    private void launchWellcomeScreen(){
        Intent intent = new Intent(this, WellcomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void launchHomeScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void addBottomDots (int currentPage){
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        binding.layoutDots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            binding.layoutDots.addView(dots[i]);

        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);

    }

    ViewPager2.OnPageChangeCallback pageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                binding.btnNext.setText(getString(R.string.start));
                binding.btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                binding.btnNext.setText(getString(R.string.next));
                binding.btnSkip.setVisibility(View.VISIBLE);
            }
        }
    };

    public class ViewsSliderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        public ViewsSliderAdapter(){

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(viewType, parent, false);
            return new SliderViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemViewType(int position) {
            return layouts[position];
        }

        @Override
        public int getItemCount() {
            return layouts.length;
        }

        public class SliderViewHolder extends RecyclerView.ViewHolder {
            public TextView title, year, genre;

            public SliderViewHolder(View view) {
                super(view);
            }
        }
    }
}
