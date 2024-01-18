package com.example.artspace

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

data class ArtInfo(val name: String, val author: String, val year: Int, val image: Int)

@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {
    var currentArt by remember { mutableStateOf(0) }
    var artList = listOf(
        ArtInfo("VR for art", "Vaishav", 2023, R.drawable.arts_1),
        ArtInfo("Atul in the wild", "Atul Chutiya", 1980, R.drawable.arts_2)
    )

    fun onNextClick() {
        if (currentArt != (artList.size - 1)) {
            currentArt++
        }
    }

    fun onPreviousClick() {
        if (currentArt != 0) {
            currentArt--
        }

    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 4.dp, horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = modifier.height(50.dp))
        ArtPreview(
            artImage = artList[currentArt].image
        )
        Spacer(modifier = modifier.height(50.dp))
        ArtInformation(artList[currentArt])
        ArtButtons(onNextClick = ::onNextClick, onPreviousClick = ::onPreviousClick)
    }
}

@Composable
fun ArtPreview(
    artImage: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(450.dp)
            .shadow(10.dp)
    ) {
        Image(
            painter = painterResource(id = artImage),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = modifier.padding(50.dp)
        )
    }
}

@Composable
fun ArtInformation(
    artInfo: ArtInfo,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .background(color = Color.LightGray)
    ) {
        Column(
            modifier = modifier.padding(20.dp)
        ) {
            Text(
                text = artInfo.name,
                fontSize = 26.sp,
            )
            Row {
                Text(
                    text = artInfo.author,
                    modifier.padding(end = 3.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(text = artInfo.year.toString())
            }
        }
    }
}

@Composable
fun ArtButtons(
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = onPreviousClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            modifier = modifier.padding(horizontal = 10.dp)
        ) {
            Text(text = "Previous")
        }
        Button(
            onClick = onNextClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            modifier = modifier.padding(horizontal = 10.dp)
        ) {
            Text(text = "Next")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}