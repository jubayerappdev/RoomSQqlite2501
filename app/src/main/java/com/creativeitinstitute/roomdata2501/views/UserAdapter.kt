package com.creativeitinstitute.roomdata2501.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.creativeitinstitute.roomdata2501.databinding.ItemUserBinding
import com.creativeitinstitute.roomdata2501.db.User

class UserAdapter( val listener: HandleUserClick,val userList: List<User>) : RecyclerView.Adapter<UserAdapter.userVH>(){

    interface HandleUserClick{
        fun onEditClick(user: User)
        fun onLongDeleteClick(user: User)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): userVH {

        return userVH(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: userVH,
        position: Int
    ) {
       userList[position].let {user->

           holder.binding.apply {
               tvUserName.text = "Name: ${user.name}"
               tvUserMobile.text = "Mobile: ${user.mobile}"
               tvUserAge.text = "Age: ${user.age}"

               btnEdit.setOnClickListener {
                   listener.onEditClick(user)


               }
               root.setOnLongClickListener {
                   listener.onLongDeleteClick(user)
                   true
               }
           }

       }



    }

    override fun getItemCount(): Int =userList.size


    class userVH(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root)
}