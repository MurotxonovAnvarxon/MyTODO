package com.example.mytodo.presentor.screens.add

import android.app.TimePickerDialog
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.mytodo.worker.WorkerHelper
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit


class AddScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val vm = getViewModel<AddViewModel>()
        AddScreenContent(vm::onEventDispatcher)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreenContent(
    onEventDispatcher: (AddContract.Intent) -> Unit

) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }


    val mContext = LocalContext.current

    // Declaring and initializing a calendar
    val mCalendar = Calendar.getInstance()
    var mHour by remember {
        mutableStateOf(0L)
    }
    var mMinute by remember {
        mutableStateOf(0L)
    }

    // Value for storing time as a string
    var mTime by remember { mutableStateOf(0L) }
    var mTime2 by remember { mutableStateOf("") }


    var timeX = 0L
    // Creating a TimePicker dialog
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        { _, mHour: Int, mMinute: Int ->
            mTime2 = "${mHour}:${mMinute}"
            mTime = mHour * 3600L + mMinute * 60L + Calendar.getInstance().timeInMillis
            Log.d("TAG", "${mTime} ")
        }, mHour.toInt(), mMinute.toInt(), false
    )

    timeX = Calendar.getInstance().timeInMillis
    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
    val currentDateAndTime: String = sdf.format(Date())

    val myTime = mTime - timeX

    val selectTime =
        OneTimeWorkRequestBuilder<WorkerHelper>()
            .setInitialDelay(myTime, TimeUnit.MILLISECONDS)
            .build()


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // On button click, TimePicker is
        // displayed, user can select a time
        Button(
            onClick = { mTimePickerDialog.show() },
            colors = ButtonDefaults.buttonColors(Color(0XFF0F9D58))
        ) {
            Text(text = "Open Time Picker", color = Color.White)
        }

        // Add a spacer of 100dp
        Spacer(modifier = Modifier.size(100.dp))

        // Display selected time
        Text(text = "Selected Time: ${mTime2}", fontSize = 30.sp)


        Box(modifier = Modifier.fillMaxSize())
        {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center), verticalArrangement = Arrangement.Center
            ) {

                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        title = it.trim()
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .height(65.dp)
                        .height(70.dp),
                    label = { Text(text = "title") },
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = {
                        description = it.trim()
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .height(65.dp)
                        .height(70.dp),
                    label = { Text(text = "description") },
                )

                Button(
                    onClick = {
                        onEventDispatcher.invoke(
                            AddContract.Intent.SaveData(title, description, mTime.toLong())
                        )
                        WorkManager.getInstance(mContext)
                            .enqueue(selectTime)
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                ) {
                    Text(text = "Add", fontSize = 22.sp)
                }
            }
        }
    }
}