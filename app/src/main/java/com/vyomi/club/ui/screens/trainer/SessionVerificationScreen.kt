package com.vyomi.club.ui.screens.trainer

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.vyomi.club.ui.components.VyomiOtpInputField
import com.vyomi.club.ui.components.VyomiPrimaryButton
import com.vyomi.club.ui.components.VyomiSecondaryButton
import com.vyomi.club.ui.components.VyomiTextField
import com.vyomi.club.ui.components.VyomiTopAppBar
import com.vyomi.club.ui.theme.ChampagneAccent
import com.vyomi.club.ui.theme.DarkBackground
import com.vyomi.club.ui.theme.Dimens
import com.vyomi.club.ui.theme.StatusSuccess
import com.vyomi.club.ui.theme.StatusSuccessContainer
import com.vyomi.club.ui.theme.SurfaceBorder
import com.vyomi.club.ui.theme.SurfaceCard
import com.vyomi.club.ui.theme.SurfaceElevated
import com.vyomi.club.ui.theme.TextMuted
import com.vyomi.club.ui.theme.TextPrimary
import com.vyomi.club.ui.theme.TextSecondary

@Composable
fun SessionVerificationScreen(
    onNavigateBack: () -> Unit,
    onSessionVerifiedCompleted: () -> Unit
) {
    var stage by remember { mutableIntStateOf(1) } // 1: Enter Phone / Check, 2: Enter OTP, 3: Verified Locked
    var phoneInput by remember { mutableStateOf("9876543210") }
    var otpInput by remember { mutableStateOf("713592") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        VyomiTopAppBar(
            title = when (stage) {
                1 -> "Start Session Attendance"
                2 -> "Verify Session OTP"
                else -> "Attendance Verified"
            },
            onBackClick = { if (stage > 1 && stage < 3) stage-- else onNavigateBack() }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.SpacingStandard)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            when (stage) {
                // STAGE 1: CLIENT IDENTIFICATION & ELIGIBILITY CHECK
                1 -> {
                    Column {
                        Text(
                            text = "Daily Session Verification",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = "Enter client phone to check active membership and trainer assignment.",
                            fontSize = 13.sp,
                            color = TextSecondary,
                            modifier = Modifier.padding(top = 2.dp, bottom = 20.dp)
                        )

                        VyomiTextField(
                            value = phoneInput,
                            onValueChange = { phoneInput = it },
                            label = "Registered Client Mobile",
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            leadingIcon = {
                                Text("+91  ", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary, modifier = Modifier.padding(start = 12.dp))
                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Verification Criteria Result Card
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(Dimens.CornerRadiusLarge))
                                .background(SurfaceElevated)
                                .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusLarge))
                                .padding(Dimens.SpacingStandard),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text("Client Eligibility Status:", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = ChampagneAccent)

                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Client:", fontSize = 12.sp, color = TextSecondary)
                                Text("Dev Sharma", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                            }
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Membership Validity:", fontSize = 12.sp, color = TextSecondary)
                                Text("✓ Active (64 days remaining)", fontSize = 12.sp, color = StatusSuccess)
                            }
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Trainer Assignment:", fontSize = 12.sp, color = TextSecondary)
                                Text("✓ Assigned to Kabir Roy", fontSize = 12.sp, color = StatusSuccess)
                            }
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Today's Attendance:", fontSize = 12.sp, color = TextSecondary)
                                Text("⏳ Pending daily lock", fontSize = 12.sp, color = ChampagneAccent)
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
                                text = "Requirement: OTP is sent to the client's registered mobile number. The trainer enters the client-supplied OTP to officially complete/lock attendance.",
                                fontSize = 11.sp,
                                color = TextMuted
                            )
                        }
                    }

                    VyomiPrimaryButton(
                        text = "Send Session OTP ➔",
                        onClick = { stage = 2 },
                        modifier = Modifier.padding(top = 24.dp)
                    )
                }

                // STAGE 2: ENTER OTP
                2 -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Enter Session Attendance OTP",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = "Dev Sharma • Masked +91 98765 •••••",
                            fontSize = 13.sp,
                            color = TextSecondary,
                            modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
                        )

                        VyomiOtpInputField(
                            otpValue = otpInput,
                            onOtpChange = { otpInput = it }
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Session OTP valid for 04:30",
                            fontSize = 12.sp,
                            color = TextMuted
                        )
                        Text(
                            text = "Resend OTP",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = ChampagneAccent,
                            modifier = Modifier
                                .clickable { /* Resend session OTP */ }
                                .padding(top = 6.dp)
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(Dimens.CornerRadiusSmall))
                                .background(SurfaceElevated)
                                .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusSmall))
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "🔒 Lock Rule: Successful OTP entry locks today's attendance record. Once locked, records are immutable.",
                                fontSize = 11.sp,
                                color = TextMuted
                            )
                        }
                    }

                    VyomiPrimaryButton(
                        text = "Verify Attendance & Lock Session",
                        onClick = { stage = 3 },
                        modifier = Modifier.padding(top = 24.dp)
                    )
                }

                // STAGE 3: VERIFIED & LOCKED
                3 -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(top = 20.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .background(StatusSuccessContainer, CircleShape)
                                .border(2.dp, StatusSuccess, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("✓", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = StatusSuccess)
                        }

                        Text(
                            text = "Attendance verified",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        Text(
                            text = "Daily attendance recorded and locked in facility database.",
                            fontSize = 13.sp,
                            color = TextSecondary,
                            modifier = Modifier.padding(top = 4.dp)
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Locked Record Card
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
                                Text("Trainer:", fontSize = 12.sp, color = TextSecondary)
                                Text("Kabir Roy", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent)
                            }
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Verification Timestamp:", fontSize = 12.sp, color = TextSecondary)
                                Text("08 Nov 2026, 11:15 AM", fontSize = 12.sp, color = TextPrimary)
                            }
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Record Integrity:", fontSize = 12.sp, color = TextSecondary)
                                Text("🔒 Locked & Tamper-Proof", fontSize = 12.sp, color = StatusSuccess)
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Note: Successful OTP verification officially marks today's attendance. Repeated submission for the same day is blocked.",
                            fontSize = 11.sp,
                            color = TextMuted
                        )
                    }

                    Column(modifier = Modifier.padding(top = 24.dp)) {
                        VyomiPrimaryButton(
                            text = "Return to Trainer Home",
                            onClick = onSessionVerifiedCompleted
                        )
                    }
                }
            }
        }
    }
}

