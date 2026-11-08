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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vyomi.club.ui.components.VyomiPrimaryButton
import com.vyomi.club.ui.components.VyomiTextField
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
fun SixMonthReviewScreen(
    onApproveReview: () -> Unit,
    onBackClick: () -> Unit
) {
    var proposedRateText by remember { mutableStateOf("55") }
    var effectiveDateText by remember { mutableStateOf("01 December 2026") }
    var reviewNotes by remember {
        mutableStateOf("Consistently high client retention and 100% verified attendance punctuality. Approved +5% incentive hike for next half-year.")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            VyomiTopAppBar(
                title = "Six-Month Incentive Review",
                subtitle = "Kabir Roy",
                onBackClick = onBackClick
            )

            Column(modifier = Modifier.padding(Dimens.SpacingStandard)) {
                Text(
                    text = "Semi-Annual Performance Evaluation",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = "Review completed 6-month cycle and approve discretionary rate adjustments.",
                    fontSize = 13.sp,
                    color = TextSecondary,
                    modifier = Modifier.padding(top = 2.dp, bottom = 20.dp)
                )

                // 6-Month Review Metrics Card
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(Dimens.CornerRadiusLarge))
                        .background(SurfaceElevated)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusLarge))
                        .padding(Dimens.SpacingStandard),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text("Cycle Performance Summary:", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = ChampagneAccent)

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Review Period:", fontSize = 12.sp, color = TextSecondary)
                        Text("01 Jun 2026 – 30 Nov 2026", fontSize = 12.sp, color = TextPrimary)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Eligible Collections:", fontSize = 12.sp, color = TextSecondary)
                        Text("₹5,40,000 INR", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Verified Sessions Conducted:", fontSize = 12.sp, color = TextSecondary)
                        Text("312 Verified", fontSize = 12.sp, color = TextPrimary)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Accrued Baseline Share (50%):", fontSize = 12.sp, color = TextSecondary)
                        Text("₹2,70,000 INR", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Current Base Percentage:", fontSize = 12.sp, color = TextSecondary)
                        Text("50.0%", fontSize = 12.sp, color = TextPrimary)
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Adjustment Form Fields
                VyomiTextField(
                    value = proposedRateText,
                    onValueChange = { proposedRateText = it },
                    label = "Proposed New Percentage (Manual Evaluation) *",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    supportingText = "Percentage increase is manually reviewed, not automatically granted."
                )

                Spacer(modifier = Modifier.height(Dimens.SpacingMedium))

                VyomiTextField(
                    value = effectiveDateText,
                    onValueChange = { effectiveDateText = it },
                    label = "Effective Date of Adjustment *",
                    placeholder = "e.g. 01 December 2026"
                )

                Spacer(modifier = Modifier.height(Dimens.SpacingMedium))

                VyomiTextField(
                    value = reviewNotes,
                    onValueChange = { reviewNotes = it },
                    label = "Executive Evaluation Notes",
                    singleLine = false
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(Dimens.CornerRadiusSmall))
                        .background(SurfaceCard)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusSmall))
                        .padding(12.dp)
                ) {
                    Text(
                        text = "🔒 Snapshot Rule: If approved, the new 55% rate applies ONLY to payments recorded on or after 01 Dec 2026. Older payments permanently preserve their original 50% rate.",
                        fontSize = 11.sp,
                        color = TextMuted
                    )
                }
            }
        }

        Column(modifier = Modifier.padding(Dimens.SpacingStandard)) {
            VyomiPrimaryButton(
                text = "Approve Percentage Adjustment (Owner)",
                onClick = onApproveReview
            )
        }
    }
}

