package com.example.cryptocurrency.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cryptocurrency.R;
import com.example.cryptocurrency.adapter.CoinListAdapter;
import com.example.cryptocurrency.model.CoinsItem;
import com.example.cryptocurrency.viewModel.CoinListViewModel;

import java.util.List;

public class CoinListActivity extends AppCompatActivity {

    CoinListViewModel coinListViewModel;
    RecyclerView recyclerView;
    CoinListAdapter adapter;
    TextView tvHataMesaji;
    ProgressBar pbYukleniyor;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.coin_list);
        tvHataMesaji =findViewById(R.id.hataMesaji);
        pbYukleniyor =findViewById(R.id.yukleniyor);
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        coinListViewModel= ViewModelProviders.of(this).get(CoinListViewModel.class);
        coinListViewModel.getCoins();
        adapter = new CoinListAdapter(this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                pbYukleniyor.setVisibility(View.VISIBLE);
                tvHataMesaji.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                coinListViewModel.getCoins();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        //ilk başta tecyclerBesinAdapter boştu başta boş oluşacak sonra adapter sınıfındaki besinListesiniGüncelle metoduyla dolduracağım
        observeLiveData();


    }

    //viewmodeldaki verilerimiz livedata verilerdi gözlemlenebilir verilerdi
    //biz burada gözlemleyeceğimiz durumlara göre neler yapacağımızı belirtmemiz gerekiyor
    //gözlemlediğim veri bana liste verecek bende onu rv e verecem rv de tanımladığım setList metoduyla
    public void observeLiveData(){
        coinListViewModel.coinsMutableLiveData.observe(this, new Observer<List<CoinsItem>>() {
            @Override
            public void onChanged(List<CoinsItem> postModels) {
                adapter.setList(postModels);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

        coinListViewModel.hataMesajı.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean hataMesajı) {
                if(hataMesajı)
                {
                    tvHataMesaji.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                else{
                    tvHataMesaji.setVisibility(View.GONE);
                }

            }
        });

        coinListViewModel.yukleniyor.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean yukleniyor) {
                if(yukleniyor)
                {
                    tvHataMesaji.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    pbYukleniyor.setVisibility(View.VISIBLE);
                }
                else{
                    pbYukleniyor.setVisibility(View.GONE);
                }

            }
        });
    }
}