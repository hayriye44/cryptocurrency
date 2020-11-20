package com.example.cryptocurrency.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.cryptocurrency.R;
import com.example.cryptocurrency.databinding.ActivityCoinDetailBinding;
import com.example.cryptocurrency.model.CoinsItem;

public class CoinDetailActivity extends AppCompatActivity {
    CoinsItem coin;
    ActivityCoinDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_detail);

        Bundle extras = getIntent().getExtras();
        coin=(CoinsItem) getIntent().getSerializableExtra("coin");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_coin_detail);
        binding.setCoin(coin);
        binding.setImageUrl(coin.getIconUrl());

    }
}