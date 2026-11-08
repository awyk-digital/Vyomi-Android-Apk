package com.vyomi.club.ui.screens.owner

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.vyomi.club.ui.theme.StatusSuccess
import com.vyomi.club.ui.theme.SurfaceBorder
import com.vyomi.club.ui.theme.SurfaceCard
import com.vyomi.club.ui.theme.SurfaceElevated
import com.vyomi.club.ui.theme.TextMuted
import com.vyomi.club.ui.theme.TextPrimary
import com.vyomi.club.ui.theme.TextSecondary

data class MembershipTierConfig(
    val name: String,
    val duration: String,
    val standardPrice: Double,
    val status: String = "Active"
)

@Composable
fun MembershipConfigScreen(
    onBackClick: () -> Unit
) {
    val catalogue = listOf(
        MembershipTierConfig("Quarterly Gold", "3 Months", 18000.0),
        MembershipTierConfig("Annual Platinum VIP", "12 Months", 60000.0),
        MembershipTierConfig("Monthly Core", "1 Month", 7500.0),
        MembershipTierConfig("Half-Yearly Elite", "6 Months", 32000.0)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        VyomiTopAppBar(
            title = "Membership Catalogue",
            subtitle = "Facility Tiers",
            onBackClick = onBackClick
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.SpacingStandard, vertical = Dimens.SpacingSmall),
            verticalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall)
        ) {
            items(catalogue) { item ->
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
                    Column {
                        Text(item.name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                        Text("${item.duration} Duration • Standard Base", fontSize = 11.sp, color = TextSecondary, modifier = Modifier.padding(top = 1.dp))
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("₹${item.standardPrice.toInt()} INR", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent)
                        Text("● ${item.status}", fontSize = 10.sp, color = StatusSuccess, modifier = Modifier.padding(top = 1.dp))
                    }
                }
            }
        }
    }
}

