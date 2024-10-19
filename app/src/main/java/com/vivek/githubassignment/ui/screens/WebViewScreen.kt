package com.vivek.githubassignment.ui.screens

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(url: String) {
    val context = LocalContext.current
    val webView = WebView(context).apply {
        settings.javaScriptEnabled = true
        webViewClient = WebViewClient()
        loadUrl(url)
    }

    AndroidView(
        factory = { webView },
        update = { it.loadUrl(url) }
    )
}