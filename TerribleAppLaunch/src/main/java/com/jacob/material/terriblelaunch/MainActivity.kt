package com.jacob.material.terriblelaunch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jacob.http.HttpLoader
import com.jacob.http.MdRetrofitFactory.getLoader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var adapter: ThronesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.main_activity)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        adapter = ThronesAdapter(this)
        recyclerView.adapter = adapter
        loadThrones()
    }

    private fun loadThrones() {
        lifecycleScope.launch {
            var response:Response<List<Thrones>>
            withContext(Dispatchers.IO){
                response = getLoader(HttpLoader::class.java).getThrones();
            }
            if(response.isSuccessful){
                adapter!!.setDataList(response.body());

            }
        }
    }
}