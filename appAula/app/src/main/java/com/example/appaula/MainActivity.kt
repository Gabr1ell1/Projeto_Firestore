package com.example.appaula

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appaula.ui.theme.AppAulaTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppAulaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppAulagPreview()
                }
            }
        }
    }
}

@Composable
fun AppAula() {
    var email by remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxWidth(),
        Arrangement.Center
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            Arrangement.Center,

            ) {
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
            Arrangement.Center
        ) {
            Text(
                "Cadastro",
                fontSize = 30.sp,
                )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
            Arrangement.Center
        ) {

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") }
            )
        }

        Row (
            Modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
            Arrangement.Center
        ) {
            Button(
                onClick = {
                    val db = Firebase.firestore

                    // Create a new user with a first and last name

                    val user = hashMapOf(
                        "email" to email,
                    )

                    // Add a new document with a generated ID
                    db.collection("users")
                        .add(user)
                        .addOnSuccessListener { documentReference ->
                            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                border = BorderStroke(
                    1.dp,
                    Color.Blue
                ) // Aqui você define a cor e espessura da borda
            ) {
                Text(
                    "Cadastrar",
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppAulagPreview() {
    AppAulaTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AppAula()
        }
    }
}