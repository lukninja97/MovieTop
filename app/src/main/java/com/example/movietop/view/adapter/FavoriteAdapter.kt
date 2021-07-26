package com.example.movietop.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.movietop.databinding.RowMovieBinding
import com.example.movietop.service.model.FavoriteModel
import com.example.movietop.service.model.MovieModel
import com.example.movietop.view.AllFavoritesFragmentDirections
import com.example.movietop.view.viewholder.FavoriteViewHolder

class FavoriteAdapter(private var movies: List<FavoriteModel>?, val clickListener: (FavoriteModel) -> Unit): RecyclerView.Adapter<FavoriteViewHolder>() {

    private var mFavoriteList: List<FavoriteModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = RowMovieBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(mFavoriteList[position], clickListener)
        holder.setImage(mFavoriteList[position].poster)
        /*holder.itemView.setOnClickListener{

            val movieId = mFavoriteList[position].id
            val action = AllFavoritesFragmentDirections.actionAllFavoritesFragmentToMovieFragment(movieId)
            Navigation.findNavController(holder.itemView).navigate(action)
        }*/
    }

    override fun getItemCount(): Int {
       return mFavoriteList.count()
    }

    fun updateFavorites(list: List<FavoriteModel>){
        mFavoriteList = list
        notifyDataSetChanged()
    }

}