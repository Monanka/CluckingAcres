package com.example.cluckingacres


import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class HelpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_help, container, false)
    }
}
class HelpFragment1 : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_help)
        findViewById<TextView>(R.id.webmd_link).setOnClickListener { openLink(it.tag.toString()) }
        findViewById<TextView>(R.id.poultrysite).setOnClickListener { openLink(it.tag.toString()) }
        findViewById<TextView>(R.id.poultryhub).setOnClickListener { openLink(it.tag.toString()) }
        findViewById<TextView>(R.id.manual).setOnClickListener { openLink(it.tag.toString()) }
        findViewById<TextView>(R.id.vet).setOnClickListener { openLink(it.tag.toString())}
    }

    // Function to open a website in a browser
    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}
