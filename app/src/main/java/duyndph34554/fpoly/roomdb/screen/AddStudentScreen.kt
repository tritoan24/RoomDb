package duyndph34554.fpoly.roomdb.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.room.Room
import duyndph34554.fpoly.roomdb.database.StudentDatabase
import duyndph34554.fpoly.roomdb.model.StudentModel
import kotlinx.coroutines.launch


@Composable
fun AddStudentScreen(navController: NavHostController) {
    var hoTen by remember {
        mutableStateOf("")
    }

    var mssv by remember {
        mutableStateOf("")
    }

    var daratruong by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val db = Room.databaseBuilder(
        context = context,
        StudentDatabase::class.java, "students-db2"
    ).build()
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add Student",
            color = Color.Green,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Normal,
            fontSize = 24.sp,
            modifier = Modifier.padding(horizontal = 0.dp, vertical = 16.dp),
            fontWeight = FontWeight.Bold
        )

        Column {
            Text(text = "Moi nhap ho ten:")

            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(value = hoTen,
                onValueChange = {hoTen = it},
                label = { Text(text = "Moi nhap ho ten")}
            )
        }


        Spacer(modifier = Modifier.height(10.dp))

        Column {

            Text(text = "Moi nhap mssv:")

            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(value = mssv,
                onValueChange = {mssv = it},
                label = { Text(text = "Moi nhap mssv")}
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Column {
            Text(text = "Trang thai ra truong: ")

            Spacer(modifier = Modifier.height(4.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = daratruong,
                        onClick = { daratruong = true }
                    )
                    Text(text = "Da ra truong")
                }

                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = !daratruong,
                        onClick = { daratruong = false }
                    )
                    Text(text = "Chua ra truong")
                }

            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
                         coroutineScope.launch {
                             db.studentDao().insertStudent(
                                 StudentModel(
                                     hoten = hoTen,
                                     mssv = mssv,
                                     daratruong = daratruong
                                 )
                             )
                             navController.navigate("home")
                         }
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.White
            ),
            modifier = Modifier.width(150.dp)
        ) {
            Text(text = "Add")
        }

    }

}