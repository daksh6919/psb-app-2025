package com.ur4nium.daksh19
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Here you would inflate your main layout XML and return the view.
        // For this example, let's assume you have a layout file named 'fragment_home.xml'
        return inflater.inflate(R.layout.activity_profile, container, false)
    }
}