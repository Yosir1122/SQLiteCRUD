package com.example.sqlitecrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.sqlitecrud.adapter.RvAdapter
import com.example.sqlitecrud.databinding.ActivityMainBinding
import com.example.sqlitecrud.databinding.CostomDialogBinding
import com.example.sqlitecrud.db.MyDbHelper
import com.example.sqlitecrud.models.User

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var rvAdapter: RvAdapter
    lateinit var myDbHelper: MyDbHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        myDbHelper = MyDbHelper(this)
        binding.btnAdd.setOnClickListener {
            val dialog = AlertDialog.Builder(this).create()
            val costomDialogBinding = CostomDialogBinding.inflate(layoutInflater)
            costomDialogBinding.btnSave.setOnClickListener {
                val user = User(
                    1,
                    costomDialogBinding.edtName.text.toString(),
                    costomDialogBinding.edtNumber.text.toString()
                )
                myDbHelper.addUser(user)
                onResume()
                dialog.cancel()
            }
            dialog.setView(costomDialogBinding.root)
            dialog.show()
        }
    }

    override fun onResume() {
        super.onResume()
        val list = myDbHelper.showUser()
        rvAdapter = RvAdapter(list as ArrayList<User>)
        binding.rv.adapter = rvAdapter
    }
}