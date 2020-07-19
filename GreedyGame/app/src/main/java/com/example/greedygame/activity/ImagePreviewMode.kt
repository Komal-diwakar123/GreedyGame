package com.example.greedygame.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.greedygame.R
import com.example.greedygame.imageloader.NetworkImageView
import com.example.greedygame.imageloader.ReddItImageNetworkImageView

class ImagePreviewMode : AppCompatActivity() {

    val IMAGE_URL = "image_url"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_preview_mode)
        initUI()
    }

    private fun initUI() {
        val backButton: ImageView = findViewById(R.id.back_button)
        backButton.setOnClickListener { v: View? -> finish() }
        showProgressBar(View.VISIBLE)
        loadImage()
    }

    private fun loadImage() {
        val imageURI =
            intent.getStringExtra(IMAGE_URL)

        if (imageURI != null) {
            val imageView: ReddItImageNetworkImageView = findViewById(R.id.image_preview)
            imageView.loadImage(imageURI, object : NetworkImageView.NetworkImageViewListener {
                override fun onImageSuccessfullyLoaded() {
                    showProgressBar(View.GONE)
                }

                override fun onImageFailedToLoad() {
                    showProgressBar(View.GONE)
                }
            })
        }
    }

    private fun showProgressBar(visible: Int) {
        val progressBar: ProgressBar = findViewById(R.id.image_progress_bar)
        progressBar.visibility = visible
    }
}