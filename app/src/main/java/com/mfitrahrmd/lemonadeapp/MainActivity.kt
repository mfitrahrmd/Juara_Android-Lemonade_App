package com.mfitrahrmd.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mfitrahrmd.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        }
    }
}

@Composable
fun LemonApp() {
    var currentStep by remember {
        mutableStateOf(1)
    }
    var requiredTap = 0

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep) {
            1 -> {
                requiredTap = (2..4).random()
                LemonTextAndImage(
                    text = stringResource(id = R.string.lemon_select),
                    imageId = R.drawable.lemon_tree,
                    contentDescription = null
                ) {
                    currentStep = 2
                }
            }

            2 -> {
                var currentTap by remember {
                    mutableStateOf(1)
                }
                LemonTextAndImage(
                    text = stringResource(id = R.string.lemon_squeeze),
                    imageId = R.drawable.lemon_squeeze,
                    contentDescription = null
                ) {
                    if (currentTap < requiredTap) {
                        currentTap++
                    } else {
                        currentStep = 3
                    }
                }
            }
            
            3 -> {
                LemonTextAndImage(text = stringResource(id = R.string.lemon_drink), imageId = R.drawable.lemon_drink, contentDescription = null) {
                    currentStep = 4
                }
            }

            4 -> {
                LemonTextAndImage(text = stringResource(id = R.string.lemon_restart), imageId = R.drawable.lemon_restart, contentDescription = null) {
                    currentStep = 1
                }
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    text: String,
    imageId: Int,
    contentDescription: String?,
    onImageClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = contentDescription,
            modifier = Modifier
                .wrapContentSize()
                .clickable {
                    onImageClick()
                }
                .clip(RoundedCornerShape(48.dp))
                .background(Color(0xFFBEF8FF))
                .padding(vertical = 48.dp, horizontal = 64.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = text, fontSize = 18.sp)
    }
}

@Preview
@Composable
fun DefaultPreview() {
    LemonadeAppTheme {
        LemonApp()
    }
}