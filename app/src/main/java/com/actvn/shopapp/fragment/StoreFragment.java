package com.actvn.shopapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.actvn.shopapp.R;

import com.actvn.shopapp.adapter.Store2Adapter;
import com.actvn.shopapp.adapter.StoreAdapter;
import com.actvn.shopapp.api.model.Data;
import com.actvn.shopapp.api.model.Headphone;
import com.actvn.shopapp.api.model.Products;
import com.actvn.shopapp.api.model.Tablet;
import com.actvn.shopapp.api.service.UserService;
import com.actvn.shopapp.utils.ConstApp;
import com.actvn.shopapp.views.CameraActivity;
import com.actvn.shopapp.views.CameraSecurityActivity;
import com.actvn.shopapp.views.HeadphoneActivity;
import com.actvn.shopapp.views.LaptopActivity;
import com.actvn.shopapp.views.PhoneActivity;
import com.actvn.shopapp.views.TabletActivity;
import com.actvn.shopapp.views.TvActivity;
import com.actvn.shopapp.views.WatchActivity;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class StoreFragment extends Fragment {
    private CardView cardViewMeat;
    private CardView cardViewFish;
    private CardView cardViewFruits;
    private CardView cardViewVegetable;
    private CardView cardViewTv, cardViewWatch, cardViewCamera, cardViewCameraSecurity;

    private LinearLayout lnStore2;
    private ImageView imgShow;
    private ImageView imgHide;

    private View view;
    private StoreAdapter storeAdapter;
    private Store2Adapter store2Adapter;
    private UserService userService;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewStore2;
    private List<Data> datas = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_store, container, false);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ConstApp.BASE_URL)
                .client(client.build()).addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        userService = retrofit.create(UserService.class);
        denzcokunView();
        initViewss();
        loadItem();

        return view;
    }

    // Slider
    private void denzcokunView(){
        ImageSlider imageSlider = view.findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://cdn.tgdd.vn/2020/08/banner/800-300-800x300-6.png"));
        slideModels.add(new SlideModel("https://cdn.tgdd.vn/2020/08/banner/reno4-chung-800-300-800x300.png"));
        slideModels.add(new SlideModel("https://cdn.tgdd.vn/2020/08/banner/800-300-800x300-9.png"));
        slideModels.add(new SlideModel("https://cdn.tgdd.vn/2020/08/banner/a51-71-800-300-800x300.png"));
        slideModels.add(new SlideModel("https://cdn.tgdd.vn/2020/08/banner/800-300-800x300-4.png"));
        slideModels.add(new SlideModel("https://cdn.tgdd.vn/2020/08/banner/800-300-800x300-8.png"));

        imageSlider.setImageList(slideModels,false);
    }

    private void initViewss() {
        storeAdapter = new StoreAdapter(datas, getContext());
        store2Adapter = new Store2Adapter(datas, getContext());

        /*viewFlipper = view.findViewById(R.id.viewFlipper);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);*/

        cardViewMeat = view.findViewById(R.id.cardviewMeat);
        cardViewFish = view.findViewById(R.id.cardviewFish);
        cardViewFruits = view.findViewById(R.id.cardviewFruits);
        cardViewVegetable = view.findViewById(R.id.cardviewVegetables);
        cardViewTv = view.findViewById(R.id.cardviewTv);
        cardViewWatch = view.findViewById(R.id.cardviewWatch);
        cardViewCamera = view.findViewById(R.id.cardviewCamera);
        cardViewCameraSecurity = view.findViewById(R.id.cardviewCameraSecurity);

        lnStore2 = view.findViewById(R.id.lnStore2);
        imgShow = view.findViewById(R.id.imgShow);
        imgHide = view.findViewById(R.id.imgHide);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(storeAdapter);

        recyclerViewStore2 = view.findViewById(R.id.recyclerViewStore2);
        recyclerViewStore2.setHasFixedSize(true);
        recyclerViewStore2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        recyclerViewStore2.setAdapter(storeAdapter);

        imgShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnStore2.setVisibility(View.VISIBLE);
                imgHide.setVisibility(View.VISIBLE);
                imgShow.setVisibility(View.GONE);
            }
        });

        imgHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnStore2.setVisibility(View.GONE);
                imgShow.setVisibility(View.VISIBLE);
                imgHide.setVisibility(View.GONE);
            }
        });

        cardViewMeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PhoneActivity.class);
                startActivity(intent);
            }
        });


        cardViewFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LaptopActivity.class);
                startActivity(intent);
            }
        });

        cardViewFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TabletActivity.class);
                startActivity(intent);
            }
        });

        cardViewVegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HeadphoneActivity.class);
                startActivity(intent);
            }
        });

        cardViewTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TvActivity.class);
                startActivity(intent);
            }
        });

        cardViewWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WatchActivity.class);
                startActivity(intent);
            }
        });

        cardViewCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CameraActivity.class);
                startActivity(intent);
            }
        });

        cardViewCameraSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CameraSecurityActivity.class);
                startActivity(intent);
            }
        });
    }

    private void GetNewItem() {

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    // Load Feature Product
    private void loadItem() {
        Call<Products> call = userService.getProducts();

        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if (response.isSuccessful() && response.body().getData() != null) {
                    if (!datas.isEmpty()) {
                        datas.clear();
                    }
                    datas = response.body().getData();
                    storeAdapter = new StoreAdapter(datas, getContext());
                    recyclerView.setAdapter(storeAdapter);
                    storeAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(getContext(), "No Result", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {

            }

        });
    }
}
