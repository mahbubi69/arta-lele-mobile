package com.ibnu.artalele.ui.extra.category.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ibnu.artalele.ui.extra.category.IncomeCategoryFragment
import com.ibnu.artalele.ui.extra.category.SpendingCategoryFragment

class CategoryViewPagerAdapter(fa: FragmentActivity, private val tabTitles: IntArray) :
    FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = tabTitles.size

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null

        when (position) {
            0 -> {
                fragment = IncomeCategoryFragment()
            }
            1 -> {
                fragment = SpendingCategoryFragment()
            }
        }

        return fragment as Fragment;
    }
}