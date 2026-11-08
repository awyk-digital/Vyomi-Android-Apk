package com.vyomi.club.ui.screens.auth

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vyomi.club.data.model.UserRole
import com.vyomi.club.ui.components.VyomiPrimaryButton
import com.vyomi.club.ui.components.VyomiTextField
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
fun SignInScreen(
    onSignInSuccess: (UserRole) -> Unit
) {
    var identifier by remember { mutableStateOf("FD-PRIYA-04") }
    var password by remember { mutableStateOf("••••••••••••") }
    var showPassword by remember { mutableStateOf(false) }
    var selectedRole by remember { mutableStateOf(UserRole.FRONT_DESK) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(Dimens.SpacingStandard)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(SurfaceElevated, RoundedCornerShape(8.dp))
                            .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("V", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent)
                    }
                    Text(
                        text = "VYOMI",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp,
                        color = TextPrimary,
                        modifier = Modifier.padding(start = Dimens.SpacingSmall)
                    )
                }
                Box(
                    modifier = Modifier
                        .background(SurfaceElevated, RoundedCornerShape(Dimens.CornerRadiusSmall))
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusSmall))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("Staff Terminal", fontSize = 11.sp, color = TextSecondary)
                }
            }

            Text(
                text = "Staff Sign-In",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Text(
                text = "Enter your operational credentials to access your designated role portal.",
                fontSize = 13.sp,
                color = TextSecondary,
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
            )

            // Credentials Fields
            VyomiTextField(
                value = identifier,
                onValueChange = { identifier = it },
                label = "Staff Identifier or Mobile",
                placeholder = "e.g. FD-PRIYA-04"
            )

            Spacer(modifier = Modifier.height(Dimens.SpacingMedium))

            VyomiTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Text(
                        text = if (showPassword) "Hide" else "Show",
                        fontSize = 12.sp,
                        color = TextSecondary,
                        modifier = Modifier
                            .clickable { showPassword = !showPassword }
                            .padding(end = 12.dp)
                    )
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Role Simulator Selector (For prototype review)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceElevated, RoundedCornerShape(Dimens.CornerRadiusMedium))
                    .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusMedium))
                    .padding(Dimens.SpacingMedium)
            ) {
                Text(
                    text = "⚡ Prototype Role Switcher:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = ChampagneAccent
                )
                Text(
                    text = "Select account role for design review:",
                    fontSize = 11.sp,
                    color = TextMuted,
                    modifier = Modifier.padding(top = 2.dp, bottom = 8.dp)
                )

                UserRole.entries.forEach { role ->
                    val isSelected = selectedRole == role
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .background(
                                if (isSelected) SurfaceCard else DarkBackground,
                                RoundedCornerShape(Dimens.CornerRadiusSmall)
                            )
                            .border(
                                Dimens.BorderWidthStandard,
                                if (isSelected) ChampagneAccent else SurfaceBorder,
                                RoundedCornerShape(Dimens.CornerRadiusSmall)
                            )
                            .clickable { selectedRole = role }
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = role.label,
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                            color = if (isSelected) TextPrimary else TextSecondary
                        )
                        if (isSelected) {
                            Text("● Active", fontSize = 11.sp, color = ChampagneAccent)
                        }
                    }
                }
            }
        }

        Column(modifier = Modifier.padding(top = 24.dp)) {
            VyomiPrimaryButton(
                text = "Sign In as ${selectedRole.label}",
                onClick = { onSignInSuccess(selectedRole) }
            )
            Text(
                text = "Protected under Vyomi Operational Access Control • v1.2.0",
                fontSize = 11.sp,
                color = TextMuted,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = Dimens.SpacingMedium)
            )
        }
    }
}

