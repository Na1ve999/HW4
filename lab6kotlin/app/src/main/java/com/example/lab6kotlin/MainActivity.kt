package com.example.lab6kotlin

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    inner class MyAdapter(private val data: ArrayList<Data>,private val view: Int): BaseAdapter() {

        override fun getCount(): Int {
            return data.size
        }

        override fun getItem(position: Int): Any {
            return data[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val convertView = View.inflate(parent.context, view, null)
            val name = convertView.findViewById<TextView>(R.id.name)
            name.text = data[position].name
            val imageView = convertView.findViewById<ImageView>(R.id.imageView)
            imageView.setImageResource(data[position].photo)
            return convertView
        }

    }

    private val transData = ArrayList<Data>()
    private val cubeeData = ArrayList<Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transNameArray = arrayOf("腳踏車", "機車", "汽車", "巴士")
        val transPhotoIdArray = intArrayOf(R.drawable.trans1, R.drawable.trans2, R.drawable.trans3, R.drawable.trans4)
        for (i in transNameArray.indices) {
            transData.add(Data(transPhotoIdArray[i], transNameArray[i]))
        }
        val transAdapter = MyAdapter(transData, R.layout.trans_list)
        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.adapter = transAdapter
        val messageArray = arrayOf("訊息1", "訊息2", "訊息3", "訊息4", "訊息5", "訊息6")
        val messageAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, messageArray)
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = messageAdapter
        val cubeeNameArray = arrayOf("哭哭", "發抖", "再見", "生氣", "昏倒", "竊笑", "很棒", "你好", "驚嚇", "大笑")
        val cubeePhotoIdArray = intArrayOf(R.drawable.cubee1, R.drawable.cubee2, R.drawable.cubee3, R.drawable.cubee4,
                R.drawable.cubee5, R.drawable.cubee6, R.drawable.cubee7, R.drawable.cubee8, R.drawable.cubee9, R.drawable.cubee10)
        for (i in cubeeNameArray.indices) {
            cubeeData.add(Data(cubeePhotoIdArray[i], cubeeNameArray[i]))
        }
        val cubeeAdapter = MyAdapter(cubeeData, R.layout.cubee_list)
        val gridView: GridView = findViewById(R.id.gridView)
        gridView.adapter = cubeeAdapter
        gridView.numColumns = 3
    }
}

data class Data(
    val photo: Int,
    val name: String
)