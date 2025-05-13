package com.example.retrofit

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.adapter.UserAdapter
import com.example.retrofit.my_interface.ApiService
import com.example.retrofit.model.User
import com.example.retrofit.my_interface.ClickItemListener
import retrofit2.Call
import retrofit2.Callback  // ✅ thêm dòng này
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var mRcvUser: RecyclerView
    private lateinit var mListUser: MutableList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRcvUser = findViewById(R.id.rcv_users)
        val linearLayoutManager = LinearLayoutManager(this)
        mRcvUser.layoutManager = linearLayoutManager

        mListUser = ArrayList()

        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        mRcvUser.addItemDecoration(itemDecoration)

        callApiGetUsers()
    }

    private fun callApiGetUsers() {
        ApiService.apiService.getListUsers(userId = 1).enqueue(object : Callback<List<User>> {
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                val mListUser = response.body() ?: emptyList()
                val userAdapter = UserAdapter(mListUser as MutableList<User>, object : ClickItemListener {
                    override fun onClickItem(user: User) {
                        onClickGoToDetail(user)
                    }
                })
                mRcvUser.adapter = userAdapter  // ✅ sửa tên biến đúng
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "onFailure", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun onClickGoToDetail(user: User?) {
        val intent = Intent(this, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("object_user", user)
        intent.putExtras(bundle)
        this.startActivity(intent)
    }
}
