package com.jawadhameed.profilecard

import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.jawadhameed.profilecard.R
import com.jawadhameed.profilecard.ui.theme.poppins

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var isFlipped by remember { mutableStateOf(false) }
    val rotation = remember { Animatable(0f) }


    LaunchedEffect(isFlipped) {
        rotation.animateTo(
            targetValue = if (isFlipped) 180f else 0f,
            animationSpec = tween(durationMillis = 600)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.purple_200)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(420.dp)
                .graphicsLayer {
                    rotationY = rotation.value
                    cameraDistance = 16 * density
                }
                .clickable { isFlipped = !isFlipped },
            elevation = CardDefaults.cardElevation(16.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            if (rotation.value <= 90f) {
                FrontSide()
            } else {
                Box(
                    Modifier
                        .fillMaxSize()
                        .graphicsLayer { rotationY = 180f },
                    contentAlignment = Alignment.Center
                ) {
                    BackSide()
                }
            }
        }
    }
}

@Composable
fun FrontSide() {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(2.dp, color = colorResource(R.color.red), CircleShape),
            painter = painterResource(R.drawable.profile),
            contentDescription = null
        )
        Text(
            text = "Jawad Hameed",
            color = Color.Black,
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = poppins
        )
        Text(
            text = stringResource(R.string.about),
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontFamily = poppins
        )
        ElevatedButton(
            onClick = {
                openUrl("https://wa.me/923077571405", context = context)
            },
            modifier = Modifier.padding(top = 16.dp),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = colorResource(R.color.green)
            )
        ) {
            Text(
                text = "Contact on WhatsApp",
                color = Color.White,
                fontSize = 14.sp,
                fontFamily = poppins
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun BackSide() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            modifier = Modifier
                .size(150.dp),
            painter = painterResource(R.drawable.qr_code),
            contentDescription = null
        )
        Text(
            text = "Email: jawadhameed454@gmail.com\nPhone: +92 307 7571405",
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 20.dp),
            fontFamily = poppins
        )
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilledIconButton(
                modifier = Modifier
                    .padding(5.dp)
                    .size(30.dp),
                onClick = {
                    openUrl(
                        url = "https://www.linkedin.com/in/jawad-hameed",
                        context = context
                    )
                },
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = colorResource(R.color.linkedin)
                )
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.linkedin),
                    contentDescription = null,
                    tint = Color.White
                )
            }

            FilledIconButton(
                modifier = Modifier
                    .padding(5.dp)
                    .size(30.dp),
                onClick = {
                    openUrl(
                        url = "https://www.facebook.com/jawad143sahil",
                        context = context
                    )
                },
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = colorResource(R.color.facebook)
                )
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(R.drawable.facebook),
                    contentDescription = null,
                    tint = Color.White
                )
            }

            FilledIconButton(
                modifier = Modifier
                    .padding(5.dp)
                    .size(30.dp),
                onClick = {
                    openUrl(
                        url = "https://www.instagram.com/jawadhameed143",
                        context = context
                    )
                },
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = colorResource(R.color.instagram)
                )
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(R.drawable.instagram),
                    contentDescription = null,
                    tint = Color.White
                )
            }

        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

private fun openUrl(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
    context.startActivity(intent)
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
