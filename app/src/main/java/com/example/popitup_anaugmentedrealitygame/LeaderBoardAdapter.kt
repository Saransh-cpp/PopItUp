package com.example.popitup_anaugmentedrealitygame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.popitup_anaugmentedrealitygame.models.Score
import com.example.popitup_anaugmentedrealitygame.models.User
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class LeaderBoardAdapter(options: FirestoreRecyclerOptions<Score>, private val listener: FragmentActivity?) : FirestoreRecyclerAdapter<Score, LeaderBoardAdapter.ScoreViewHolder>(
        options
) {

    class ScoreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val username: TextView = itemView.findViewById(R.id.userName)
        val highScore: TextView = itemView.findViewById(R.id.highScore)
        val userImage: ImageView = itemView.findViewById(R.id.userImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        return ScoreViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_leaderboard, parent, false))
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int, model: Score) {
        holder.username.text = model.createdBy.displayName
        holder.highScore.text = model.highScore.toString()
        Glide.with(holder.userImage.context).load(model.createdBy.imageUrl).circleCrop().into(holder.userImage)
    }
}
