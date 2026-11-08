package com.vyomi.club.ui.screens.frontdesk

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.vyomi.club.data.model.VyomiStatusType
import com.vyomi.club.ui.components.VyomiPrimaryButton
import com.vyomi.club.ui.components.VyomiSecondaryButton
import com.vyomi.club.ui.components.VyomiStatusChip
import com.vyomi.club.ui.components.VyomiTopAppBar
import com.vyomi.club.ui.theme.ChampagneAccent
import com.vyomi.club.ui.theme.DarkBackground
import com.vyomi.club.ui.theme.Dimens
import com.vyomi.club.ui.theme.SurfaceBorder
import com.vyomi.club.ui.theme.SurfaceCard
import com.vyomi.club.ui.theme.SurfaceElevated
import com.vyomi.club.ui.theme.TextMuted
import com.vyomi.club.ui.theme.TextPrimary
import com.vyomi.club.ui.theme.TextSecondary

@Composable
fun ClientDetailScreen(
    clientId: String,
    onNavigateToRecordPayment: (String) -> Unit,
    onBackClick: () -> Unit
) {
    var selectedTab by remember { mutableStateOf("Overview") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
    ) {
        VyomiTopAppBar(
            title = "Client Profile",
            subtitle = "#$clientId",
            onBackClick = onBackClick
        )

        Column(modifier = Modifier.padding(Dimens.SpacingStandard)) {
            // Header Profile Card
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(Dimens.CornerRadiusLarge))
                    .background(SurfaceElevated)
                    .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusLarge))
                    .padding(Dimens.SpacingStandard),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(SurfaceCard, CircleShape)
                        .border(1.5.dp, ChampagneAccent, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("DS", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent)
                }

                Column(modifier = Modifier.padding(start = Dimens.SpacingMedium)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Dev Sharma",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Box(modifier = Modifier.padding(start = 8.dp)) {
                            VyomiStatusChip(status = VyomiStatusType.ACTIVE)
                        }
                    }
                    Text(
                        text = "+91 98765 ••••• (Phone Verified ✓)",
                        fontSize = 12.sp,
                        color = TextSecondary,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                    Text(
                        text = "Quarterly Gold • Valid till 07 Feb 2027",
                        fontSize = 11.sp,
                        color = TextMuted,
                        modifier = Modifier.padding(top = 1.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(Dimens.SpacingMedium))

            // Sub-tabs
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("Overview", "Sessions (14)", "Payments (1)").forEach { tab ->
                    val isSelected = selectedTab == tab
                    Box(
                        modifier = Modifier
                            .background(
                                if (isSelected) SurfaceCard else DarkBackground,
                                RoundedCornerShape(Dimens.CornerRadiusSmall)
                            )
                            .border(
                                Dimens.BorderWidthStandard,
                                if (isSelected) ChampagneAccent else SurfaceBorder,
                                RoundedCornerShape(Dimens.CornerRadiusSmall)
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = tab,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) TextPrimary else TextSecondary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(Dimens.SpacingMedium))

            // Detail Specifications
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(Dimens.CornerRadiusMedium))
                    .background(SurfaceElevated)
                    .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusMedium))
                    .padding(Dimens.SpacingStandard),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                DetailRow("Assigned Trainer:", "Kabir Roy (Strength PT)", isHighlight = true)
                DetailRow("Agreed Contract Price:", "₹18,000 INR")
                DetailRow("Enrolled On:", "08 Nov 2026, 10:14 AM")
                DetailRow("Registered By Staff:", "Priya Patel (Front Desk)")
                DetailRow("Phone Masking:", "+91 98765 ••••• (Privacy Protected)")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Actions
            VyomiPrimaryButton(
                text = "💳 Record Payment for Client",
                onClick = { onNavigateToRecordPayment(clientId) }
            )

            Spacer(modifier = Modifier.height(Dimens.SpacingSmall))

            VyomiSecondaryButton(
                text = "🔄 Renew / Extend Membership (Proposed)",
                onClick = { /* Open proposed renewal dialog */ }
            )
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String, isHighlight: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 12.sp, color = TextSecondary)
        Text(
            text = value,
            fontSize = 13.sp,
            fontWeight = if (isHighlight) FontWeight.Bold else FontWeight.Medium,
            color = if (isHighlight) ChampagneAccent else TextPrimary
        )
    }
}

