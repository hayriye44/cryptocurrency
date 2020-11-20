package com.example.cryptocurrency.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptocurrency.R;
import com.example.cryptocurrency.model.CoinsItem;
import com.example.cryptocurrency.utils.HelperFunctions;
import com.example.cryptocurrency.view.CoinDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class CoinListAdapter extends RecyclerView.Adapter<CoinListAdapter.CoinViewHolder>  {
    HelperFunctions helper=new HelperFunctions();
    private List<CoinsItem> coinList = new ArrayList<>();
    Context context;
    public CoinListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CoinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CoinViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_coin, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CoinViewHolder holder, final int position) {
        holder.txtSymbol.setText(coinList.get(position).getSymbol());
        holder.txtPrice.setText(coinList.get(position).getPrice()+"");
        helper.LoadImage(holder.ivIcon,coinList.get(position).getIconUrl(),context);

        try{
            holder.txtPrice.setTextColor(coinList.get(position).getColor());
            holder.txtSymbol.setTextColor(coinList.get(position).getColor());
        }catch (IllegalArgumentException e){
            holder.txtPrice.setTextColor(Color.parseColor("#303030"));
            holder.txtSymbol.setTextColor(Color.parseColor("#303030"));
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("coin", coinList.get(position));
                Intent intent = new Intent(context, CoinDetailActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            /* val action=BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayiFragment(0)
            Navigation.findNavController(it).navigate(action)*/
            }
        });


    }

    @Override
    public int getItemCount() {
        return coinList.size();
    }


    //normalde activityde veriyi cekip .notifydatasetchange diyerek yeni verileri gönderiyoruk ondan öncede listemizi yeni verilerle yüklüyorduk
    //artık bu işlemleri mvvm den dolayı view de yapmayacaz view modelde yapacaz
    //o yüzden bu fonksiyonu burdan çağıracaz
    //view modeldan yeni besinListesini alacam ve şimdiki besin listesini yenisi ile güncelleyip notifydatasetchanged ile güncellemeyi tamamlıyıcam
    //güncelleme yapılacağında bu fonk çağrılacak
     public void setList(List<CoinsItem> characterList) {
        this.coinList = characterList;
        notifyDataSetChanged();
    }


    public class CoinViewHolder extends RecyclerView.ViewHolder {
        TextView txtSymbol, txtPrice;
        ImageView ivIcon;
        public CoinViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            txtSymbol = (TextView) itemView.findViewById(R.id.txtSymbol);
            ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
        }
    }


}