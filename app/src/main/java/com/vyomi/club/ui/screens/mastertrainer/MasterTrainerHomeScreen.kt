package com.vyomi.club.ui.screens.mastertrainer

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vyomi.club.ui.components.VyomiStatCard
import com.vyomi.club.ui.components.VyomiTopAppBar
import com.vyomi.club.ui.theme.ActionIvory
import com.vyomi.club.ui.theme.ActionIvoryText
import com.vyomi.club.ui.theme.ChampagneAccent
import com.vyomi.club.ui.theme.DarkBackground
import com.vyomi.club.ui.theme.Dimens
import com.vyomi.club.ui.theme.SurfaceBorder
import com.vyomi.club.ui.theme.SurfaceElevated
import com.vyomi.club.ui.theme.TextMuted
import com.vyomi.club.ui.theme.TextPrimary
import com.vyomi.club.ui.theme.TextSecondary

@Composable
fun MasterTrainerHomeScreen(
    onNavigateToQueue: () -> Unit,
    onNavigateToTrainers: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
    ) {
        VyomiTopAppBar(
            title = "Master Trainer",
            subtitle = "Facility Operations",
            trailingContent = {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(SurfaceElevated, CircleShape)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("MT", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent)
                }
            }
        )

        Column(modifier = Modifier.padding(Dimens.SpacingStandard)) {
            Text(
                text = "Welcome, Arjun Verma",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Text(
                text = "Manage trainer allocations and monitor verified attendance.",
                fontSize = 13.sp,
                color = TextSecondary,
                modifier = Modifier.padding(top = 2.dp, bottom = 16.dp)
            )

            // Primary Assignment Action
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(Dimens.CornerRadiusLarge))
                    .background(ActionIvory)
                    .clickable { onNavigateToQueue() }
                    .padding(horizontal = Dimens.SpacingStandard, vertical = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(DarkBackground, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("🎯", fontSize = 18.sp)
                    }
                    Column(modifier = Modifier.padding(start = Dimens.SpacingMedium)) {
                        Text(
                            text = "Assign Unallocated Clients",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = ActionIvoryText
                        )
                        Text(
                            text = "5 clients waiting in assignment queue",
                            fontSize = 12.sp,
                            color = ActionIvoryText.copy(alpha = 0.7f)
                        )
                    }
                }
                Text("➔", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ActionIvoryText)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "FACILITY ROSTER STATUS",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextSecondary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall)
            ) {
                VyomiStatCard(value = "5", label = "Unassigned", highlight = true, modifier = Modifier.weight(1f), onClick = onNavigateToQueue)
                VyomiStatCard(value = "12/14", label = "Active Trainers", modifier = Modifier.weight(1f), onClick = onNavigateToTrainers)
                VyomiStatCard(value = "34", label = "Sessions Today", modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "RECENT CLIENT ALLOCATIONS",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextSecondary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            listOf(
                Pair("Karan Singhania (Quarterly Gold)", "Assigned to Kabir Roy • 10:15 AM"),
                Pair("Pooja Hegde (Half-Yearly Elite)", "Assigned to Vikram Malhotra • Yesterday"),
                Pair("Aditi Rao (Annual Platinum)", "Assigned to Sneha Rao • Yesterday")
            ).forEach { (client, details) ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = Dimens.SpacingSmall)
                        .clip(RoundedCornerShape(Dimens.CornerRadiusMedium))
                        .background(SurfaceElevated)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusMedium))
                        .padding(Dimens.SpacingMedium)
                ) {
                    Text(text = client, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                    Text(text = details, fontSize = 11.sp, color = TextMuted, modifier = Modifier.padding(top = 2.dp))
                }
            }
        }
    }
}

