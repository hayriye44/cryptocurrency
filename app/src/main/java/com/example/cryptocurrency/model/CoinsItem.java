package com.example.cryptocurrency.model;

import android.graphics.Color;
import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.example.cryptocurrency.utils.HelperFunctions;
import com.example.cryptocurrency.view.CoinDetailActivity;

import java.io.Serializable;
import java.util.List;

public class CoinsItem extends BaseObservable implements Serializable {
	private String symbol;
	private String color;
	private boolean penalty;
	private String description;
	private String type;
	private String uuid;
	private String websiteUrl;
	private String price;
	private String iconType;
	private int rank;
	private boolean approvedSupply;
	private int id;
	private String iconUrl;
	private String slug;
	private int numberOfMarkets;
	private boolean confirmedSupply;
	private long firstSeen;
	private double change;
	private List<String> history;
	private int listedAt;
	private String name;
	private int numberOfExchanges;


	public String getSymbol(){
		return symbol;
	}

	public int getColor(){

		if(color==null)
		{color="#303030";}
		return Color.parseColor(color);
	}

	public boolean isPenalty(){
		return penalty;
	}

	public String getDescription(){
		return description;
	}

	public String getType(){
		return type;
	}

	public String getUuid(){
		return uuid;
	}

	public String getWebsiteUrl(){
		return websiteUrl;
	}


	public String getPrice(){
		Double x= Math.floor(Double.parseDouble(price)*100)/100;
		return x.toString();
	}

	public String getIconType(){
		return iconType;
	}

	public int getRank(){
		return rank;
	}


	public boolean isApprovedSupply(){
		return approvedSupply;
	}

	public int getId(){
		return id;
	}

	public String getIconUrl(){
		return iconUrl;
	}

	public String getSlug(){
		return slug;
	}


	public int getNumberOfMarkets(){
		return numberOfMarkets;
	}

	public boolean isConfirmedSupply(){
		return confirmedSupply;
	}

	public long getFirstSeen(){
		return firstSeen;
	}

	public double getChange(){
		return change;
	}

	public List<String> getHistory(){
		return history;
	}

	public int getListedAt(){
		return listedAt;
	}


	public String getName(){
		return name;
	}

	public int getNumberOfExchanges(){
		return numberOfExchanges;
	}

	@BindingAdapter("image")
	public static void loadImage(ImageView view, String imageUrl) {
		HelperFunctions helper=new HelperFunctions();
		helper.LoadImage(view,imageUrl,view.getContext());


	}
}