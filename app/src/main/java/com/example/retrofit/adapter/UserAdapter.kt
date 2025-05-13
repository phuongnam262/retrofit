package com.example.retrofit.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.DetailActivity
import com.example.retrofit.R
import com.example.retrofit.model.User


class UserAdapter(
    private val context: Context,
    private val mListUsers: MutableList<User>
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = mListUsers[position]
        holder.tvId.text = user.id.toString()
        holder.tvTitle.text = user.title
        holder.layoutItem.setOnClickListener {
            // Xử lý khi item được click
            onClickGoToDetail(user)
        }
    }

    private fun onClickGoToDetail(user: User?) {
        val intent = Intent(context, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("object_user", user)
        intent.putExtras(bundle)
        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return mListUsers.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvId: TextView = itemView.findViewById(R.id.tv_id)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val layoutItem: LinearLayout = itemView.findViewById(R.id.layout_item)
    }
}
