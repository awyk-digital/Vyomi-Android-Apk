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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vyomi.club.ui.components.VyomiTopAppBar
import com.vyomi.club.ui.theme.ActionIvory
import com.vyomi.club.ui.theme.ActionIvoryText
import com.vyomi.club.ui.theme.ChampagneAccent
import com.vyomi.club.ui.theme.DarkBackground
import com.vyomi.club.ui.theme.Dimens
import com.vyomi.club.ui.theme.SurfaceBorder
import com.vyomi.club.ui.theme.SurfaceCard
import com.vyomi.club.ui.theme.SurfaceElevated
import com.vyomi.club.ui.theme.TextMuted
import com.vyomi.club.ui.theme.TextPrimary
import com.vyomi.club.ui.theme.TextSecondary

data class QueueItem(
    val clientId: String,
    val name: String,
    val phone: String,
    val membershipTier: String,
    val registeredTime: String
)

@Composable
fun AssignmentQueueScreen(
    onAssignTrainerClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    var selectedTab by remember { mutableStateOf("Unassigned") }

    val unassignedList = listOf(
        QueueItem("VY-2026-089", "Dev Sharma", "+91 98765 •••••", "Quarterly Gold", "Today, 10:14 AM"),
        QueueItem("VY-2026-090", "Rohit Joshi", "+91 98112 •••••", "Annual Platinum", "Today, 09:20 AM"),
        QueueItem("VY-2026-087", "Meera Sen", "+91 99204 •••••", "Monthly Core", "Yesterday, 06:15 PM"),
        QueueItem("VY-2026-084", "Gaurav Khanna", "+91 98330 •••••", "Quarterly Gold", "Yesterday, 04:30 PM"),
        QueueItem("VY-2026-083", "Simran Bajaj", "+91 98211 •••••", "Half-Yearly Elite", "06 Nov, 11:00 AM")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        VyomiTopAppBar(
            title = "Assignment Queue",
            subtitle = "5 Pending",
            onBackClick = onBackClick
        )

        // Segment Tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.SpacingStandard, vertical = Dimens.SpacingSmall),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Unassigned (5)", "Assigned (42)").forEach { tab ->
                val tabKey = tab.substringBefore(" ")
                val isSelected = selectedTab == tabKey
                Box(
                    modifier = Modifier
                        .background(
                            if (isSelected) ActionIvory else SurfaceElevated,
                            RoundedCornerShape(Dimens.CornerRadiusFull)
                        )
                        .border(
                            Dimens.BorderWidthStandard,
                            if (isSelected) ActionIvory else SurfaceBorder,
                            RoundedCornerShape(Dimens.CornerRadiusFull)
                        )
                        .clickable { selectedTab = tabKey }
                        .padding(horizontal = 14.dp, vertical = 7.dp)
                ) {
                    Text(
                        text = tab,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) ActionIvoryText else TextSecondary
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.SpacingStandard, vertical = Dimens.SpacingSmall),
            verticalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall)
        ) {
            items(unassignedList) { item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(Dimens.CornerRadiusMedium))
                        .background(SurfaceElevated)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusMedium))
                        .padding(Dimens.SpacingMedium)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column {
                            Text(
                                text = item.name,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary
                            )
                            Text(
                                text = "${item.phone} • ${item.membershipTier}",
                                fontSize = 12.sp,
                                color = TextSecondary,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                            Text(
                                text = "Registered: ${item.registeredTime}",
                                fontSize = 11.sp,
                                color = TextMuted,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .background(SurfaceCard, RoundedCornerShape(Dimens.CornerRadiusSmall))
                                .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusSmall))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text("⏳ Unassigned", fontSize = 10.sp, color = ChampagneAccent)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Box(
                            modifier = Modifier
                                .background(ActionIvory, RoundedCornerShape(Dimens.CornerRadiusSmall))
                                .clickable { onAssignTrainerClick(item.clientId) }
                                .padding(horizontal = 14.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "Assign Trainer ➔",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = ActionIvoryText
                            )
                        }
                    }
                }
            }
        }
    }
}

