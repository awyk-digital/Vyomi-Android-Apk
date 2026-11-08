package com.vyomi.club.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vyomi.club.data.model.VyomiStatusType
import com.vyomi.club.ui.theme.Dimens
import com.vyomi.club.ui.theme.StatusError
import com.vyomi.club.ui.theme.StatusErrorBorder
import com.vyomi.club.ui.theme.StatusErrorContainer
import com.vyomi.club.ui.theme.StatusPending
import com.vyomi.club.ui.theme.StatusPendingBorder
import com.vyomi.club.ui.theme.StatusPendingContainer
import com.vyomi.club.ui.theme.StatusSuccess
import com.vyomi.club.ui.theme.StatusSuccessBorder
import com.vyomi.club.ui.theme.StatusSuccessContainer
import com.vyomi.club.ui.theme.SurfaceBorder
import com.vyomi.club.ui.theme.SurfaceCard
import com.vyomi.club.ui.theme.TextPrimary
import com.vyomi.club.ui.theme.TextSecondary

@Composable
fun VyomiStatusChip(
    status: VyomiStatusType,
    modifier: Modifier = Modifier,
    customLabel: String? = null
) {
    val (bgColor, borderColor, textColor, iconString, defaultLabel) = when (status) {
        VyomiStatusType.VERIFIED -> {
            Triple(StatusSuccessContainer, StatusSuccessBorder, StatusSuccess) to Pair("●", "Verified")
        }
        VyomiStatusType.ACTIVE -> {
            Triple(StatusSuccessContainer, StatusSuccessBorder, StatusSuccess) to Pair("●", "Active")
        }
        VyomiStatusType.PENDING_OTP -> {
            Triple(StatusPendingContainer, StatusPendingBorder, StatusPending) to Pair("⏳", "Pending OTP")
        }
        VyomiStatusType.UNASSIGNED -> {
            Triple(StatusPendingContainer, StatusPendingBorder, StatusPending) to Pair("⏳", "Unassigned")
        }
        VyomiStatusType.OVERDUE -> {
            Triple(StatusErrorContainer, StatusErrorBorder, StatusError) to Pair("⚠", "Overdue")
        }
        VyomiStatusType.LOCKED -> {
            Triple(SurfaceCard, SurfaceBorder, TextSecondary) to Pair("🔒", "Locked Record")
        }
    }.let { (colors, content) ->
        Tuple5(colors.first, colors.second, colors.third, content.first, content.second)
    }

    Row(
        modifier = modifier
            .background(bgColor, RoundedCornerShape(Dimens.CornerRadiusFull))
            .border(Dimens.BorderWidthStandard, borderColor, RoundedCornerShape(Dimens.CornerRadiusFull))
            .padding(horizontal = 8.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$iconString ",
            fontSize = 10.sp,
            color = textColor
        )
        Text(
            text = customLabel ?: defaultLabel,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = textColor
        )
    }
}

private data class Tuple5<A, B, C, D, E>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E
)

