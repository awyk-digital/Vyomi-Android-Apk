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
import com.vyomi.club.data.model.PaymentRecord
import com.vyomi.club.ui.components.VyomiTopAppBar
import com.vyomi.club.ui.theme.ActionIvory
import com.vyomi.club.ui.theme.ActionIvoryText
import com.vyomi.club.ui.theme.ChampagneAccent
import com.vyomi.club.ui.theme.DarkBackground
import com.vyomi.club.ui.theme.Dimens
import com.vyomi.club.ui.theme.StatusSuccess
import com.vyomi.club.ui.theme.SurfaceBorder
import com.vyomi.club.ui.theme.SurfaceElevated
import com.vyomi.club.ui.theme.TextMuted
import com.vyomi.club.ui.theme.TextPrimary
import com.vyomi.club.ui.theme.TextSecondary

@Composable
fun PaymentListScreen(
    onNavigateToRecordPayment: () -> Unit,
    onPaymentClick: (String) -> Unit,
    onBackClick: (() -> Unit)? = null
) {
    val payments = listOf(
        PaymentRecord("PAY-9921", "C-089", "Dev Sharma", "TR-01", "Kabir Roy", 18000.0, "08 Nov 2026", "UPI", "UPI/6120984", 50.0, 9000.0, "Priya Patel", true),
        PaymentRecord("PAY-9920", "C-088", "Ananya Kapoor", "TR-02", "Sneha Rao", 60000.0, "07 Nov 2026", "Bank Transfer", "NEFT/890123", 50.0, 30000.0, "Priya Patel", true),
        PaymentRecord("PAY-9919", "C-086", "Pooja Hegde", "TR-03", "Vikram Malhotra", 32000.0, "06 Nov 2026", "Credit Card", "TXN/44123", 50.0, 16000.0, "Priya Patel", true),
        PaymentRecord("PAY-9918", "C-085", "Karan Singhania", "TR-01", "Kabir Roy", 18000.0, "05 Nov 2026", "UPI", "UPI/554901", 50.0, 9000.0, "Priya Patel", true)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        VyomiTopAppBar(
            title = "Payment Registry",
            subtitle = "Desk Ledger",
            onBackClick = onBackClick,
            trailingContent = {
                Box(
                    modifier = Modifier
                        .background(ActionIvory, RoundedCornerShape(Dimens.CornerRadiusSmall))
                        .clickable { onNavigateToRecordPayment() }
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text("+ Record Payment", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ActionIvoryText)
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.SpacingStandard, vertical = Dimens.SpacingSmall),
            verticalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall)
        ) {
            items(payments) { payment ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(Dimens.CornerRadiusMedium))
                        .background(SurfaceElevated)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusMedium))
                        .clickable { onPaymentClick(payment.paymentId) }
                        .padding(Dimens.SpacingMedium)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column {
                            Text(payment.clientName, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                            Text("#${payment.paymentId} • ${payment.paymentMode} • ${payment.paymentDate}", fontSize = 11.sp, color = TextSecondary, modifier = Modifier.padding(top = 1.dp))
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("₹${payment.collectedAmount.toInt()}", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                            Text("● Confirmed", fontSize = 10.sp, color = StatusSuccess, modifier = Modifier.padding(top = 1.dp))
                        }
                    }

                    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).height(1.dp).background(SurfaceBorder))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Trainer: ${payment.trainerName}", fontSize = 12.sp, color = ChampagneAccent)
                        Text("Accrued 50% Share: ₹${payment.trainerShareAmount.toInt()}", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = TextSecondary)
                    }
                }
            }
        }
    }
}

