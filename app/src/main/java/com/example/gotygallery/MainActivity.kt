package com.example.gotygallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gotygallery.ui.theme.GOTYGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GOTYGalleryTheme {
                GOTYGalleryApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GOTYGalleryApp() {
    GOTYGallery()
}

@Composable
fun GOTYGallery() {
    var year by remember {
        mutableStateOf("2018")
    }

    val gotyImage = when(year) {
        "2018" -> R.drawable.god_of_war
        "2019" -> R.drawable.sekiro
        "2020" -> R.drawable.the_last_of_us_part2
        "2021" -> R.drawable.it_takes_two
        else -> R.drawable.elden_ring
    }

    val gotyText = when(gotyImage) {
        R.drawable.god_of_war -> R.string.god_of_war
        R.drawable.sekiro -> R.string.sekiro
        R.drawable.the_last_of_us_part2 -> R.string.the_last_of_us
        R.drawable.it_takes_two -> R.string.it_takes_two
        else -> R.string.elden_ring
    }

    val gotyDev = when(year) {
        "2018" -> R.drawable.santa_monica
        "2019" -> R.drawable.from_software
        "2020" -> R.drawable.naughty_dog
        "2021" -> R.drawable.hazelight
        else -> R.drawable.from_software
    }

    Box(
        modifier =  Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.game_awards_statue),
            contentDescription = null,
            modifier = Modifier.fillMaxHeight(),
            alpha = 0.8f,
            contentScale = ContentScale.FillHeight
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.game_awards_image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            GOTYWithImageAndText(gotyImage = gotyImage, gotyText = gotyText)
            Spacer(modifier = Modifier.height(20.dp))
            GOTYDevImage(devImage = gotyDev)
            Spacer(modifier = Modifier.height(40.dp))
           NextGOTY(
               year = year,
               previousOnClick = {
                   year = year.toInt().dec().toString()
               },
               nextOnClick = {
                   year = year.toInt().inc().toString()
               },
           )
        }
    }
}

@Composable
fun GOTYWithImageAndText(
    gotyImage: Int,
   @StringRes gotyText: Int
) {
    Column() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .size(305.dp, 150.dp),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(2.dp, Color.White)
        ) {
            Image(
                painter = painterResource(id = gotyImage),
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 3.dp),
                contentScale = ContentScale.FillWidth
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xff25318F),
                            Color(0xffE8341D)
                        )
                    ),
                    shape = RoundedCornerShape(10.dp)
                )
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = gotyText).uppercase(),
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun GOTYDevImage(
    devImage: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0x00ffffff),
                        Color.Black
                    )
                ),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            modifier = Modifier.padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = devImage),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Composable
fun NextGOTY(
    year: String,
    previousOnClick:() -> Unit,
    nextOnClick:() -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        Button(
            onClick = previousOnClick,
            enabled = (year != "2018"),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(
                text = "Previous",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }
        Text(
            text = year,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xffB1323C),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Button(
            onClick = nextOnClick,
            enabled = (year != "2022"),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Next",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }
    }
}
