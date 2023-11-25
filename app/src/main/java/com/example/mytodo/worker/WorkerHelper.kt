package com.example.mytodo.worker

import android.annotation.SuppressLint
import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.mytodo.utils.NotificationHelper
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class WorkerHelper @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
) :
    CoroutineWorker(context, workerParameters) {
    private val notificationHelper by lazy { NotificationHelper(context) }

    @SuppressLint("MissingPermission")
    override suspend fun doWork(): Result {
        notificationHelper.notificationManagerCompat.notify(1, notificationHelper.getNotification())
        return Result.success()
    }

}