package com.actvn.shopapp.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.actvn.shopapp.MainActivity;
import com.actvn.shopapp.R;
import com.actvn.shopapp.WellcomeActivity;
import com.actvn.shopapp.api.model.User;
import com.actvn.shopapp.api.service.UserService;
import com.actvn.shopapp.login.LoginActivity;
import com.actvn.shopapp.order.OrderActivity;
import com.actvn.shopapp.utils.ConstApp;
import com.actvn.shopapp.utils.ShareStoreUtils;

import java.util.List;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProfileFragment extends Fragment {

    private LinearLayout lnLoginProfile;
    private LinearLayout lnProfile;
    private LinearLayout lnOrder;
    private LinearLayout lnSetting;
    private LinearLayout lnLogout;

    private View view;
    private LinearLayout lnInforProfile;
    private Button btnLogoutProfile;

    private ImageView imgEditProfile;
    private ImageView imgProfile;

    private TextView txtNameProfile;
    private TextView txtEmailProfile;
    private TextView txtPhoneProfile;
    private TextView txtAddressProfile;

    private UserService userService;
    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ConstApp.BASE_URL)
                .client(client.build()).addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        userService = retrofit.create(UserService.class);

        initView();

    }

    private void initView() {
        lnLoginProfile = view.findViewById(R.id.lnLoginProfile);
        lnProfile = view.findViewById(R.id.lnProfile);
        lnOrder = view.findViewById(R.id.lnOrder);
        lnSetting = view.findViewById(R.id.lnSetting);
        lnLogout = view.findViewById(R.id.lnLogout);

        imgEditProfile = view.findViewById(R.id.imgEditProfile);
        imgProfile = view.findViewById(R.id.imgProfile);

        txtNameProfile = view.findViewById(R.id.txtNameProfile);
        txtEmailProfile = view.findViewById(R.id.txtEmailProfile);
        txtPhoneProfile = view.findViewById(R.id.txtPhoneProfile);
        txtAddressProfile = view.findViewById(R.id.txtAddressProfile);

        if (ShareStoreUtils.getToken(getContext()) != null) {
            lnLoginProfile.setVisibility(View.GONE);
            imgEditProfile.setVisibility(View.VISIBLE);
            imgProfile.setVisibility(View.VISIBLE);
            txtNameProfile.setVisibility(View.VISIBLE);
            txtEmailProfile.setVisibility(View.VISIBLE);
            txtPhoneProfile.setVisibility(View.VISIBLE);
            txtAddressProfile.setVisibility(View.VISIBLE);
            loadJson();

            lnOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), OrderActivity.class);
                    startActivity(intent);
                }
            });

            lnSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            lnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShareStoreUtils.saveToken(getContext(), null);
                    Intent intent = new Intent();
                    intent.setClass(getContext(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });

        } else {
            lnLoginProfile.setVisibility(View.VISIBLE);
            lnLogout.setVisibility(View.GONE);
            imgEditProfile.setVisibility(View.GONE);
            imgProfile.setVisibility(View.GONE);
            txtNameProfile.setVisibility(View.GONE);
            txtEmailProfile.setVisibility(View.GONE);
            txtPhoneProfile.setVisibility(View.GONE);
            txtAddressProfile.setVisibility(View.GONE);

            lnLoginProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });

            lnProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });

        }
    }

    private void loadJson() {
        String token_type = "Bearer ";
        Call<User> call = userService.getUser(String.valueOf(token_type + ShareStoreUtils.getToken(getContext())));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null) {
                    user = response.body();
                    txtNameProfile.setText(user.getName());
                    txtEmailProfile.setText(user.getEmail());
                    txtPhoneProfile.setText(user.getPhone());
                    txtAddressProfile.setText(user.getAddress1());
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


    }
}
