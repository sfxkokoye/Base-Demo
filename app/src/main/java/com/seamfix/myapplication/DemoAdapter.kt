package com.seamfix.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DemoAdapter():RecyclerView.Adapter<DemoAdapter.MyViewHolder>()  {
    var listener: RecyclerClick? = null
    val data = DummyData()
    var apps = mutableListOf<Data>()
    var appList = mutableListOf<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.app_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//       holder.appName.text = appList[position].description

        holder.appName.text = data.name[position]
       holder.appImage.setImageResource(data.image[position])
        holder.appImage.setOnClickListener {
            listener?.onRecyclerViewItemClicked(it,data.image.toString())
        }

    }

    override fun getItemCount(): Int {
        return data.name.size
    }

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
          val appName: TextView
          var appImage: ImageView

          init {
              appName = itemView.findViewById(R.id.app_name)
              appImage = itemView.findViewById(R.id.app_image)
          }
    }

    fun setApp(app: Data) {
        this.appList = app as MutableList<Data>
    }
}