package com.vyomi.club.ui.screens.frontdesk

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
import com.vyomi.club.data.model.Client
import com.vyomi.club.data.repository.VyomiRepository
import com.vyomi.club.ui.components.VyomiOtpInputField
import com.vyomi.club.ui.components.VyomiPrimaryButton
import com.vyomi.club.ui.components.VyomiTextField
import com.vyomi.club.ui.components.VyomiTopAppBar
import com.vyomi.club.ui.theme.ActionIvory
import com.vyomi.club.ui.theme.ActionIvoryText
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
fun ClientRegistrationScreen(
    onNavigateBack: () -> Unit,
    onRegistrationCompleted: (String) -> Unit
) {
    // Form State
    var clientName by remember { mutableStateOf("") }
    var clientAddress by remember { mutableStateOf("") }
    var clientPhone by remember { mutableStateOf("") }
    var photoCaptured by remember { mutableStateOf(false) }

    // OTP Verification State
    var isOtpRequested by remember { mutableStateOf(false) }
    var otpInput by remember { mutableStateOf("") }
    var isPhoneVerified by remember { mutableStateOf(false) }

    // Membership Plan State
    var selectedPlan by remember { mutableStateOf("Quarterly Gold") }
    var planDuration by remember { mutableStateOf("3 Months") }
    var planPrice by remember { mutableStateOf(18000.0) }

    // Success State
    var isRegistered by remember { mutableStateOf(false) }

    val membershipOptions = listOf(
        Triple("Quarterly Gold", "3 Months", 18000.0),
        Triple("Annual Platinum VIP", "12 Months", 60000.0),
        Triple("Monthly Core", "1 Month", 7500.0)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        VyomiTopAppBar(
            title = if (isRegistered) "Enrolled" else "Register Client",
            onBackClick = onNavigateBack,
            trailingContent = {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(SurfaceElevated, RoundedCornerShape(8.dp))
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("V", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent)
                }
            }
        )

        if (!isRegistered) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Dimens.SpacingStandard)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(Dimens.SpacingSmall))

                // ----------------------------------------------------
                // SECTION 1: CLIENT IDENTITY & PHOTO
                // ----------------------------------------------------
                Text(
                    text = "CLIENT IDENTITY",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.5.sp,
                    color = ChampagneAccent,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(Dimens.CornerRadiusLarge))
                        .background(SurfaceElevated)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusLarge))
                        .padding(Dimens.SpacingStandard),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(SurfaceCard)
                            .border(1.5.dp, if (photoCaptured) ChampagneAccent else SurfaceBorder, CircleShape)
                            .clickable { photoCaptured = !photoCaptured },
                        contentAlignment = Alignment.Center
                    ) {
                        if (photoCaptured) {
                            Text("👤", fontSize = 26.sp)
                        } else {
                            Text("📷", fontSize = 22.sp, color = TextMuted)
                        }
                    }

                    Column(modifier = Modifier.padding(start = Dimens.SpacingStandard)) {
                        Text(
                            text = if (photoCaptured) "Photo Captured ✓" else "Face Photo",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (photoCaptured) StatusSuccess else TextPrimary
                        )
                        Text(
                            text = if (photoCaptured) "Tap to retake" else "Tap circle to capture",
                            fontSize = 11.sp,
                            color = TextMuted
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Client Name
                VyomiTextField(
                    value = clientName,
                    onValueChange = { clientName = it },
                    label = "Client Full Name",
                    placeholder = "e.g. Dev Sharma"
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Residential Address
                VyomiTextField(
                    value = clientAddress,
                    onValueChange = { clientAddress = it },
                    label = "Residential Address",
                    placeholder = "Apartment, Building, Street, City"
                )

                Spacer(modifier = Modifier.height(24.dp))

                // ----------------------------------------------------
                // SECTION 2: PHONE & OTP VERIFICATION
                // ----------------------------------------------------
                Text(
                    text = "CONTACT & VERIFICATION",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.5.sp,
                    color = ChampagneAccent,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(Dimens.CornerRadiusLarge))
                        .background(SurfaceElevated)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusLarge))
                        .padding(Dimens.SpacingStandard)
                ) {
                    VyomiTextField(
                        value = clientPhone,
                        onValueChange = { if (it.length <= 10) clientPhone = it },
                        label = "Mobile Phone Number",
                        placeholder = "10-digit number",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        leadingIcon = {
                            Text(
                                text = "+91 ",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary,
                                modifier = Modifier.padding(start = 12.dp)
                            )
                        },
                        trailingIcon = {
                            if (!isPhoneVerified) {
                                Box(
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                        .background(SurfaceCard, RoundedCornerShape(6.dp))
                                        .border(Dimens.BorderWidthStandard, ChampagneAccent, RoundedCornerShape(6.dp))
                                        .clickable {
                                            if (clientPhone.length >= 10) {
                                                isOtpRequested = true
                                            }
                                        }
                                        .padding(horizontal = 10.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = if (isOtpRequested) "Resend OTP" else "Verify OTP",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = ChampagneAccent
                                    )
                                }
                            } else {
                                Box(
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                        .background(StatusSuccessContainer, RoundedCornerShape(6.dp))
                                        .padding(horizontal = 10.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = "● Verified ✓",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = StatusSuccess
                                    )
                                }
                            }
                        }
                    )

                    // Inline OTP Input (Appears when Verify is clicked)
                    if (isOtpRequested && !isPhoneVerified) {
                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "Enter 6-digit OTP received by client:",
                            fontSize = 12.sp,
                            color = TextSecondary,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        VyomiOtpInputField(
                            otpValue = otpInput,
                            onOtpChange = {
                                otpInput = it
                                if (it.length == 6) {
                                    isPhoneVerified = true
                                }
                            }
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Code expires in 04:30", fontSize = 11.sp, color = TextMuted)
                            Text(
                                text = "Confirm Code",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = ChampagneAccent,
                                modifier = Modifier.clickable {
                                    if (otpInput.isNotEmpty()) isPhoneVerified = true
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // ----------------------------------------------------
                // SECTION 3: MEMBERSHIP DETAILS
                // ----------------------------------------------------
                Text(
                    text = "MEMBERSHIP DETAILS",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.5.sp,
                    color = ChampagneAccent,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Selectable Tier Cards
                membershipOptions.forEach { (name, duration, price) ->
                    val isSelected = selectedPlan == name
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
                            .clickable {
                                selectedPlan = name
                                planDuration = duration
                                planPrice = price
                            }
                            .padding(horizontal = Dimens.SpacingStandard, vertical = 14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = name,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary
                            )
                            Text(
                                text = "$duration • Full Facility Access",
                                fontSize = 11.sp,
                                color = TextSecondary,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }
                        Text(
                            text = "₹${price.toInt().toString().replace(Regex("(\\d)(?=(\\d{3})+$)"), "$1,")}",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) ChampagneAccent else TextPrimary
                        )
                    }
                }

                // Compact Validity Strip
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(Dimens.CornerRadiusSmall))
                        .background(SurfaceElevated)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusSmall))
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Validity: 08 Nov 2026 – 07 Feb 2027", fontSize = 12.sp, color = TextSecondary)
                    Text("90 Days", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent)
                }

                Spacer(modifier = Modifier.height(28.dp))

                // Primary Complete Action
                VyomiPrimaryButton(
                    text = "Complete Registration ➔",
                    onClick = {
                        val newClient = Client(
                            id = "VY-2026-${(100..999).random()}",
                            name = if (clientName.isBlank()) "Dev Sharma" else clientName,
                            phone = if (clientPhone.length >= 5) "+91 ${clientPhone.take(5)} •••••" else "+91 98765 •••••",
                            rawPhone = if (clientPhone.isBlank()) "9876543210" else clientPhone,
                            membershipTier = selectedPlan,
                            membershipDuration = planDuration,
                            agreedPrice = planPrice,
                            startDate = "08 Nov 2026",
                            endDate = "07 Feb 2027",
                            isPhoneVerified = isPhoneVerified,
                            assignedTrainerName = null,
                            registeredBy = "Priya Patel",
                            registeredAt = "Just now"
                        )
                        VyomiRepository.instance.registerNewClient(newClient)
                        isRegistered = true
                    }
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        } else {
            // SUCCESS CONFIRMATION
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimens.SpacingStandard),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .background(StatusSuccessContainer, CircleShape)
                        .border(2.dp, StatusSuccess, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("✓", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = StatusSuccess)
                }

                Text(
                    text = "Client Registered",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    modifier = Modifier.padding(top = 18.dp)
                )

                Text(
                    text = if (clientName.isBlank()) "Dev Sharma" else clientName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = ChampagneAccent,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Text(
                    text = "$selectedPlan • $planDuration",
                    fontSize = 13.sp,
                    color = TextSecondary,
                    modifier = Modifier.padding(top = 2.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                VyomiPrimaryButton(
                    text = "View Client Profile",
                    onClick = { onRegistrationCompleted("VY-2026-089") }
                )

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            clientName = ""
                            clientAddress = ""
                            clientPhone = ""
                            isOtpRequested = false
                            otpInput = ""
                            isPhoneVerified = false
                            isRegistered = false
                        }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Register Another Client", fontSize = 13.sp, color = TextSecondary)
                }
            }
        }
    }
}
