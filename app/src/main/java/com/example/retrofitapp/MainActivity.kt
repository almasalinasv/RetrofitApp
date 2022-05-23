package com.example.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofitapp.Remote.PokemonEntry
import com.example.retrofitapp.Remote.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitBuilder.create().getPokemonById("27")

        retrofit.enqueue(object: Callback<PokemonEntry>{
            override fun onResponse(call: Call<PokemonEntry>, response: Response<PokemonEntry>) {
                val resBody = response.body()
                if(resBody != null){
                    Log.d("retrofitresponse","res:  ${response.body()}")
                    Log.d("retrofitresponse","name:  ${response.body()?.name}")

                        for (stat in resBody.stats){
                            Log.d("retrofitresponse","stats: ${stat.stat.stat_value}: ${stat.base_stat}")
                        }
                    }

                val typesArray = response.body()?.types
                if(typesArray != null) {
                    Log.d("retrofitresponse", "type: ${typesArray[0].type.name}")
                }

                val spritesArray = response.body()?.sprites
                if(spritesArray != null){
                    Log.d("retrofitresponse", "sprites: ${spritesArray.front_default}")
                }

            }



            override fun onFailure(call: Call<PokemonEntry>, t: Throwable) {
                Log.e("retrofitresponse","error: ${t.message}")
            }
        })
    }
}