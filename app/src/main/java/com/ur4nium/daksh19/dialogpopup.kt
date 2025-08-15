package com.ur4nium.daksh19

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ur4nium.daksh19.databinding.ActivityDialogpopupBinding

class MyPopupDialog : DialogFragment() {

    // Use view binding to access the views in the layout.
    private lateinit var binding: ActivityDialogpopupBinding

    // This method is used to create and customize the dialog.
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        // Inflate the layout using ViewBinding
        binding = ActivityDialogpopupBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        // Dialog window styling for pop-up effect and blurred background
        dialog.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawableResource(android.R.color.transparent)
            addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            setDimAmount(0.5f) // Adjust dim amount for the blurred background effect
        }

        // Firebase image slider setup
        val imageList = ArrayList<SlideModel>()
        val db = Firebase.firestore

        db.collection("dashboard_slider")
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    Log.d("Firestore", "No images found in the collection.")
                    return@addOnSuccessListener
                }
                for (document in documents) {
                    val imageUrl = document.getString("url")
                    if (imageUrl != null) {
                        imageList.add(SlideModel(imageUrl, ScaleTypes.CENTER_CROP))
                    }
                }
                binding.imageSlider.setImageList(imageList)
            }
            .addOnFailureListener { exception ->
                Log.w("Firestore", "Error getting documents: ", exception)
            }

        // Correctly access views using the 'binding' object
        // The previous code had a NullPointerException here because it couldn't find the views.
        val faqPairs = listOf(
            Pair(binding.plusIcon1, binding.faq1Answer),
            Pair(binding.plusIcon2, binding.faq2Answer),
            Pair(binding.plusIcon3, binding.faq3Answer),
            Pair(binding.plusIcon4, binding.faq4Answer),
            Pair(binding.plusIcon5, binding.faq5Answer),
            Pair(binding.plusIcon6, binding.faq6Answer)
        )

        // Loop through the pairs and set up the click listeners
        faqPairs.forEach { (icon, answer) ->
            icon.setOnClickListener {
                if (answer.visibility == View.GONE) {
                    // Show the answer and change the icon to 'minus'
                    answer.visibility = View.VISIBLE
                    icon.setImageResource(R.drawable.minus)
                } else {
                    // Hide the answer and change the icon back to 'add_sharp'
                    answer.visibility = View.GONE
                    icon.setImageResource(R.drawable.add_sharp)
                }
            }
        }

        dialog.setCanceledOnTouchOutside(true)
        return dialog
    }
}
