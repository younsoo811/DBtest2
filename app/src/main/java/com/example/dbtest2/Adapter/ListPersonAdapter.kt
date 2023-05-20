package com.example.dbtest2.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.example.dbtest2.Model.Person
import com.example.dbtest2.R
import com.example.dbtest2.databinding.LowLayoutBinding

class ListPersonAdapter (internal val activity:Activity,
                         internal val lstPerson: List<Person>,
                         internal val edt_id:EditText,
                         internal val edt_name:EditText,
                         internal val edt_email:EditText):BaseAdapter() {

    internal var inflater:LayoutInflater

    private lateinit var binding: LowLayoutBinding

    init {
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView:View
        rowView = inflater.inflate(R.layout.low_layout,null)

        binding = LowLayoutBinding.inflate(inflater)

        binding.rowId.text = lstPerson[position].id.toString()
        binding.rowName.text = lstPerson[position].name.toString()
        binding.rowEmail.text = lstPerson[position].email.toString()

        //Event
        binding.root.setOnClickListener{
            edt_id.setText(binding.rowId.text.toString())
            edt_name.setText(binding.rowName.text.toString())
            edt_email.setText(binding.rowEmail.text.toString())
        }
        return rowView
    }

    override fun getItem(position: Int): Any {
        return lstPerson[position]
    }

    override fun getItemId(position: Int): Long {
        return lstPerson[position].id.toLong()
    }

    override fun getCount(): Int {
        return lstPerson.size
    }
}