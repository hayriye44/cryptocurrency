package com.example.cryptocurrency.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cryptocurrency.RestApi.ManagerAll;
import com.example.cryptocurrency.model.CoinsItem;
import com.example.cryptocurrency.model.ResponseCoins;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoinListViewModel extends ViewModel {
    //view modelden kalıtım alınca lifecycle.ViewModel kütüphansi eklendi
    //ViewModel sınıfı aslında görünümlerimizde model arasında bir yapı veri çekme işleme busines tarafı burda yapıyoz
    //lifecycle.ViewModel sınıfı sayesinde view model sınıfımızda tek scope ta tüm yaşam döngüsüne erişebiliyoruz.
    //livedata observed(gözlemlenmek) live data gözlemlenebilir bir sınıftır. ve gözlemleyiciler bu livedatayı gözlemliyor
    //view model yaşamdöngüsü avantajı. livedata yaşam döngüsü içinde viewmodel ile birlikte veri değişikliklerini görünümlere bildirsin
    //livadatamız değiştirilebilir
    public  MutableLiveData<List<CoinsItem>> coinsMutableLiveData =new MutableLiveData<>();
    public  MutableLiveData<Boolean> hataMesajı = new MutableLiveData<>();
    public  MutableLiveData<Boolean> yukleniyor = new MutableLiveData<>();
    //  int page=1,limit=4;


    public void getCoins() {
        yukleniyor.setValue(true);
        Call<ResponseCoins> request = ManagerAll.getInstance().coinList();
        request.enqueue(new Callback<ResponseCoins>() {
            @Override
            public void onResponse(Call<ResponseCoins> call, Response<ResponseCoins> response) {
                    coinsMutableLiveData.setValue(response.body().getData().getCoins());
                    hataMesajı.setValue(false);
                    yukleniyor.setValue(false);
            }

            @Override
            public void onFailure(Call<ResponseCoins> call, Throwable t) {
                hataMesajı.setValue(true);
                yukleniyor.setValue(false);
                Log.e("kk",""+t);
            }
        });
    }



}



