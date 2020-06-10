package com.matchxz.lzhy.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.matchxz.lzhy.Config
import com.matchxz.lzhy.R
import com.matchxz.lzhy.ui.WebActivity
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : Fragment() {

    private lateinit var mineViewModel: MineViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mineViewModel =
            ViewModelProvider(this).get(MineViewModel::class.java)
        return inflater.inflate(R.layout.fragment_mine, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        layout_agreement.setOnClickListener {
            WebActivity.startActivity(requireContext(), Config.AGREEMENT)
        }

        layout_policy.setOnClickListener {
            WebActivity.startActivity(requireContext(), Config.PRIVACY_POLICY)
        }
    }
}