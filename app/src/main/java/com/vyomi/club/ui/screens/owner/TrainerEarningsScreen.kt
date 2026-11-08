package com.vyomi.club.ui.screens.owner

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vyomi.club.ui.components.VyomiTopAppBar
import com.vyomi.club.ui.theme.ChampagneAccent
import com.vyomi.club.ui.theme.DarkBackground
import com.vyomi.club.ui.theme.Dimens
import com.vyomi.club.ui.theme.SurfaceBorder
import com.vyomi.club.ui.theme.SurfaceElevated
import com.vyomi.club.ui.theme.TextMuted
import com.vyomi.club.ui.theme.TextPrimary
import com.vyomi.club.ui.theme.TextSecondary

data class TrainerEarningsItem(
    val id: String,
    val name: String,
    val eligibleCollections: Double,
    val accruedShare: Double,
    val rate: String,
    val clientCount: Int
)

@Composable
fun TrainerEarningsScreen(
    onTrainerDetailClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val earningsList = listOf(
        TrainerEarningsItem("TR-01", "Kabir Roy", 96000.0, 48000.0, "50% Baseline", 8),
        TrainerEarningsItem("TR-02", "Sneha Rao", 72000.0, 36000.0, "50% Baseline", 6),
        TrainerEarningsItem("TR-03", "Vikram Malhotra", 110000.0, 55000.0, "50% Baseline", 12),
        TrainerEarningsItem("TR-04", "Aditi Nair", 48000.0, 24000.0, "50% Baseline", 5)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        VyomiTopAppBar(
            title = "Trainer Compensation",
            subtitle = "November 2026 Cycle",
            onBackClick = onBackClick
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.SpacingStandard, vertical = Dimens.SpacingSmall),
            verticalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall)
        ) {
            items(earningsList) { item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(Dimens.CornerRadiusMedium))
                        .background(SurfaceElevated)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusMedium))
                        .clickable { onTrainerDetailClick(item.id) }
                        .padding(Dimens.SpacingMedium)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column {
                            Text(item.name, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                            Text("${item.clientCount} Active Clients • Rate: ${item.rate}", fontSize = 11.sp, color = TextSecondary, modifier = Modifier.padding(top = 1.dp))
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("₹${item.accruedShare.toInt()}", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent)
                            Text("Accrued Share", fontSize = 10.sp, color = TextMuted, modifier = Modifier.padding(top = 1.dp))
                        }
                    }

                    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).height(1.dp).background(SurfaceBorder))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Eligible Collections: ₹${item.eligibleCollections.toInt()}", fontSize = 12.sp, color = TextMuted)
                        Text("Payment Breakdown ➔", fontSize = 12.sp, color = ChampagneAccent, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }
    }
}

