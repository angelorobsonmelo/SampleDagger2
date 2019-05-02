package br.com.angelorobson.sampledagger2.modules.users.users.viewholder

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.angelorobson.sampledagger2.databinding.UsersItemBinding

class UserViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val binding: UsersItemBinding? = DataBindingUtil.bind(view)
}