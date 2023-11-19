package com.example.nantokanare

//import androidx.compose.runtime.Composable
//import androidx.compose.ui.tooling.preview.Preview
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.nantokanare.ui.theme.NantokaNareTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val intent = Intent(applicationContext,MusicService::class.java)
        //val StartButton = findViewById(R.id.)
        // val player:MediaPlayer? = MediaPlayer.create(applicationContext,R.raw.fjordnosundakaze)

        // player?.start()
        setContent {
            NantokaNareTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    bottomBar = {
                        BottomAppBar(
                            containerColor = MaterialTheme.colorScheme.outline
                        ) {

                        }
                    },
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {Text("Relax Music")},
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.onPrimary)
                        )
                    }

                ) {
                    it -> "$it"
                    Text(text = "Android")
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                onSTPush()
                            }) {
                            Text(
                                text = "音を流すよ"
                            )
                        }
                        Button(
                            onClick = {
                                    onSPPush()
                                }) {
                            Text(
                                text = "音を止めるよ"
                            )
                        }
                    }
                }
            }
        }
    }

    fun onSTPush(){
        val intent: Intent = Intent(application,MusicService::class.java);
        startService(intent)
    }

    fun onSPPush(){
        val intent: Intent = Intent(application,MusicService::class.java)
        stopService(intent)
    }

    override fun onDestroy() {
        stopService(intent)
        super.onDestroy()
    }
}


