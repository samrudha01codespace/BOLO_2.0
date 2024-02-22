package com.samrudhasolutions.bolo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.samrudhasolutions.bolo.classes.FolderAdapter

class FirebaseActivity : AppCompatActivity() {

    private lateinit var folderRecyclerView: RecyclerView
    private lateinit var folderAdapter: FolderAdapter
    private lateinit var folderList: MutableList<String>
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance()

        // Initialize RecyclerView
        folderRecyclerView = findViewById(R.id.folderRecyclerView)
        folderRecyclerView.layoutManager = LinearLayoutManager(this)
        folderList = ArrayList()
        folderAdapter = FolderAdapter(folderList) { position ->
            val selectedFolder = folderList[position]
            fetchVideos(selectedFolder)
        }
        folderRecyclerView.adapter = folderAdapter

        // Fetch folders from Firestore
        fetchFolders()
    }

    private fun fetchFolders() {
        db.collection("folders")
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                folderList.clear()
                for (documentSnapshot in queryDocumentSnapshots.documents) {
                    val folderName = documentSnapshot.id
                    folderList.add(folderName)
                }
                folderAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Error fetching folders: ", e)
                Toast.makeText(this@FirebaseActivity, "Error fetching folders", Toast.LENGTH_SHORT).show()
            }
    }

    private fun fetchVideos(folderName: String) {
        db.collection("folders")
            .document(folderName)
            .collection("videos")
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                val videoList = ArrayList<String>() // Changed to store video URLs as strings
                for (documentSnapshot in queryDocumentSnapshots.documents) {
                    val videoUrl = documentSnapshot.getString("video_url")
                    if (videoUrl != null) {
                        videoList.add(videoUrl)
                    }
                }
                // Now you have the list of video URLs, you can pass it to your adapter or handle it as required.
                // Here you can open a new activity or fragment to display the video list for the selected folder.
                // For demonstration purpose, I'll just log the video URLs.
                Log.d(TAG, "Video URLs for folder $folderName: $videoList")
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Error fetching videos: ", e)
                Toast.makeText(this@FirebaseActivity, "Error fetching videos", Toast.LENGTH_SHORT).show()
            }
    }

    companion object {
        private const val TAG = "com.samrudhasolutions.bolo.FirebaseActivity"
    }
}
