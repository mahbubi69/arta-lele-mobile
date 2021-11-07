package com.ibnu.artalele.ui.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ibnu.artalele.R
import com.ibnu.artalele.ui.home.pemasukan.IncomeFragment
import com.ibnu.artalele.ui.home.pengeluaran.SpendingFragment

class HomeViewPagerAdapter(fa: FragmentActivity, private val tabTitles: IntArray) :
    FragmentStateAdapter(fa) {

//    private val tabTitles = intArrayOf(
//        R.string.pemasukan,
//        R.string.pengeluaran
//    )

    override fun getItemCount(): Int = tabTitles.size

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null

        when (position) {
            0 -> {
                fragment = IncomeFragment()
            }
            1 -> {
                fragment = SpendingFragment()
            }
        }

        return fragment as Fragment;
    }
}