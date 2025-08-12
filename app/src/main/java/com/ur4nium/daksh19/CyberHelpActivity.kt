package com.ur4nium.daksh19

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CyberHelpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cyberhelp)

        val faqPairs = listOf(
            Pair(findViewById<ImageView>(R.id.plus_icon1), findViewById<TextView>(R.id.faq1_answer)),
            Pair(findViewById<ImageView>(R.id.plus_icon2), findViewById<TextView>(R.id.faq2_answer)),
            Pair(findViewById<ImageView>(R.id.plus_icon3), findViewById<TextView>(R.id.faq3_answer)),
            Pair(findViewById<ImageView>(R.id.plus_icon4), findViewById<TextView>(R.id.faq4_answer)),
            Pair(findViewById<ImageView>(R.id.plus_icon5), findViewById<TextView>(R.id.faq5_answer)),
            Pair(findViewById<ImageView>(R.id.plus_icon6), findViewById<TextView>(R.id.faq6_answer)),
            Pair(findViewById<ImageView>(R.id.plus_icon7), findViewById<TextView>(R.id.faq7_answer)),
            Pair(findViewById<ImageView>(R.id.plus_icon8), findViewById<TextView>(R.id.faq8_answer))
        )

        faqPairs.forEach { (icon, answer) ->
            icon.setOnClickListener {
                if (answer.visibility == View.GONE) {
                    answer.visibility = View.VISIBLE
                    icon.setImageResource(R.drawable.minus)
                } else {
                    answer.visibility = View.GONE
                    icon.setImageResource(R.drawable.add_sharp)
                }
            }
        }
    }
}

