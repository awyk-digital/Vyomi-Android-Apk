package com.vyomi.club.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vyomi.club.data.model.Client
import com.vyomi.club.data.model.PaymentRecord
import com.vyomi.club.data.model.Trainer
import com.vyomi.club.data.model.VyomiStatusType
import com.vyomi.club.ui.theme.ChampagneAccent
import com.vyomi.club.ui.theme.Dimens
import com.vyomi.club.ui.theme.StatusSuccess
import com.vyomi.club.ui.theme.SurfaceBorder
import com.vyomi.club.ui.theme.SurfaceCard
import com.vyomi.club.ui.theme.SurfaceElevated
import com.vyomi.club.ui.theme.TextMuted
import com.vyomi.club.ui.theme.TextPrimary
import com.vyomi.club.ui.theme.TextSecondary

@Composable
fun VyomiStatCard(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
    highlight: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(Dimens.CornerRadiusMedium))
            .background(SurfaceElevated)
            .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusMedium))
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
            .padding(Dimens.SpacingMedium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = if (highlight) ChampagneAccent else TextPrimary
        )
        Text(
            text = label,
            fontSize = 11.sp,
            color = TextSecondary,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

@Composable
fun VyomiClientRowCard(
    client: Client,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Dimens.CornerRadiusMedium))
            .background(SurfaceElevated)
            .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusMedium))
            .clickable { onClick() }
            .padding(Dimens.SpacingMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            // Client Initials Avatar
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(SurfaceCard, CircleShape)
                    .border(Dimens.BorderWidthStandard, SurfaceBorder, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                val initials = client.name.split(" ").mapNotNull { it.firstOrNull()?.toString() }.take(2).joinToString("")
                Text(
                    text = initials,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = ChampagneAccent
                )
            }

            Column(modifier = Modifier.padding(start = Dimens.SpacingMedium)) {
                Text(
                    text = client.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                Text(
                    text = "${client.phone} • ${client.membershipTier}",
                    fontSize = 12.sp,
                    color = TextSecondary,
                    modifier = Modifier.padding(top = 1.dp)
                )
                Text(
                    text = "Trainer: ${client.assignedTrainerName ?: "Unassigned"}",
                    fontSize = 11.sp,
                    color = if (client.assignedTrainerName == null) ChampagneAccent else TextMuted,
                    modifier = Modifier.padding(top = 1.dp)
                )
            }
        }

        VyomiStatusChip(
            status = if (client.isPhoneVerified) VyomiStatusType.ACTIVE else VyomiStatusType.PENDING_OTP
        )
    }
}

@Composable
fun VyomiTrainerRowCard(
    trainer: Trainer,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Dimens.CornerRadiusMedium))
            .background(SurfaceElevated)
            .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusMedium))
            .clickable { onClick() }
            .padding(Dimens.SpacingMedium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .background(SurfaceCard, CircleShape)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    val initials = trainer.name.split(" ").mapNotNull { it.firstOrNull()?.toString() }.take(2).joinToString("")
                    Text(
                        text = initials,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = ChampagneAccent
                    )
                }
                Column(modifier = Modifier.padding(start = Dimens.SpacingMedium)) {
                    Text(
                        text = trainer.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                    Text(
                        text = "${trainer.assignedClientCount} Assigned Clients • ${trainer.verifiedSessionCount} Sessions",
                        fontSize = 11.sp,
                        color = TextSecondary
                    )
                }
            }

            VyomiStatusChip(
                status = VyomiStatusType.ACTIVE,
                customLabel = trainer.status
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.SpacingSmall)
                .height(1.dp)
                .background(SurfaceBorder)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.SpacingSmall),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Applied Share: ${trainer.appliedSharePercent.toInt()}% Baseline",
                fontSize = 12.sp,
                color = ChampagneAccent,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Manage Clients ➔",
                fontSize = 12.sp,
                color = TextSecondary
            )
        }
    }
}

