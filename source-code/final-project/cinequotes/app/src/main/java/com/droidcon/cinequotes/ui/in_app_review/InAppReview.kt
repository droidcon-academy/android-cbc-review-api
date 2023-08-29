package com.droidcon.cinequotes.ui.in_app_review

import android.content.ContentValues.TAG
import android.util.Log
import androidx.activity.ComponentActivity
import com.google.android.play.core.review.ReviewException
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.review.model.ReviewErrorCode

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
                        handleReviewErrorCodes(it.exception)
                    }
                }
            } else {
                Log.w(TAG, "There was a problem requesting the review flow ${task.exception}")
            }
        }
    }

    private fun handleReviewErrorCodes(exception: Exception?) {
        if (exception is ReviewException) {
            when (exception.errorCode) {
                ReviewErrorCode.INTERNAL_ERROR -> {
                    Log.w(TAG, "Rating failed: Internal error")
                }
                ReviewErrorCode.INVALID_REQUEST -> {
                    Log.w(TAG, "Rating failed: Invalid request")
                }
                ReviewErrorCode.PLAY_STORE_NOT_FOUND -> {
                    Log.w(TAG, "Rating failed: Play Store not found")
                }
                else -> {
                    Log.w(TAG, "Rating failed")
                }
            }
        }
    }
}
