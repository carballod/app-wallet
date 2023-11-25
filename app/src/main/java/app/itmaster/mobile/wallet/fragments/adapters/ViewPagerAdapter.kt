package app.itmaster.mobile.wallet.fragments.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import app.itmaster.mobile.wallet.fragments.DashboardsFragment
import app.itmaster.mobile.wallet.fragments.InfoFragment
import app.itmaster.mobile.wallet.fragments.WalletsFragment

class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return DashboardsFragment()
            1 -> return WalletsFragment()
            2 -> return InfoFragment()
        }
        return DashboardsFragment()
    }

}