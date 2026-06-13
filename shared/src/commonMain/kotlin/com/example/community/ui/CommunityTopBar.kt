package com.example.community.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.community.navigation.LocalDrawerState
import community.shared.generated.resources.Res
import community.shared.generated.resources.ic_logout
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

internal val TopBarBg = Color(0xFF060D1A)
internal val TopBarTextPrimary = Color(0xFFEEF1F6)
internal val TopBarTextSecondary = Color(0xFFBCC5D3)

@Composable
fun CommunityTopBar(
    title: String,
    onLogoutClick: () -> Unit = {},
) {
    val drawerState = LocalDrawerState.current
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { scope.launch { drawerState.open() } }) {
            Text("☰", color = TopBarTextPrimary, fontSize = 20.sp)
        }
        Text(
            text = title,
            color = TopBarTextPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        IconButton(onClick = onLogoutClick) {
            Icon(
                painter = painterResource(Res.drawable.ic_logout),
                contentDescription = "Logout",
                tint = TopBarTextSecondary,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}
