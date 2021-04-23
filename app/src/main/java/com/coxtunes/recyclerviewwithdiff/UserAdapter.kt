package com.coxtunes.recyclerviewwithdiff

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.coxtunes.recyclerviewwithdiff.databinding.LayoutUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(private var binding: LayoutUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(user: User) {
            binding.tvName.text = user.name
            binding.tvEmail.text = user.email
        }
    }

    /*
    * Call back for Async Item
    * */
    private val differCallBack = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    /*
    * Assign call back to listDiffer & need to call this variable to set data
    * */
    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_user,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = differ.currentList[position]
        holder.bindView(user)
        holder.itemView.setOnClickListener {
            actionListener.invoke(user)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    internal var actionListener: (user: User) -> Unit = { _ -> }
}