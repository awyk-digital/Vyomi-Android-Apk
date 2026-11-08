package com.vyomi.club.ui.screens.mastertrainer

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vyomi.club.data.model.Trainer
import com.vyomi.club.ui.components.VyomiPrimaryButton
import com.vyomi.club.ui.components.VyomiSecondaryButton
import com.vyomi.club.ui.components.VyomiTextField
import com.vyomi.club.ui.components.VyomiTopAppBar
import com.vyomi.club.ui.theme.ChampagneAccent
import com.vyomi.club.ui.theme.DarkBackground
import com.vyomi.club.ui.theme.Dimens
import com.vyomi.club.ui.theme.StatusSuccess
import com.vyomi.club.ui.theme.SurfaceBorder
import com.vyomi.club.ui.theme.SurfaceCard
import com.vyomi.club.ui.theme.SurfaceElevated
import com.vyomi.club.ui.theme.TextMuted
import com.vyomi.club.ui.theme.TextPrimary
import com.vyomi.club.ui.theme.TextSecondary

@Composable
fun AssignTrainerScreen(
    clientId: String,
    onConfirmAssignment: (trainerName: String) -> Unit,
    onBackClick: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTrainerId by remember { mutableStateOf("TR-01") }
    var showConfirmation by remember { mutableStateOf(false) }

    val trainers = listOf(
        Trainer("TR-01", "Kabir Roy", "Active", "Strength & Hypertrophy", 8, 142, 50.0),
        Trainer("TR-02", "Sneha Rao", "Active", "Functional & HIIT", 6, 98, 50.0),
        Trainer("TR-03", "Vikram Malhotra", "Active", "Athletic Conditioning", 12, 210, 50.0),
        Trainer("TR-04", "Aditi Nair", "Active", "Mobility & Core", 5, 76, 50.0)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            VyomiTopAppBar(
                title = if (showConfirmation) "Confirm Assignment" else "Select Trainer",
                subtitle = "Client #$clientId",
                onBackClick = { if (showConfirmation) showConfirmation = false else onBackClick() }
            )

            Column(modifier = Modifier.padding(Dimens.SpacingStandard)) {
                // Client Summary Card
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(Dimens.CornerRadiusMedium))
                        .background(SurfaceElevated)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusMedium))
                        .padding(Dimens.SpacingMedium)
                ) {
                    Text("CLIENT TO ASSIGN", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = TextMuted)
                    Text("Dev Sharma", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextPrimary, modifier = Modifier.padding(top = 2.dp))
                    Text("Quarterly Gold (3 Months) • +91 98765 •••••", fontSize = 12.sp, color = TextSecondary, modifier = Modifier.padding(top = 1.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (!showConfirmation) {
                    // Search
                    VyomiTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        label = "Search Available Trainers",
                        placeholder = "Name or specialization..."
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "SELECT TRAINER (SINGLE ALLOCATION)",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextSecondary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Trainer List
                    trainers.forEach { trainer ->
                        val isSelected = selectedTrainerId == trainer.id
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = Dimens.SpacingSmall)
                                .clip(RoundedCornerShape(Dimens.CornerRadiusMedium))
                                .background(if (isSelected) SurfaceCard else SurfaceElevated)
                                .border(
                                    Dimens.BorderWidthStandard,
                                    if (isSelected) ChampagneAccent else SurfaceBorder,
                                    RoundedCornerShape(Dimens.CornerRadiusMedium)
                                )
                                .clickable { selectedTrainerId = trainer.id }
                                .padding(Dimens.SpacingMedium),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(SurfaceCard, CircleShape)
                                        .border(Dimens.BorderWidthStandard, SurfaceBorder, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    val initials = trainer.name.split(" ").mapNotNull { it.firstOrNull()?.toString() }.joinToString("")
                                    Text(initials, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent)
                                }
                                Column(modifier = Modifier.padding(start = Dimens.SpacingMedium)) {
                                    Text(trainer.name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                                    Text("${trainer.specialization} • ${trainer.assignedClientCount} Clients", fontSize = 11.sp, color = TextSecondary)
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .background(SurfaceCard, RoundedCornerShape(Dimens.CornerRadiusSmall))
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = if (isSelected) "✓ Selected" else "Available",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = if (isSelected) ChampagneAccent else StatusSuccess
                                )
                            }
                        }
                    }
                } else {
                    // Confirmation View
                    val chosenTrainer = trainers.first { it.id == selectedTrainerId }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(Dimens.CornerRadiusLarge))
                            .background(SurfaceElevated)
                            .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusLarge))
                            .padding(Dimens.SpacingStandard),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text("Assignment Audit Parameters:", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = ChampagneAccent)
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Client:", fontSize = 12.sp, color = TextSecondary)
                            Text("Dev Sharma", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Assigned Trainer:", fontSize = 12.sp, color = TextSecondary)
                            Text(chosenTrainer.name, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent)
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Effective Date:", fontSize = 12.sp, color = TextSecondary)
                            Text("Immediate (Today, 11:00 AM)", fontSize = 12.sp, color = TextPrimary)
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Authorized By:", fontSize = 12.sp, color = TextSecondary)
                            Text("Arjun Verma (Master Trainer)", fontSize = 12.sp, color = TextPrimary)
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
                            text = "Operational Handover: Once confirmed, the client will instantly appear in ${chosenTrainer.name}'s active client roster for daily session OTP verification.",
                            fontSize = 11.sp,
                            color = TextMuted
                        )
                    }
                }
            }
        }

        // Bottom CTA
        Column(modifier = Modifier.padding(Dimens.SpacingStandard)) {
            if (!showConfirmation) {
                VyomiPrimaryButton(
                    text = "Review & Confirm Assignment",
                    onClick = { showConfirmation = true }
                )
            } else {
                val chosenTrainer = trainers.first { it.id == selectedTrainerId }
                VyomiPrimaryButton(
                    text = "Confirm & Bind Assignment",
                    onClick = { onConfirmAssignment(chosenTrainer.name) }
                )
                Spacer(modifier = Modifier.height(Dimens.SpacingSmall))
                VyomiSecondaryButton(
                    text = "Change Trainer Selection",
                    onClick = { showConfirmation = false }
                )
            }
        }
    }
}

