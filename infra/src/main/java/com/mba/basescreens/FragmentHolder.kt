package com.mba.basescreens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mba.extensions.R
import kotlinx.android.synthetic.main.fragment_base.*

class FragmentHolder : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.getString("frag")?.let { fragmentName ->
            val screenClass = (Class.forName(fragmentName) as Class<out BaseScreen>)
            val fragment = screenClass.newInstance() as BaseFragment

            childFragmentManager.beginTransaction().add(
                R.id.baseLayout, fragment
            )
        }
    }
}