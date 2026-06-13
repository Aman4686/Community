package com.example.community.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import community.shared.generated.resources.Res
import community.shared.generated.resources.ic_chevron_down
import community.shared.generated.resources.ic_chevron_up
import community.shared.generated.resources.ic_lock
import community.shared.generated.resources.ic_megaphone
import community.shared.generated.resources.ic_person
import com.example.community.navigation.LocalDrawerState
import com.example.community.navigation.LocalNavBackStack
import com.example.community.navigation.Screen
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
fun CommunityDrawerSheet() {
    val backStack = LocalNavBackStack.current
    val drawerState = LocalDrawerState.current
    val scope = rememberCoroutineScope()
    var adminExpanded by remember { mutableStateOf(true) }

    ModalDrawerSheet(
        modifier = Modifier.width(288.dp),
        drawerContainerColor = Color.Transparent,
        windowInsets = WindowInsets(0),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    // Dark glass base — ~85% opaque dark navy
                    drawRect(Color(0xD9080F1C))

                    // Radial blue glow from upper-center (inner light source)
                    drawRect(
                        brush = Brush.radialGradient(
                            colors = listOf(Color(0x2A3B7EC0), Color.Transparent),
                            center = Offset(size.width * 0.5f, size.height * 0.18f),
                        )
                    )
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 18.dp)
                    .padding(top = 30.dp, bottom = 24.dp)
            ) {
                // Header
                Text(
                    text = "Community name",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFFEEF1F6),
                    letterSpacing = 0.3.sp,
                    modifier = Modifier.padding(start = 6.dp, bottom = 30.dp)
                )

                // Announcements — active item
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .clip(RoundedCornerShape(13.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(Color.White.copy(alpha = 0.08f), Color.White.copy(alpha = 0.03f))
                            )
                        )
                        .border(1.dp, Color.White.copy(alpha = 0.07f), RoundedCornerShape(13.dp))
                ) {
                    // Left accent bar
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .width(3.dp)
                            .fillMaxHeight()
                            .padding(vertical = 11.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(Color(0xFFEEF1F6))
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 13.dp)
                            .padding(start = 16.dp, end = 13.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(13.dp)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_megaphone),
                            contentDescription = null,
                            tint = Color(0xFFE7ECF4),
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Announcements",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFEEF1F6)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Administrator — collapsible section
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { adminExpanded = !adminExpanded }
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(13.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_lock),
                        contentDescription = null,
                        tint = Color(0xFFBCC5D3),
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Administrator",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFBCC5D3),
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        painter = painterResource(if (adminExpanded) Res.drawable.ic_chevron_up else Res.drawable.ic_chevron_down),
                        contentDescription = null,
                        tint = Color(0xFF7E8A9C),
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Communities — sub-item with connector line
                AnimatedVisibility(visible = adminExpanded) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .offset(y = (-2).dp)
                                .width(1.dp)
                                .height(50.dp)
                                .background(Color.White.copy(alpha = 0.1f))
                        )
                        Row(
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_person),
                                contentDescription = null,
                                tint = Color(0xFFAAB3C1),
                                modifier = Modifier.size(19.dp)
                            )
                            Text(
                                text = "Communities",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFFAAB3C1)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // User footer
                Row(
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .alpha(0.55f)
                        .clickable { scope.launch { drawerState.close() }; backStack.add(Screen.Profile) },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.White.copy(alpha = 0.2f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "SH",
                            color = Color(0xFFCFD8E6),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Text(
                        text = "Serhii Hry",
                        color = Color(0xFF8A93A4),
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
