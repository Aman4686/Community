package com.example.community.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.community.navigation.LocalDrawerState
import com.example.community.navigation.LocalNavBackStack
import com.example.community.navigation.Screen
import community.shared.generated.resources.Res
import community.shared.generated.resources.ic_arrow_back
import community.shared.generated.resources.ic_calendar
import community.shared.generated.resources.ic_email
import community.shared.generated.resources.ic_logout
import community.shared.generated.resources.ic_phone
import org.jetbrains.compose.resources.painterResource
import androidx.compose.ui.tooling.preview.Preview

private val BgColor = Color(0xFF060D1A)
private val CardColor = Color(0xFF0D1726)
private val AccentBlue = Color(0xFF3B7EC0)
private val TextPrimary = Color(0xFFEEF1F6)
private val TextSecondary = Color(0xFFBCC5D3)
private val TextMuted = Color(0xFF8A93A4)
private val GreenDot = Color(0xFF4CAF50)
private val LogoutBg = Color(0xFF6B1A2E)

@Composable
fun ProfileScreen() {
    val backStack = LocalNavBackStack.current

    Scaffold(
        containerColor = BgColor,
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = { CommunityTopBar(title = "Profile") }
    ) { innerPadding ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgColor)
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
    ) {
        // Back button
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(CardColor)
                    .clickable { backStack.removeLastOrNull() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_arrow_back),
                    contentDescription = "Back",
                    tint = TextPrimary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        // Profile card
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(CardColor)
                .padding(vertical = 24.dp, horizontal = 20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Avatar with online dot
                Box(contentAlignment = Alignment.BottomEnd) {
                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape)
                            .border(1.5.dp, Color.White.copy(alpha = 0.2f), CircleShape)
                            .background(Color(0xFF1A2540)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "SH",
                            color = TextPrimary,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Light
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape)
                            .border(2.dp, CardColor, CircleShape)
                            .background(GreenDot)
                    )
                }

                Spacer(Modifier.height(14.dp))

                Text(
                    text = "SERHII HRY",
                    color = TextPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = "EDIT PROFILE",
                    color = AccentBlue,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.8.sp,
                    modifier = Modifier.clickable {}
                )

                Spacer(Modifier.height(20.dp))

                // Logout button
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(LogoutBg)
                        .clickable {}
                        .padding(horizontal = 24.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_logout),
                        contentDescription = null,
                        tint = TextPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Logout",
                        color = TextPrimary,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        // Contact information section
        ProfileSection(title = "CONTACT INFORMATION") {
            InfoRow(
                iconRes = Res.drawable.ic_email,
                label = "EMAIL",
                value = "amannarkoman@gmail.com"
            )
            Spacer(Modifier.height(10.dp))
            InfoRow(
                iconRes = Res.drawable.ic_phone,
                label = "PHONE",
                value = "Not provided",
                valueMuted = true
            )
        }

        Spacer(Modifier.height(16.dp))

        // Important dates section
        ProfileSection(title = "IMPORTANT DATES") {
            InfoRow(
                iconRes = Res.drawable.ic_calendar,
                label = "DATE OF BIRTH",
                value = "Not set",
                valueMuted = true
            )
        }

        Spacer(Modifier.height(32.dp))
    }
    } // Scaffold
}

@Composable
private fun ProfileSection(title: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        // Section header with blue left underline
        Row(verticalAlignment = Alignment.Bottom) {
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(16.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(AccentBlue)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = title,
                color = TextPrimary,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.2.sp
            )
        }

        Spacer(Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(CardColor)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            content()
        }
    }
}

@Composable
private fun InfoRow(
    iconRes: org.jetbrains.compose.resources.DrawableResource,
    label: String,
    value: String,
    valueMuted: Boolean = false,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = TextSecondary,
            modifier = Modifier.size(20.dp)
        )
        Column {
            Text(
                text = label,
                color = TextSecondary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.8.sp
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = value,
                color = if (valueMuted) TextMuted else TextPrimary,
                fontSize = 15.sp
            )
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    val fakeBackStack = remember { NavBackStack<NavKey>(Screen.Profile) }
    val fakeDrawerState = rememberDrawerState(DrawerValue.Closed)
    CompositionLocalProvider(
        LocalNavBackStack provides fakeBackStack,
        LocalDrawerState provides fakeDrawerState,
    ) {
        MaterialTheme {
            ProfileScreen()
        }
    }
}
