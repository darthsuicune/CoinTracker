package com.dlgdev.cointracker.ui

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.support.v4.app.ActivityCompat.requestPermissions
import android.support.v4.content.ContextCompat.checkSelfPermission
import javax.inject.Inject

class PictureHelper @Inject constructor() {
    companion object {
        const val REQUEST_READ_STORAGE = 1234
        const val OPEN_PICTURE = 1212
    }

    fun requestGallery(activity: Activity) {
        if (checkSelfPermission(activity, READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
//            if (shouldShowRequestPermissionRationale(activity, READ_EXTERNAL_STORAGE)) {
//                AlertDialog.Builder(activity)
//
//                        .show()
//
//            } else {
            requestPermissions(activity, arrayOf(READ_EXTERNAL_STORAGE), REQUEST_READ_STORAGE)
//            }
        } else {
            actuallyOpenTheFuckingGallery(activity)
        }
    }

    fun checkPermissionResults(activity: Activity, requestCode: Int,
                               grantResults: IntArray) {
        when (requestCode) {
            REQUEST_READ_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PERMISSION_GRANTED) {
                    actuallyOpenTheFuckingGallery(activity)
                }
            }
            else -> {
            }
        }
    }

    fun actuallyOpenTheFuckingGallery(activity: Activity) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        activity.startActivityForResult(intent, OPEN_PICTURE)
    }

    fun persistTheUriPermission(uri: Uri, contentResolver: ContentResolver, intent: Intent) {
        val takeFlags =
                intent.flags and (FLAG_GRANT_READ_URI_PERMISSION or FLAG_GRANT_WRITE_URI_PERMISSION)
        // Check for the freshest data.
        contentResolver.takePersistableUriPermission(uri, takeFlags)
    }
}