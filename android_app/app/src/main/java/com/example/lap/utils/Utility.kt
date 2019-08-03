package com.example.lap.utils

import android.app.Activity
import android.app.ActivityManager
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.lap.Lapplication
import com.example.lap.R
import com.google.android.material.snackbar.Snackbar
import java.util.*

class Utility {
    companion object {
        var progressInstance: Dialog? = null

        private val activePackagesCompat: Array<String>
            get() {
                val am =
                    Lapplication.getInstance().applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                val activePackages = HashSet<String>()
                val processInfos = am.runningAppProcesses
                for (processInfo in processInfos) {
                    if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        activePackages.addAll(Arrays.asList(*processInfo.pkgList))
                    }
                }
                return activePackages.toTypedArray()
            }

        val isApplicationInForeground: Boolean
            get() {
                var activePackages: Array<String>? = null
                activePackages = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    activePackagesCompat
                } else {
                    activePackages
                }
                if (activePackages != null) {
                    for (activePackage in activePackages) {
                        if (activePackage == Lapplication.getInstance().applicationContext.packageName) {
                            return true
                        }
                    }
                }
                return false
            }

        fun showErrorSnackBar(view: View?, context: Context?, msg: String) {

            if (context != null && view != null) {
                val snackbar = Snackbar
                    .make(view, msg, Snackbar.LENGTH_LONG)
                    .setAction("RETRY", null)

                // Changing action button text color
                val sbView = snackbar.view
                val textView = sbView.findViewById<TextView>(R.id.snackbar_text)

                sbView.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        android.R.color.holo_red_light
                    )
                )
                textView.setTextColor(ContextCompat.getColor(context, android.R.color.white))
                snackbar.show()
            }
        }

        fun showProgressDialog(activity: Activity, isCancelable: Boolean = false) {
            progressInstance?.let {
                if (it.isShowing) {
                    it.dismiss()
                }
            }
            progressInstance = ProgressDialog(activity)
        }

        fun hideProgressDialog() {
            progressInstance?.let {
                if (it.isShowing) {
                    it.dismiss()
                }
            }
        }
    }
}