package com.example.cryptopricetracking

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptopricetracking.Models.ResponseCryptoModelItem
import com.example.cryptopricetracking.adapter.CryptoAdapter
import com.example.cryptopricetracking.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.function.ToLongBiFunction

class MainActivity : AppCompatActivity() {
   lateinit var binding: ActivityMainBinding;
    lateinit var list:List<ResponseCryptoModelItem>;
   lateinit var adapter:CryptoAdapter;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
       list = ArrayList();
        binding.recview.layoutManager = LinearLayoutManager(this);
        adapter= CryptoAdapter(this,list);
        getdata();

    }
    fun getdata()
    {
        // base url =https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd
        RetrofInstance.apiinterface.getdata().enqueue(object : Callback<List<ResponseCryptoModelItem>?> {
            override fun onResponse(call: Call<List<ResponseCryptoModelItem>?>, response: Response<List<ResponseCryptoModelItem>?>) {
                Log.d("mydata","response");
                var data = response.body()!!
                adapter = CryptoAdapter(baseContext,data);
                binding.recview.adapter=adapter


            }

            override fun onFailure(call: Call<List<ResponseCryptoModelItem>?>, t: Throwable) {
                Log.d("mydata","error : "+t.toString());
                Toast.makeText(baseContext,"Connect to Internet",Toast.LENGTH_LONG).show();
            }
        })

    }
}