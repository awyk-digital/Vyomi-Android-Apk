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
import com.vyomi.club.ui.theme.SurfaceCard
import com.vyomi.club.ui.theme.SurfaceElevated
import com.vyomi.club.ui.theme.TextMuted
import com.vyomi.club.ui.theme.TextPrimary
import com.vyomi.club.ui.theme.TextSecondary

@Composable
fun TrainerHomeScreen(
    onNavigateToVerifySession: () -> Unit,
    onNavigateToMyClients: () -> Unit,
    onNavigateToEarningsDetail: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
    ) {
        VyomiTopAppBar(
            title = "Trainer Portal",
            subtitle = "Kabir Roy",
            trailingContent = {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(SurfaceElevated, CircleShape)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("KR", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent)
                }
            }
        )

        Column(modifier = Modifier.padding(Dimens.SpacingStandard)) {
            Text(
                text = "Wednesday, 08 Nov 2026",
                fontSize = 12.sp,
                color = TextSecondary
            )
            Text(
                text = "Welcome, Kabir",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                modifier = Modifier.padding(top = 2.dp, bottom = 16.dp)
            )

            // Primary Verification CTA
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(Dimens.CornerRadiusLarge))
                    .background(ActionIvory)
                    .clickable { onNavigateToVerifySession() }
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
                        Text("⚡", fontSize = 18.sp)
                    }
                    Column(modifier = Modifier.padding(start = Dimens.SpacingMedium)) {
                        Text(
                            text = "Verify Client Session",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = ActionIvoryText
                        )
                        Text(
                            text = "OTP attendance lock for assigned clients",
                            fontSize = 12.sp,
                            color = ActionIvoryText.copy(alpha = 0.7f)
                        )
                    }
                }
                Text("➔", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ActionIvoryText)
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Metrics
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall)
            ) {
                VyomiStatCard(value = "3", label = "Verified Today", modifier = Modifier.weight(1f))
                VyomiStatCard(value = "8", label = "Assigned Clients", highlight = true, modifier = Modifier.weight(1f), onClick = onNavigateToMyClients)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Permitted Earnings Shortcut (Authorized)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(Dimens.CornerRadiusMedium))
                    .background(SurfaceElevated)
                    .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusMedium))
                    .clickable { onNavigateToEarningsDetail() }
                    .padding(Dimens.SpacingMedium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Accrued Revenue Share (MTD)", fontSize = 12.sp, color = TextSecondary)
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier.padding(top = 2.dp)
                    ) {
                        Text("₹48,000", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent)
                        Text(" (50% Baseline)", fontSize = 11.sp, color = TextMuted, modifier = Modifier.padding(start = 4.dp, bottom = 2.dp))
                    }
                }
                Box(
                    modifier = Modifier
                        .background(SurfaceCard, RoundedCornerShape(Dimens.CornerRadiusSmall))
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusSmall))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("View ➔", fontSize = 11.sp, color = TextPrimary)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Upcoming Clients
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ASSIGNED CLIENTS FOR TODAY",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextSecondary
                )
                Text(
                    text = "View All (8)",
                    fontSize = 12.sp,
                    color = ChampagneAccent,
                    modifier = Modifier.clickable { onNavigateToMyClients() }
                )
            }

            listOf(
                Pair("Dev Sharma", "+91 98765 ••••• • Quarterly Gold"),
                Pair("Karan Singhania", "+91 98331 ••••• • Last session yesterday"),
                Pair("Sahil Kapoor", "+91 98210 ••••• • Last session 06 Nov")
            ).forEach { (name, info) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = Dimens.SpacingSmall)
                        .clip(RoundedCornerShape(Dimens.CornerRadiusMedium))
                        .background(SurfaceElevated)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusMedium))
                        .padding(Dimens.SpacingMedium),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                        Text(info, fontSize = 11.sp, color = TextMuted, modifier = Modifier.padding(top = 1.dp))
                    }
                    Box(
                        modifier = Modifier
                            .background(ActionIvory, RoundedCornerShape(Dimens.CornerRadiusSmall))
                            .clickable { onNavigateToVerifySession() }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text("Start", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ActionIvoryText)
                    }
                }
            }
        }
    }
}

