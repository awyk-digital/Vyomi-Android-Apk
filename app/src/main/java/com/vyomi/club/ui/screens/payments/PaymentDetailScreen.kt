package com.vyomi.club.ui.screens.payments

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vyomi.club.ui.components.VyomiDestructiveButton
import com.vyomi.club.ui.components.VyomiTopAppBar
import com.vyomi.club.ui.theme.ChampagneAccent
import com.vyomi.club.ui.theme.DarkBackground
import com.vyomi.club.ui.theme.Dimens
import com.vyomi.club.ui.theme.StatusSuccess
import com.vyomi.club.ui.theme.StatusSuccessBorder
import com.vyomi.club.ui.theme.StatusSuccessContainer
import com.vyomi.club.ui.theme.SurfaceBorder
import com.vyomi.club.ui.theme.SurfaceCard
import com.vyomi.club.ui.theme.SurfaceElevated
import com.vyomi.club.ui.theme.TextMuted
import com.vyomi.club.ui.theme.TextPrimary
import com.vyomi.club.ui.theme.TextSecondary

@Composable
fun PaymentDetailScreen(
    paymentId: String,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            VyomiTopAppBar(
                title = "Payment Receipt",
                subtitle = "#$paymentId",
                onBackClick = onBackClick
            )

            Column(modifier = Modifier.padding(Dimens.SpacingStandard)) {
                // Receipt Hero Card
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(Dimens.CornerRadiusLarge))
                        .background(SurfaceElevated)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusLarge))
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Total Collected Amount", fontSize = 12.sp, color = TextSecondary)
                    Text(
                        text = "₹18,000.00",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Box(
                        modifier = Modifier
                            .background(StatusSuccessContainer, RoundedCornerShape(Dimens.CornerRadiusFull))
                            .border(Dimens.BorderWidthStandard, StatusSuccessBorder, RoundedCornerShape(Dimens.CornerRadiusFull))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text("✓ Confirmed Payment Record", fontSize = 11.sp, color = StatusSuccess, fontWeight = FontWeight.Medium)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Itemized Ledger Details
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(Dimens.CornerRadiusMedium))
                        .background(SurfaceElevated)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusMedium))
                        .padding(Dimens.SpacingStandard),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Client:", fontSize = 12.sp, color = TextSecondary)
                        Text("Dev Sharma (#VY-2026-089)", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Associated Trainer:", fontSize = 12.sp, color = TextSecondary)
                        Text("Kabir Roy", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Applied Rate Snapshot:", fontSize = 12.sp, color = TextSecondary)
                        Text("50.0%", fontSize = 13.sp, color = TextPrimary)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Accrued Trainer Share:", fontSize = 12.sp, color = TextSecondary)
                        Text("₹9,000.00 (Accrued)", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Payment Mode:", fontSize = 12.sp, color = TextSecondary)
                        Text("UPI / QR Scan (UPI/6120984)", fontSize = 12.sp, color = TextPrimary)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Recorded By Staff:", fontSize = 12.sp, color = TextSecondary)
                        Text("Priya Patel (Front Desk)", fontSize = 12.sp, color = TextPrimary)
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Timestamp:", fontSize = 12.sp, color = TextSecondary)
                        Text("08 Nov 2026, 11:22 AM", fontSize = 12.sp, color = TextMuted)
                    }
                }

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
                        text = "Accounting Rule: Trainer share is tracked as an accrued liability for period settlement. The rate is snapshotted permanently to guarantee historical accuracy.",
                        fontSize = 11.sp,
                        color = TextMuted
                    )
                }
            }
        }

        Column(modifier = Modifier.padding(Dimens.SpacingStandard)) {
            VyomiDestructiveButton(
                text = "Request Audited Correction (Admin Only)",
                onClick = { /* Admin correction affordance */ }
            )
        }
    }
}

