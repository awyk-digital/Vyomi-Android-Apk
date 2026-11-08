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
fun OwnerOverviewScreen(
    onNavigateToEarnings: () -> Unit,
    onNavigateToSixMonthReview: () -> Unit,
    onNavigateToStaff: () -> Unit,
    onNavigateToMemberships: () -> Unit,
    onNavigateToAudit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
    ) {
        VyomiTopAppBar(
            title = "Executive Overview",
            subtitle = "Rohan Mehta (Owner)",
            trailingContent = {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(SurfaceElevated, CircleShape)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("👑", fontSize = 14.sp)
                }
            }
        )

        Column(modifier = Modifier.padding(Dimens.SpacingStandard)) {
            // Period Selector
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(Dimens.CornerRadiusMedium))
                    .background(SurfaceElevated)
                    .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusMedium))
                    .padding(horizontal = Dimens.SpacingMedium, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Reporting Cycle:", fontSize = 12.sp, color = TextSecondary)
                Text("📅 November 2026 (Month 4 of 6) ▾", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Financial Metrics Grid (Consistent numbers)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall)
            ) {
                VyomiStatCard(value = "₹4,82,000", label = "Collections (MTD)", modifier = Modifier.weight(1f))
                VyomiStatCard(value = "₹2,41,000", label = "Accrued Trainer Share", highlight = true, modifier = Modifier.weight(1f), onClick = onNavigateToEarnings)
            }

            Spacer(modifier = Modifier.height(Dimens.SpacingSmall))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall)
            ) {
                VyomiStatCard(value = "182", label = "Active Members", modifier = Modifier.weight(1f))
                VyomiStatCard(value = "840", label = "Verified Sessions", modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Restrained Minimalist Trajectory
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(Dimens.CornerRadiusLarge))
                    .background(SurfaceElevated)
                    .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusLarge))
                    .padding(Dimens.SpacingStandard)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("WEEKLY REVENUE TRAJECTORY", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = TextSecondary)
                    Text("Avg ₹1,20,500 / wk", fontSize = 11.sp, color = ChampagneAccent)
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    val bars = listOf(Pair("W1", 55), Pair("W2", 70), Pair("W3", 85), Pair("W4", 75))
                    bars.forEach { (label, heightPct) ->
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height((heightPct * 0.7).dp)
                                    .background(
                                        if (label == "W3") ChampagneAccent else SurfaceCard,
                                        RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)
                                    )
                            )
                            Text(label, fontSize = 10.sp, color = TextMuted, modifier = Modifier.padding(top = 4.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Quick Operational Links
            Text(
                text = "MANAGEMENT MODULES",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextSecondary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            listOf(
                Triple("📊 Trainer Revenue Share & Earnings", "Review eligible collections and baseline shares", onNavigateToEarnings),
                Triple("⏳ Six-Month Incentive Review", "Manual review for percentage adjustment", onNavigateToSixMonthReview),
                Triple("👥 Staff & Access Governance", "Account roles and permission control", onNavigateToStaff),
                Triple("🏷️ Membership Catalogue", "Price configuration and tier durations", onNavigateToMemberships),
                Triple("📜 System Audit Activity", "Immutable event and transaction logs", onNavigateToAudit)
            ).forEach { (title, subtitle, onClick) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = Dimens.SpacingSmall)
                        .clip(RoundedCornerShape(Dimens.CornerRadiusMedium))
                        .background(SurfaceElevated)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusMedium))
                        .clickable { onClick() }
                        .padding(Dimens.SpacingMedium),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(title, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                        Text(subtitle, fontSize = 11.sp, color = TextSecondary, modifier = Modifier.padding(top = 1.dp))
                    }
                    Text("➔", fontSize = 14.sp, color = TextMuted)
                }
            }
        }
    }
}

