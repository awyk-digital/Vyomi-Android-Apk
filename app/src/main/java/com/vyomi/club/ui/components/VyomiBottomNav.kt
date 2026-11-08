package com.vyomi.club.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.EventNote
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vyomi.club.data.model.UserRole
import com.vyomi.club.ui.theme.ChampagneAccent
import com.vyomi.club.ui.theme.Dimens
import com.vyomi.club.ui.theme.SurfaceBorder
import com.vyomi.club.ui.theme.SurfaceElevated
import com.vyomi.club.ui.theme.TextMuted
import com.vyomi.club.ui.theme.TextPrimary

data class NavTabItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

@Composable
fun VyomiBottomNavigationBar(
    role: UserRole,
    currentRoute: String,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabs = when (role) {
        UserRole.FRONT_DESK -> listOf(
            NavTabItem("fd_home", "Home", Icons.Default.Home),
            NavTabItem("client_directory", "Clients", Icons.Default.People),
            NavTabItem("payments_list", "Payments", Icons.Default.CreditCard),
            NavTabItem("account_more", "More", Icons.Default.MoreHoriz)
        )
        UserRole.MASTER_TRAINER -> listOf(
            NavTabItem("mt_home", "Home", Icons.Default.Home),
            NavTabItem("assignment_queue", "Assignments", Icons.Default.Assignment),
            NavTabItem("trainer_directory", "Trainers", Icons.Default.FitnessCenter),
            NavTabItem("account_more", "More", Icons.Default.MoreHoriz)
        )
        UserRole.TRAINER -> listOf(
            NavTabItem("tr_home", "Home", Icons.Default.Home),
            NavTabItem("my_clients", "My Clients", Icons.Default.People),
            NavTabItem("session_history", "Sessions", Icons.Default.EventNote),
            NavTabItem("account_more", "More", Icons.Default.MoreHoriz)
        )
        UserRole.OWNER_ADMIN -> listOf(
            NavTabItem("owner_overview", "Overview", Icons.Default.QueryStats),
            NavTabItem("client_directory", "Clients", Icons.Default.People),
            NavTabItem("trainer_earnings", "Finance", Icons.Default.CreditCard),
            NavTabItem("account_more", "More", Icons.Default.MoreHoriz)
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(SurfaceElevated)
            .border(Dimens.BorderWidthStandard, SurfaceBorder)
            .navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = Dimens.SpacingSmall),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEach { tab ->
                val isSelected = currentRoute == tab.route

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(Dimens.MinTouchTargetSize)
                        .clickable { onTabSelected(tab.route) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.label,
                        modifier = Modifier.size(20.dp),
                        tint = if (isSelected) TextPrimary else TextMuted
                    )
                    Text(
                        text = tab.label,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                        color = if (isSelected) TextPrimary else TextMuted,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                    if (isSelected) {
                        Box(
                            modifier = Modifier
                                .padding(top = 2.dp)
                                .size(4.dp)
                                .background(ChampagneAccent, CircleShape)
                        )
                    } else {
                        Box(modifier = Modifier.size(4.dp))
                    }
                }
            }
        }
    }
}

