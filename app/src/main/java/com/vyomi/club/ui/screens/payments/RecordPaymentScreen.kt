package com.vyomi.club.ui.screens.payments

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
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
import com.vyomi.club.ui.components.VyomiSecondaryButton
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
fun RecordPaymentScreen(
    onPaymentStored: (String) -> Unit,
    onBackClick: () -> Unit
) {
    var isReviewing by remember { mutableStateOf(false) }

    var clientName by remember { mutableStateOf("Dev Sharma (+91 98765 •••••)") }
    var associatedTrainer by remember { mutableStateOf("Kabir Roy (Strength PT)") }
    var collectedAmountText by remember { mutableStateOf("18000") }
    var paymentMode by remember { mutableStateOf("UPI / QR Scan") }
    var txnReference by remember { mutableStateOf("UPI/6120984") }

    val amountDouble = collectedAmountText.toDoubleOrNull() ?: 0.0
    val trainerShareBaseline = amountDouble * 0.50

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            VyomiTopAppBar(
                title = if (isReviewing) "Review Payment Record" else "Record Offline Payment",
                onBackClick = { if (isReviewing) isReviewing = false else onBackClick() }
            )

            Column(modifier = Modifier.padding(Dimens.SpacingStandard)) {
                if (!isReviewing) {
                    Text(
                        text = "Desk Payment Collection",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Text(
                        text = "Record externally collected funds and compute baseline trainer share.",
                        fontSize = 13.sp,
                        color = TextSecondary,
                        modifier = Modifier.padding(top = 2.dp, bottom = 20.dp)
                    )

                    // Client
                    VyomiTextField(
                        value = clientName,
                        onValueChange = { clientName = it },
                        label = "Selected Client *",
                        readOnly = true
                    )

                    Spacer(modifier = Modifier.height(Dimens.SpacingMedium))

                    // Associated Trainer
                    VyomiTextField(
                        value = associatedTrainer,
                        onValueChange = {},
                        label = "Associated Trainer (Auto-Linked)",
                        readOnly = true,
                        supportingText = "Revenue share is credited against this trainer's account."
                    )

                    Spacer(modifier = Modifier.height(Dimens.SpacingMedium))

                    // Collected Amount
                    VyomiTextField(
                        value = collectedAmountText,
                        onValueChange = { collectedAmountText = it },
                        label = "Amount Actually Collected in INR *",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        leadingIcon = {
                            Text("₹  ", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent, modifier = Modifier.padding(start = 12.dp))
                        }
                    )

                    Spacer(modifier = Modifier.height(Dimens.SpacingMedium))

                    // Payment Mode
                    VyomiTextField(
                        value = paymentMode,
                        onValueChange = { paymentMode = it },
                        label = "Payment Mode",
                        placeholder = "UPI, Cash, Card, Bank"
                    )

                    Spacer(modifier = Modifier.height(Dimens.SpacingMedium))

                    // Txn Ref
                    VyomiTextField(
                        value = txnReference,
                        onValueChange = { txnReference = it },
                        label = "Transaction Reference / Desk Notes",
                        placeholder = "e.g. UPI/6120984 or Desk Receipt #42"
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Auto-Calculated Baseline Share Card
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(Dimens.CornerRadiusMedium))
                            .background(SurfaceElevated)
                            .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusMedium))
                            .padding(Dimens.SpacingMedium)
                    ) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Trainer Share %:", fontSize = 12.sp, color = TextSecondary)
                            Text("50% (Standard Baseline)", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent)
                        }
                        Box(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).height(1.dp).background(SurfaceBorder))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Calculated Trainer Share:", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                            Text("₹${trainerShareBaseline.toInt()} INR", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent)
                        }
                        Text(
                            text = "Note: Share % is read-only for front desk. Only owner/admin can modify compensation rules.",
                            fontSize = 11.sp,
                            color = TextMuted,
                            modifier = Modifier.padding(top = 6.dp)
                        )
                    }
                } else {
                    // REVIEW VIEW (Screen 26)
                    Text(
                        text = "Confirm Payment Entry",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Text(
                        text = "Verify offline payment details before recording into accounting ledger.",
                        fontSize = 13.sp,
                        color = TextSecondary,
                        modifier = Modifier.padding(top = 2.dp, bottom = 20.dp)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(Dimens.CornerRadiusLarge))
                            .background(SurfaceElevated)
                            .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusLarge))
                            .padding(Dimens.SpacingStandard),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Client:", fontSize = 12.sp, color = TextSecondary)
                            Text("Dev Sharma", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Amount Collected:", fontSize = 12.sp, color = TextSecondary)
                            Text("₹${amountDouble.toInt()} INR", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Associated Trainer:", fontSize = 12.sp, color = TextSecondary)
                            Text(associatedTrainer, fontSize = 13.sp, color = ChampagneAccent)
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Applied Rate Snapshot:", fontSize = 12.sp, color = TextSecondary)
                            Text("50% (Standard)", fontSize = 12.sp, color = TextPrimary)
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Accrued Trainer Share:", fontSize = 12.sp, color = TextSecondary)
                            Text("₹${trainerShareBaseline.toInt()} INR", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent)
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Mode & Reference:", fontSize = 12.sp, color = TextSecondary)
                            Text("$paymentMode ($txnReference)", fontSize = 12.sp, color = TextMuted)
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
                            text = "⚠️ Operational Safeguard: This screen records funds received offline. It does NOT trigger a payment gateway and does NOT disburse money to the trainer.",
                            fontSize = 11.sp,
                            color = TextMuted
                        )
                    }
                }
            }
        }

        // Action Buttons
        Column(modifier = Modifier.padding(Dimens.SpacingStandard)) {
            if (!isReviewing) {
                VyomiPrimaryButton(
                    text = "Review Payment Entry ➔",
                    onClick = { isReviewing = true }
                )
            } else {
                VyomiPrimaryButton(
                    text = "Confirm & Store Payment Record",
                    onClick = { onPaymentStored("PAY-9921") }
                )
                Spacer(modifier = Modifier.height(Dimens.SpacingSmall))
                VyomiSecondaryButton(
                    text = "Edit Details",
                    onClick = { isReviewing = false }
                )
            }
        }
    }
}

