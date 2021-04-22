package com.example.popitup_anaugmentedrealitygame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popitup_anaugmentedrealitygame.daos.UserDao
import com.example.popitup_anaugmentedrealitygame.models.User
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_leaderboard.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LeaderboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LeaderboardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leaderboard, container, false)
    }
    private lateinit var userDao: UserDao
    private lateinit var adapter: LeaderBoardAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        leaderBoardText.setOnClickListener {
            Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show()
        }

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        userDao = UserDao()
        val userCollection = userDao.usersCollection
        val query = userCollection.orderBy("highScore", Query.Direction.ASCENDING)
        val recyclerViewOptions = FirestoreRecyclerOptions.Builder<User>().setQuery(query, User::class.java).build()

        adapter = LeaderBoardAdapter(recyclerViewOptions, this.activity)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LeaderboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LeaderboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}