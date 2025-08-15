package com.ur4nium.daksh19 // Make sure this matches your project's package name

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class preventcyber : AppCompatActivity() {

    // Declare the player as nullable to manage its lifecycle
    private var player: ExoPlayer? = null
    private lateinit var playerView: PlayerView
    private lateinit var videoDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videocyber)

        // Initialize UI components from the layout
        playerView = findViewById(R.id.playerView)
        videoDescription = findViewById(R.id.videoDescription)
        videoDescription.text = "This video explains how to prevent cyber frauds"
    }

    // Use onStart to begin fetching data when the activity becomes visible
    override fun onStart() {
        super.onStart()
        // Fetch the URL from Firestore before initializing the player
        fetchVideoUrlFromFirestore()
    }

    private fun fetchVideoUrlFromFirestore() {
        // Get an instance of the Firestore database
        val db = Firebase.firestore

        // Create a reference to the specific document where the URL is stored
        // IMPORTANT: Change "videos" and "cyberAwareness" to match your collection and document names
        val videoDocRef = db.collection("preventcyber").document("video1")

        // Asynchronously get the document
        videoDocRef.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    // Retrieve the "url" field from the document
                    val videoUrl = document.getString("url")
                    if (!videoUrl.isNullOrEmpty()) {
                        // If the URL is valid, initialize the player with it
                        initializePlayer(videoUrl)
                    } else {
                        // Handle cases where the URL field is missing or empty
                        Log.e("preventcyber", "URL is null or empty in Firestore document")
                        Toast.makeText(this, "Video URL not found.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Handle cases where the document doesn't exist
                    Log.e("preventcyber", "No such document in Firestore")
                    Toast.makeText(this, "Video data not found.", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors that occur while fetching the document
                Log.e("preventcyber", "Error getting document: ", exception)
                Toast.makeText(this, "Failed to load video data.", Toast.LENGTH_SHORT).show()
            }
    }

    // This function now takes the video URL as a parameter
    private fun initializePlayer(videoUrl: String) {
        // Build the ExoPlayer instance
        player = ExoPlayer.Builder(this).build()
        // Bind the player to the PlayerView
        playerView.player = player

        // Create a MediaItem from the fetched URL
        val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
        // Set the media item for the player
        player?.setMediaItem(mediaItem)
        // Prepare the player for playback
        player?.prepare()
        // Start playing the video
        player?.play()
    }

    // Use onStop to release the player when the activity is no longer visible
    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    private fun releasePlayer() {
        // Release the player's resources to prevent memory leaks
        player?.release()
        player = null
    }
}
