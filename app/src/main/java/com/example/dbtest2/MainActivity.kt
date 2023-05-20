package com.example.dbtest2


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dbtest2.Adapter.ListPersonAdapter
import com.example.dbtest2.DBHelper.DBHelper
import com.example.dbtest2.Model.Person
import com.example.dbtest2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    internal lateinit var db: DBHelper
    internal var lstPersons:List<Person> = ArrayList<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DBHelper(this)

        refreshData()

        //Event
        binding.btnAdd.setOnClickListener {
            val person = Person(
                Integer.parseInt(binding.edtId.text.toString()),binding.edtName.text.toString(),binding.edtEmail.text.toString()
            )
            db.addPerson(person)
            refreshData()
        }

        binding.btnUp.setOnClickListener {
            val person = Person(
                Integer.parseInt(binding.edtId.text.toString()),binding.edtName.text.toString(),binding.edtEmail.text.toString()
            )
            db.updatePerson(person)
            refreshData()
        }

        binding.btnDel.setOnClickListener {
            val person = Person(
                Integer.parseInt(binding.edtId.text.toString()),binding.edtName.text.toString(),binding.edtEmail.text.toString()
            )
            db.deletePerson(person)
            refreshData()

        }
    }

    private fun refreshData() {
        lstPersons = db.allPerson
        val adapter = ListPersonAdapter(this@MainActivity,lstPersons,binding.edtId, binding.edtName, binding.edtEmail)
        binding.list.adapter = adapter
    }
}