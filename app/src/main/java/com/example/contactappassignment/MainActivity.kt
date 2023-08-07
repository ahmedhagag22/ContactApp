package com.example.contactappassignment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var userName: EditText
    lateinit var phone: EditText
    lateinit var desc: EditText
    lateinit var btn: Button

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ContactAdapter
    lateinit var contactList: MutableList<Contact>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //inflate
        userName = findViewById(R.id.editTname)
        phone = findViewById(R.id.editTPhone)
        desc = findViewById(R.id.editTextDescription)
        btn = findViewById(R.id.btnsave)
        recyclerView = findViewById(R.id.recyclerViewContact)

        contactList = ArrayList()
        adapter = ContactAdapter(contactList)
        recyclerView.adapter = adapter

        btn.setOnClickListener(View.OnClickListener {
            var name = userName.text.toString().trim()
            var phone = phone.text.toString().trim()
            var description = desc.text.toString().trim()

            if (name.length < 3) {

                var toast =
                    Toast.makeText(this, "name should be 3 characters or more", Toast.LENGTH_SHORT)
                toast.show()
            } else if (phone.length != 11) {
                var toast =
                    Toast.makeText(this, "phone should be 11 characters ", Toast.LENGTH_SHORT)
                toast.show()
            } else {
                //validation passed , add the contact to recycler
                val newContact = Contact(name, phone, description)
                contactList.add(newContact)
                adapter.notifyDataSetChanged()
                clearInputFields()


            }


        })

        adapter.onProfileClickListener = object : ContactAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, items: Contact) {
                var intent = Intent(this@MainActivity, DetilsActivty::class.java)
                intent.putExtra("name", items.Name)
                intent.putExtra("phone", items.Phone)
                startActivity(intent)
            }
        }
        adapter.onCallClickListener = object : ContactAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, items: Contact) {
                var dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:" + items.Phone)
                startActivity(dialIntent)
            }

        }
    }

    fun clearInputFields() {
        userName.setText("")
        phone.setText("")
        desc.setText("")

    }
}