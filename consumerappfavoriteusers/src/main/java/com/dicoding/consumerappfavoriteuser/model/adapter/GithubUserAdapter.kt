package com.dicoding.consumerappfavoriteuser.model.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.consumerappfavoriteuser.R
import com.dicoding.consumerappfavoriteuser.databinding.ItemRowBinding
import com.dicoding.consumerappfavoriteuser.model.data.GithubUser

class GithubUserAdapter : RecyclerView.Adapter<GithubUserAdapter.GithubUserViewHolder>() {

    private val mGithubUser = ArrayList<GithubUser>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): GithubUserViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return GithubUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        val binding = ItemRowBinding.bind(holder.itemView)
        with(binding) {
            container.apply {
                if (mGithubUser.size == 1) {
                    setBackgroundResource(R.drawable.ripple_radius_bg_white)
                } else {
                    when (position) {
                        0 -> setBackgroundResource(R.drawable.ripple_top_bg_white)
                        mGithubUser.size - 1 -> setBackgroundResource(R.drawable.ripple_bottom_bg_white)
                        else -> setBackgroundResource(R.drawable.ripple_bg_white)
                    }
                }
            }
        }

        holder.bind(mGithubUser[position])
    }

    override fun getItemCount(): Int = mGithubUser.size

    fun setGithubUser(users: ArrayList<GithubUser>) {
        with(mGithubUser) {
            clear()
            addAll(users)
        }
        notifyDataSetChanged()
    }

    inner class GithubUserViewHolder(private val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(githubUser: GithubUser) {
            with(binding) {
                Glide.with(itemView)
                    .load(githubUser.avatar_url)
                    .into(imgAvatar)
                tvLogin.text = githubUser.login
            }
        }
    }
}