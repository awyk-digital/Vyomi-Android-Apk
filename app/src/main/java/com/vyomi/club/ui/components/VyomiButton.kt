package com.vyomi.club.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vyomi.club.ui.theme.ActionIvory
import com.vyomi.club.ui.theme.ActionIvoryText
import com.vyomi.club.ui.theme.Dimens
import com.vyomi.club.ui.theme.StatusError
import com.vyomi.club.ui.theme.SurfaceBorder
import com.vyomi.club.ui.theme.SurfaceElevated
import com.vyomi.club.ui.theme.TextMuted
import com.vyomi.club.ui.theme.TextPrimary

@Composable
fun VyomiPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = Dimens.MinTouchTargetSize),
        enabled = enabled && !isLoading,
        shape = RoundedCornerShape(Dimens.CornerRadiusMedium),
        colors = ButtonDefaults.buttonColors(
            containerColor = ActionIvory,
            contentColor = ActionIvoryText,
            disabledContainerColor = SurfaceElevated,
            disabledContentColor = TextMuted
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = ActionIvoryText,
                strokeWidth = 2.dp
            )
        } else {
            Text(
                text = text,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun VyomiSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = Dimens.MinTouchTargetSize),
        enabled = enabled,
        shape = RoundedCornerShape(Dimens.CornerRadiusMedium),
        border = BorderStroke(Dimens.BorderWidthStandard, SurfaceBorder),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = SurfaceElevated,
            contentColor = TextPrimary
        )
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun VyomiDestructiveButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = Dimens.MinTouchTargetSize),
        enabled = enabled,
        shape = RoundedCornerShape(Dimens.CornerRadiusMedium),
        border = BorderStroke(Dimens.BorderWidthStandard, StatusError.copy(alpha = 0.5f)),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = SurfaceElevated,
            contentColor = StatusError
        )
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

