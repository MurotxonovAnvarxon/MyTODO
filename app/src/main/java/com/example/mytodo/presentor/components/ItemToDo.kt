package com.example.mytodo.presentor.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mytodo.data.model.ToDoData


@Composable
fun Items(toDoData: ToDoData) {

    Card(
        modifier = Modifier
            .padding(20.dp)
            .height(150.dp)
            .fillMaxWidth()
    ) {
        Text(text = toDoData.title, modifier = Modifier.padding(start = 10.dp))
        Text(text = toDoData.description, modifier = Modifier.padding(start = 10.dp))
        Text(text = toDoData.time.toString(), modifier = Modifier.padding(start = 10.dp))
    }


}
@Composable
@Preview(showBackground = true)
fun ItemPreview() {
    Items(ToDoData(1,"","",1L))
}