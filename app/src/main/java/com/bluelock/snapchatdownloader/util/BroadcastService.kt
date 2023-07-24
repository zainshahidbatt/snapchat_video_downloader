package com.bluelock.snapchatdownloader.util

import android.app.DownloadManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Environment
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.bluelock.snapchatdownloader.db.Database
import com.bluelock.snapchatdownloader.models.FVideo

class BroadcastService : Service() {
    var db: Database? = null
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate: broadcastService")
        db = Database.init(applicationContext)
        registerBroadcastReceiver()
    }

    private fun registerBroadcastReceiver() {
        Log.d(TAG, "registerBroadcastReceiver: broadcast service")
        val downloadComplete: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (Constants.downloadVideos.containsKey(id)) {
                    Log.d("receiver", "onReceive: download complete")
                    val fVideo = db!!.getVideo(id)
                    val videoPath: String = Environment.getExternalStorageDirectory().toString() +
                            "/Download" + Utils.RootDirectorySnapchat + fVideo.fileName

                    Toast.makeText(applicationContext, "Download complete", Toast.LENGTH_SHORT)
                        .show()
                    db!!.updateState(id, FVideo.COMPLETE)
                    db!!.setUri(id, videoPath)
                    Constants.downloadVideos.remove(id)
                }
            }
        }
        registerReceiver(
            downloadComplete,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )
    }

    companion object {
        private const val TAG = "BroadcastService"
    }
}