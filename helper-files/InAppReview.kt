package com.droidcon.cinequotes.ui.theme.in_app_review

import android.content.ContentValues.TAG
import android.util.Log
import androidx.activity.ComponentActivity
import com.google.android.play.core.review.ReviewManagerFactory

class InAppReview {

    fun requestReview(activity: ComponentActivity) {
        val reviewManager = ReviewManagerFactory.create(activity)

        val request = reviewManager.requestReviewFlow()

        request.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val reviewInfo = task.result

                val reviewFlow = reviewManager.launchReviewFlow(activity, reviewInfo)
                reviewFlow.addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.w(TAG, "Rating successful")
                    } else {
                        Log.w(TAG, "Rating failed")
                    }
                }
            } else {
                Log.w(TAG, "There was a problem requesting the review flow ${task.exception}")
            }
        }
    }
}
