package com.coxtunes.recyclerviewwithdiff

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.coxtunes.recyclerviewwithdiff.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var useradapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        useradapter = UserAdapter()
        useradapter.differ.submitList(loadUserData())

        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = useradapter
        }

        useradapter.actionListener = {
            Toast.makeText(this, "Click on ${it.name}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadUserData(): MutableList<User> {
        return mutableListOf(
            User("USER 1", "user1@gmail.com"),
            User("USER 2", "user2@gmail.com"),
            User("USER 3", "user3@gmail.com")
        )
    }
}