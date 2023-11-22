package app.itmaster.mobile.wallet.fragments.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import app.itmaster.mobile.wallet.fragments.DashboardsFragment
import app.itmaster.mobile.wallet.fragments.InfoFragment
import app.itmaster.mobile.wallet.fragments.WalletsFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DashboardsFragment()
            1 -> WalletsFragment()
            2 -> InfoFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

}