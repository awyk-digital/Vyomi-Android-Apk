package com.vyomi.club.ui.screens.trainer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.vyomi.club.data.model.SessionRecord
import com.vyomi.club.data.model.VyomiStatusType
import com.vyomi.club.ui.components.VyomiStatusChip
import com.vyomi.club.ui.components.VyomiTopAppBar
import com.vyomi.club.ui.theme.DarkBackground
import com.vyomi.club.ui.theme.Dimens
import com.vyomi.club.ui.theme.SurfaceBorder
import com.vyomi.club.ui.theme.SurfaceElevated
import com.vyomi.club.ui.theme.TextMuted
import com.vyomi.club.ui.theme.TextPrimary
import com.vyomi.club.ui.theme.TextSecondary

@Composable
fun SessionHistoryScreen(
    onBackClick: (() -> Unit)? = null
) {
    val sessions = listOf(
        SessionRecord("SES-8901", "C-089", "Dev Sharma", "TR-01", "Kabir Roy", "08 Nov 2026", "11:15 AM", true),
        SessionRecord("SES-8900", "C-079", "Sahil Kapoor", "TR-01", "Kabir Roy", "08 Nov 2026", "09:30 AM", true),
        SessionRecord("SES-8899", "C-085", "Karan Singhania", "TR-01", "Kabir Roy", "08 Nov 2026", "07:45 AM", true),
        SessionRecord("SES-8895", "C-072", "Tarun Varma", "TR-01", "Kabir Roy", "07 Nov 2026", "06:10 PM", true)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        VyomiTopAppBar(
            title = "Session Attendance Log",
            subtitle = "Verified Records",
            onBackClick = onBackClick
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.SpacingStandard, vertical = Dimens.SpacingSmall),
            verticalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall)
        ) {
            items(sessions) { session ->
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
                        Text(
                            text = session.clientName,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimary
                        )
                        Text(
                            text = "${session.sessionDate} at ${session.verifiedAt}",
                            fontSize = 12.sp,
                            color = TextSecondary,
                            modifier = Modifier.padding(top = 1.dp)
                        )
                        Text(
                            text = "Trainer: ${session.trainerName} • #${session.sessionId}",
                            fontSize = 10.sp,
                            color = TextMuted,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }

                    VyomiStatusChip(status = VyomiStatusType.LOCKED)
                }
            }
        }
    }
}

