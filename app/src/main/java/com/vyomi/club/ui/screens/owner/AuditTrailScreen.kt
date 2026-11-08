package com.vyomi.club.ui.screens.owner

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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

data class AuditLogEntry(
    val actor: String,
    val action: String,
    val details: String,
    val timestamp: String
)

@Composable
fun AuditTrailScreen(
    onBackClick: () -> Unit
) {
    val auditEvents = listOf(
        AuditLogEntry("Priya Patel (FD)", "Recorded Payment #PAY-9921", "Client Dev Sharma (₹18,000 via UPI)", "08 Nov, 11:22 AM"),
        AuditLogEntry("Kabir Roy (TR)", "Verified Session Attendance", "Client Dev Sharma (OTP Confirmed, Locked)", "08 Nov, 11:15 AM"),
        AuditLogEntry("Arjun Verma (MT)", "Assigned Trainer", "Dev Sharma assigned to Kabir Roy", "08 Nov, 10:15 AM"),
        AuditLogEntry("Priya Patel (FD)", "Client Enrolled & Phone Verified", "Dev Sharma (+91 98765•••••)", "08 Nov, 10:14 AM"),
        AuditLogEntry("Rohan Mehta (Owner)", "Approved Incentive Hike", "Kabir Roy 50% → 55% eff. Dec 2026", "07 Nov, 04:30 PM")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        VyomiTopAppBar(
            title = "System Audit Trail",
            subtitle = "Immutable Ledger",
            onBackClick = onBackClick
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.SpacingStandard, vertical = Dimens.SpacingSmall),
            verticalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall)
        ) {
            items(auditEvents) { event ->
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
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(event.action, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                        Text(event.timestamp, fontSize = 10.sp, color = TextMuted)
                    }
                    Text(event.details, fontSize = 12.sp, color = ChampagneAccent, modifier = Modifier.padding(top = 2.dp))
                    Text("Actor: ${event.actor}", fontSize = 11.sp, color = TextSecondary, modifier = Modifier.padding(top = 2.dp))
                }
            }
        }
    }
}

