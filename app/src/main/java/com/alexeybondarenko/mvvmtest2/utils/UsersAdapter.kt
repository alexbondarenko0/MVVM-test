package com.alexeybondarenko.mvvmtest2.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexeybondarenko.mvvmtest2.R
import com.alexeybondarenko.mvvmtest2.databinding.UsersListItemBinding
import com.alexeybondarenko.mvvmtest2.model.UserModel
import com.squareup.picasso.Picasso

class UsersAdapter(
    private var usersList: List<UserModel>,
    private val context: Context?
) : RecyclerView.Adapter<UsersAdapter.UserHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val itemBinding =
            UsersListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserHolder(itemBinding, context)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user: UserModel = usersList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = usersList.size

    class UserHolder(private val itemBinding: UsersListItemBinding, private val context: Context?) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(user: UserModel) {
            itemBinding.tvLogin.text = user.login
            itemBinding.tvID.text = "ID: ${user.id}"
            Picasso.with(context)
                .load(user.avatarUrl)
                .placeholder(context?.resources?.getDrawable(R.drawable.ic_launcher_foreground))
                .into(itemBinding.ivAvatar)
        }
    }
}