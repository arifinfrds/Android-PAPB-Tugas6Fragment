package tugas6fragment.android.arifinfrds.com.tugas6fragment

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FirstFragment.OnFragmentInteractionListener,
        SecondFragment.OnFragmentInteractionListener {

    private var mData: String? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null

        when (item.itemId) {
            R.id.navigation_home -> {
                setTitle("Home")
                selectedFragment = FirstFragment.newInstance("test", "test")
                displayFragment(selectedFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                setTitle("Edit")

                if (mData != null) {
                    selectedFragment = SecondFragment.newInstance("Data", mData!!)
                } else {
                    mData = "not set"
                }

                Log.d("Data :", "MainActivity: mOnNavigationItemSelectedListener: mData : ${mData}")
                displayFragment(selectedFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        displayFragment(FirstFragment.newInstance("", ""));


    }


    private fun displayFragment(fragment: Fragment?) {
        if (fragment != null) {
            //Manually displaying the first fragment - one time only
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

    override fun onFragmentInteraction(uri: Uri) {
    }


    override fun onFragmentInteraction(data: String) {
        mData = data
        Log.d("Data :", "MainActivity: onFragmentInteraction: Data : ${data}")
    }
}
