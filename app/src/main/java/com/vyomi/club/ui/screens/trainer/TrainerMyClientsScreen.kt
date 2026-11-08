package com.vyomi.club.ui.screens.trainer

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import com.vyomi.club.ui.components.VyomiTextField
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

data class AssignedClientItem(
    val id: String,
    val name: String,
    val phone: String,
    val plan: String,
    val lastSession: String
)

@Composable
fun TrainerMyClientsScreen(
    onVerifySessionClick: (String) -> Unit,
    onBackClick: (() -> Unit)? = null
) {
    var searchQuery by remember { mutableStateOf("") }

    val clients = listOf(
        AssignedClientItem("VY-2026-089", "Dev Sharma", "+91 98765 •••••", "Quarterly Gold", "Today - Pending"),
        AssignedClientItem("VY-2026-085", "Karan Singhania", "+91 98331 •••••", "Quarterly Gold", "Yesterday, 6:30 PM"),
        AssignedClientItem("VY-2026-079", "Sahil Kapoor", "+91 98210 •••••", "Annual Platinum", "06 Nov, 7:00 AM"),
        AssignedClientItem("VY-2026-072", "Tarun Varma", "+91 99100 •••••", "Half-Yearly Elite", "05 Nov, 8:15 AM")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        VyomiTopAppBar(
            title = "My Assigned Clients",
            subtitle = "8 Total",
            onBackClick = onBackClick
        )

        Column(modifier = Modifier.padding(horizontal = Dimens.SpacingStandard, vertical = Dimens.SpacingSmall)) {
            VyomiTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = "Search My Clients",
                placeholder = "Filter by name or phone..."
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.SpacingStandard, vertical = Dimens.SpacingSmall),
            verticalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall)
        ) {
            items(clients) { client ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(Dimens.CornerRadiusMedium))
                        .background(SurfaceElevated)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusMedium))
                        .padding(Dimens.SpacingMedium),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(SurfaceCard, CircleShape)
                                .border(Dimens.BorderWidthStandard, SurfaceBorder, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            val initials = client.name.split(" ").mapNotNull { it.firstOrNull()?.toString() }.joinToString("")
                            Text(initials, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent)
                        }

                        Column(modifier = Modifier.padding(start = Dimens.SpacingMedium)) {
                            Text(client.name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                            Text("${client.phone} • ${client.plan}", fontSize = 11.sp, color = TextSecondary)
                            Text("Last Attendance: ${client.lastSession}", fontSize = 10.sp, color = TextMuted, modifier = Modifier.padding(top = 1.dp))
                        }
                    }

                    Box(
                        modifier = Modifier
                            .background(ActionIvory, RoundedCornerShape(Dimens.CornerRadiusSmall))
                            .clickable { onVerifySessionClick(client.id) }
                            .padding(horizontal = 12.dp, vertical = 7.dp)
                    ) {
                        Text("Verify OTP", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ActionIvoryText)
                    }
                }
            }
        }
    }
}

