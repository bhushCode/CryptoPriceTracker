
package com.example.cryptopricetracking

import android.graphics.Color
import android.media.tv.TvContract.Programs
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import com.example.cryptopricetracking.databinding.ActivityCryptoInfoBinding
import com.squareup.picasso.Picasso

class CryptoInfoActivity : AppCompatActivity() {
   lateinit var binding:ActivityCryptoInfoBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCryptoInfoBinding.inflate(layoutInflater);
        setContentView(binding.root);
        //for action bar name
       supportActionBar?.setTitle(intent.getStringExtra("name"))
        //for price textview
        binding.cryptoInfoPrice.text=intent.getIntExtra("current_price",0).toString();
        //for symbol of cryptocoin
        binding.cryptoInfoSymbol.text=intent.getStringExtra("symbol").toString();

        if(intent.getStringExtra("price_change_percentage_24h").toString() < 0.toString()){
            binding.cryptoInfoChangePercentage.text=
                String.format("%.2f",intent.getStringExtra("price_change_percentage_24h").toString().toFloat())+"%"
            binding.cryptoInfoChangePercentage.setTextColor(Color.parseColor("#FF0000"));
            binding.cryptoImage.setImageResource(R.drawable.down_red);

        }
        else{
            binding.cryptoInfoChangePercentage.text= String.format("%.2f",intent.getStringExtra("price_change_percentage_24h").toString().toFloat())+"%"
            binding.cryptoInfoChangePercentage.setTextColor(Color.parseColor("#00FF00"));
            binding.cryptoImage.setImageResource(R.drawable.up_green);
        }

        //for bitcoid logo
        Picasso.get().load(intent.getStringExtra("image")).into(binding.cryptoInfoImageSymbol);
        binding.cryptoInfoMarketCap.append(intent.getStringExtra("market_cap"))
        binding.cryptoInfoTotalVolume.append(intent.getStringExtra("total_volume"))

        val high24 = intent.getIntExtra("high_24",0)
        val low24 = intent.getIntExtra("low_24",0)
        val currentPrice = intent.getIntExtra("current_price",0);

        val highPrice24= high24-currentPrice;
        val lowPrice24 = currentPrice - low24;





        // for high_price textview
        binding.cryptoInfoHigh24h.setTextColor(Color.parseColor("#00FF00"));//green code
        binding.cryptoInfoHigh24h.append("+"+highPrice24.toString())

        //for low_price textview
        binding.cryptoInfoLow24h.setTextColor(Color.parseColor("#FF0000"))//red code
        binding.cryptoInfoLow24h.append("-"+lowPrice24)

        binding.cryptoInfoPriceChange24h.setTextColor(Color.parseColor("#FFFF00"))//red code
        binding.cryptoInfoPriceChange24h.text=String.format("%.2f",intent.getStringExtra("price_change_24h")?.toFloat())


        //all time high and all time low
        binding.cryptoInfoAth.text=(intent.getStringExtra("ath"));
        binding.cryptoInfoAtl.text=(intent.getStringExtra("atl"))

        var athpercentage = String.format("%.2f",intent.getStringExtra("ath_change_percentage")?.toFloat());
         binding.cryptoInfoAthPercentange.text="  ("+athpercentage+"%)";

        var atlpercentage = String.format("%.2f",intent.getStringExtra("atl_change_percentage")?.toFloat());
         binding.cryptoInfoAtlPercentange.text="  ("+atlpercentage+"%)";


        // supply
        binding.cryptoInfoTotalSupply.append(intent.getStringExtra("total_supply"));
        binding.cryptoInfoMaxSupply.append(intent.getStringExtra("max_supply"));
        binding.cryptoInfoCirculatingSupply.append(intent.getStringExtra("circulating_supply"));





    }
}