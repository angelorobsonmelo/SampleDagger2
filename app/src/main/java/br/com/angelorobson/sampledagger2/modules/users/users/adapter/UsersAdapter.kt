package br.com.angelorobson.sampledagger2.modules.users.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.angelorobson.sampledagger2.R
import br.com.angelorobson.sampledagger2.model.User
import br.com.angelorobson.sampledagger2.modules.users.users.viewholder.UserViewHolder

class UsersAdapter(private val users: MutableList<User>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.users_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding   = (holder as UserViewHolder).binding
        binding?.user = users[position]
        binding?.executePendingBindings()
    }

    override fun getItemCount() = users.size

    fun updateItems(users: MutableList<User>) {
        this.users.addAll(users)
        notifyDataSetChanged()
    }

}