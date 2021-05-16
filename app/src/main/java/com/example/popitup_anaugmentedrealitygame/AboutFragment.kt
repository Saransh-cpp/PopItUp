package com.example.popitup_anaugmentedrealitygame

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_leaderboard.*
import kotlinx.android.synthetic.main.fragment_leaderboard.adView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AboutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutFragment : Fragment() {
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MobileAds.initialize(this.activity!!.applicationContext) {}

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        val builder =  CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()

        githubLink.setOnClickListener {
            customTabsIntent.launchUrl(this.activity, Uri.parse("https://github.com/Saransh-cpp/PopItUp"))
        }

        name1.setOnClickListener {
            customTabsIntent.launchUrl(this.activity, Uri.parse("https://github.com/Saransh-cpp"))
        }

        name2.setOnClickListener {
            customTabsIntent.launchUrl(this.activity, Uri.parse("https://shreyabhoj.github.io/"))
        }

        name3.setOnClickListener {
            customTabsIntent.launchUrl(this.activity, Uri.parse("https://www.linkedin.com/in/parth-tripathi-689506202/"))
        }

        name4.setOnClickListener {
            customTabsIntent.launchUrl(this.activity, Uri.parse("https://www.linkedin.com/in/onkar-mahapatra-921106200/"))
        }

        name5.setOnClickListener {
            customTabsIntent.launchUrl(this.activity, Uri.parse("https://www.instagram.com/anant._.saxena/"))
        }

        name6.setOnClickListener {
            customTabsIntent.launchUrl(this.activity, Uri.parse("https://www.instagram.com/akshat_jain62/"))
        }
    }

    override fun onPause() {
        adView.pause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        adView.resume()
    }

    override fun onDestroyView() {
        adView.destroy()
        super.onDestroyView()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AboutFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                AboutFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}