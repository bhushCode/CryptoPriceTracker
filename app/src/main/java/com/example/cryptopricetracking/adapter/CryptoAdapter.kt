package com.example.cryptopricetracking.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptopricetracking.CryptoInfoActivity
import com.example.cryptopricetracking.MainActivity
import com.example.cryptopricetracking.Models.ResponseCryptoModelItem
import com.example.cryptopricetracking.R
import com.squareup.picasso.Picasso

class CryptoAdapter(var con:Context, list: List<ResponseCryptoModelItem>) : RecyclerView.Adapter<CryptoAdapter.viewHolder>() {
 var context = con;
    var cryptolist = list;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.crypto_coins,parent,false);
        return viewHolder(view);
    }

    override fun getItemCount(): Int {

       return cryptolist.count();
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.rec_crypto_name.text = cryptolist[position].name;
        holder.rec_crypto_symbol.text = cryptolist[position].symbol.toUpperCase();
        holder.rev_crypto_price.text = String.format("%.2f",cryptolist[position].current_price.toFloat());
        Picasso.get().load(cryptolist[position].image).into(holder.rec_image_symbol);
        if(cryptolist[position].price_change_percentage_24h <0){
             holder.rec_crypto_changePercentage.text=
                 String.format("%.2f",cryptolist[position].price_change_percentage_24h.toString().toFloat())+"%"
             holder.rec_crypto_changePercentage.setTextColor(Color.parseColor("#FF0000"));
            holder.rev_image.setImageResource(R.drawable.down_red);

        }
        else{
            holder.rec_crypto_changePercentage.text= String.format("%.2f",cryptolist[position].price_change_percentage_24h.toString().toFloat())+"%"
            holder.rec_crypto_changePercentage.setTextColor(Color.parseColor("#00FF00"));
            holder.rev_image.setImageResource(R.drawable.up_green);
        }

       holder.itemView.setOnClickListener()
       {
           Log.d("mydata","clicked");
           var intent = Intent(con,CryptoInfoActivity::class.java);
           var bundle = Bundle();

           bundle.putString("market_cap",cryptolist[position].market_cap.toString());
           bundle.putString("image",cryptolist[position].image.toString());
           bundle.putString("symbol",cryptolist[position].symbol.toString());
           bundle.putString("total_volume",cryptolist[position].total_volume.toString());
           bundle.putInt("current_price",cryptolist[position].current_price.toInt())
           bundle.putString("name",cryptolist[position].name.toString())
           bundle.putInt("high_24", cryptolist[position].high_24h.toInt())
           bundle.putInt("low_24",cryptolist[position].low_24h.toInt())

           bundle.putString("price_change_percentage_24h",cryptolist[position].price_change_percentage_24h.toString())
           bundle.putString("price_change_24h",cryptolist[position].price_change_24h.toString())
           bundle.putString("total_supply",cryptolist[position].total_supply.toString())
           bundle.putString("max_supply",cryptolist[position].max_supply.toString())
           bundle.putString("circulating_supply",cryptolist[position].circulating_supply.toString())
           bundle.putString("ath",cryptolist[position].ath.toString())
           bundle.putString("ath_change_percentage",cryptolist[position].ath_change_percentage.toString())
           bundle.putString("atl",cryptolist[position].atl.toString())
           bundle.putString("atl_change_percentage",cryptolist[position].atl_change_percentage.toString())
           intent.putExtras(bundle)
           try {
               startActivity(holder.rec_crypto_changePercentage.context,intent,bundle);

           }catch (e:Exception)
           {
               Log.d("mydata",e.toString());
           }
       }


    }


    class viewHolder(item:View) : RecyclerView.ViewHolder(item)
    {
        var rec_crypto_name = item.findViewById<TextView>(R.id.rec_crypto_name);
        var rev_crypto_price = item.findViewById<TextView>(R.id.rev_crypto_price);
        var rec_crypto_symbol = item.findViewById<TextView>(R.id.rec_crypto_symbol);
        var rev_image = item.findViewById<ImageView>(R.id.rev_image);
        var rec_crypto_changePercentage = item.findViewById<TextView>(R.id.rec_crypto_changePercentage);
        var rec_image_symbol= item.findViewById<ImageView>(R.id.rev_image_symbol)



    }


}