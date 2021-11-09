package com.jetpack.facebookintegration

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.facebook.Profile

@Composable
fun CustomLoginButton(profile: Profile?, login: () -> Unit, logout: () -> Unit) {
    val buttonText = if (profile == null) {
        "Continue with Facebook"
    } else {
        "Log out"
    }
    val onClick = if (profile == null) {
        login
    } else {
        logout
    }
    Button(
        onClick = {
            onClick
        }
    ) {
        Text(
            text = buttonText
        )
    }
}