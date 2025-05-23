package com.example.retrofit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit.model.User
import com.example.retrofit.my_interface.AuthApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    internal lateinit var email: EditText
    internal lateinit var password: EditText
    private lateinit var btnLogin: Button
    internal lateinit var mUser: User

    internal lateinit var mListUser: List<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        email=findViewById(R.id.et_email)
        password=findViewById(R.id.et_password)
        btnLogin=findViewById(R.id.btn_login)
        mListUser = emptyList()

        // Đặt listener ngay lập tức
        btnLogin.setOnClickListener {
            clickLogin()
        }


        callApiGetUsers();
    }

    private fun callApiGetUsers() {
        AuthApi.apiService.getUsers().enqueue(object : Callback<AuthApi.UserResponse> {
            override fun onResponse(call: Call<AuthApi.UserResponse>, response: Response<AuthApi.UserResponse>) {
                mListUser = response.body()?.users ?: emptyList()
                Log.d("ListUser", "${mListUser.size}")
            }

            override fun onFailure(call: Call<AuthApi.UserResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "CallAPI Fail: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}

private fun LoginActivity.clickLogin() {
    if (mListUser.isEmpty()) {
        Toast.makeText(this, "User data not loaded yet", Toast.LENGTH_SHORT).show()
        return
    }

    val emailInput = email.text.toString().trim()
    val passwordInput = password.text.toString().trim()
    if (emailInput.isEmpty() || passwordInput.isEmpty()) {
        Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
        return
    }
    for (user in mListUser) {
        if (user.username == emailInput && user.password == passwordInput) {
            mUser = user
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("object_user", mUser)
            intent.putExtras(bundle)
            startActivity(intent)
            return
        }
    }
// Nếu không khớp với bất kỳ user nào:
    Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
}
