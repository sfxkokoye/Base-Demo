package com.seamfix.myapplication

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.seamfix.myapplication.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity: AppCompatActivity(), RecyclerClick {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: AppViewmodel
    var demoAdapter = DemoAdapter()
    lateinit var appList: Data

    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = androidx.lifecycle.ViewModelProvider(this).get(AppViewmodel::class.java)

        binding.appRecycler.layoutManager = GridLayoutManager(this, 2)
        val adapter = DemoAdapter()
        binding.appRecycler.adapter = adapter
        demoAdapter.listener = this


        getParam()
    }

    override fun onRecyclerViewItemClicked(view: View, s: String) {

        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("mars://main/new"))
        val downloadeIntent = Intent(Intent.ACTION_VIEW, Uri.parse("mars://main/new"))
        when (view.id) {
            R.id.app_image -> {
                try {
                    startActivity(appIntent)
                } catch (e: ActivityNotFoundException) {
                    try {
                        startActivity(downloadeIntent)
                    } catch (e: Exception) {
                        Toast.makeText(this, "app not found", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

}

//    fun getApps(){
//        viewModel.getApps()
//        viewModel.appList.observe(this, Observer<List<App>>{
//            demoAdapter.setApp(it)
//            demoAdapter.notifyDataSetChanged()
//        })
//    }

    fun getParam(){
        viewModel.getAllParams()
        viewModel.param.observe(this, {
            appList = it
            println(appList)
            demoAdapter.setApp(it)
            demoAdapter.notifyDataSetChanged()
        })
    }
}