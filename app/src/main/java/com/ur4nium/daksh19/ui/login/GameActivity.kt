package com.ur4nium.daksh19

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val webView = findViewById<WebView>(R.id.webViewGame)

        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient() // Needed for JS dialogs & timers

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true // Enables local storage
        webSettings.loadsImagesAutomatically = true
        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW // If game loads HTTP content
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT
        webSettings.javaScriptCanOpenWindowsAutomatically = true // If game uses popups

        // Load the game
        webView.loadUrl("https://saksham-2202.github.io/fraud-simulator1/")
    }

    override fun onBackPressed() {
        val webView = findViewById<WebView>(R.id.webViewGame)
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}

