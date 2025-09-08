package com.creativeitinstitute.roomdata2501.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.creativeitinstitute.roomdata2501.R
import com.creativeitinstitute.roomdata2501.databinding.ActivityAddUserBinding
import com.creativeitinstitute.roomdata2501.databinding.ActivityMainBinding
import com.creativeitinstitute.roomdata2501.db.User
import com.creativeitinstitute.roomdata2501.db.UserDao
import com.creativeitinstitute.roomdata2501.db.UserDatabase

@Suppress("DEPRECATION")
class AddUserActivity : AppCompatActivity() {

    companion object{
        const val editKey = "edit"
        const val update = "Update User"
        const val add = "Add User"
    }
    private lateinit var binding: ActivityAddUserBinding
    private lateinit var dao: UserDao
    var userId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "User_DB"
        ).allowMainThreadQueries().build()

         dao = db.getUserDao()

        if(intent.hasExtra(editKey)){
            binding.btnAddUser.text = update
            var user = intent.getParcelableExtra<User>(editKey)
            user?.let {
                binding.apply {
                    etUserName.setText(it.name)
                    etUserMobile.setText(it.mobile)
                    etUserAge.setText(it.age.toString())

                    userId = user.id


                }
            }



        }else{
            binding.btnAddUser.text = add
        }

        binding.btnAddUser.setOnClickListener {

            val name = binding.etUserName.text.toString().trim()
            val age = binding.etUserAge.text.toString().trim()
            val mobile = binding.etUserMobile.text.toString().trim()

            if (binding.btnAddUser.text.toString()==add){
                addUser(name, age, mobile)
            }else{
                updateUser(name, age, mobile)
            }


        }
    }

    private fun updateUser(name: String, age: String, mobile: String) {
        Toast.makeText(this, "Update Successfully...", Toast.LENGTH_LONG).show()
        val user = User(userId, name, age.toInt(), mobile)
        dao.editUser(user)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun addUser(name: String, age: String, mobile: String) {
        val user = User(0, name, age.toInt(), mobile)
        dao.addUser(user)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}


