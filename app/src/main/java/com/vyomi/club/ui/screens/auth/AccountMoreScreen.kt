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
import androidx.compose.foundation.shape.CircleShape
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
import com.vyomi.club.data.model.UserRole
import com.vyomi.club.ui.components.VyomiDestructiveButton
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
fun AccountMoreScreen(
    currentRole: UserRole,
    onNavigateToProposed: () -> Unit,
    onSignOut: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
    ) {
        VyomiTopAppBar(title = "Account & Facility")

        Column(modifier = Modifier.padding(Dimens.SpacingStandard)) {
            // Profile Card
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
                        .size(48.dp)
                        .background(SurfaceCard, CircleShape)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "PP",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = ChampagneAccent
                    )
                }

                Column(modifier = Modifier.padding(start = Dimens.SpacingMedium)) {
                    Text(
                        text = "Priya Patel",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                    Text(
                        text = currentRole.label,
                        fontSize = 13.sp,
                        color = TextSecondary,
                        modifier = Modifier.padding(top = 1.dp)
                    )
                    Text(
                        text = "● Terminal Online",
                        fontSize = 11.sp,
                        color = StatusSuccess,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(Dimens.SpacingStandard))

            Text(
                text = "SYSTEM SETTINGS",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextSecondary,
                modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
            )

            // Menu Items Container
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(Dimens.CornerRadiusLarge))
                    .background(SurfaceElevated)
                    .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusLarge))
            ) {
                MoreMenuItem(
                    title = "Proposed Features & Extension Specs",
                    subtitle = "Renewal, freeze, payout tracking, enquiry sync",
                    badge = "Proposed",
                    onClick = onNavigateToProposed
                )
                Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(SurfaceBorder))
                MoreMenuItem(
                    title = "Audit & Tamper-Evident History",
                    subtitle = "Review timestamped operational events"
                )
                Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(SurfaceBorder))
                MoreMenuItem(
                    title = "Terminal Support & Documentation",
                    subtitle = "Facility desk emergency hotline: 1800-VYOMI"
                )
            }

            Spacer(modifier = Modifier.height(Dimens.SpacingXXLarge))

            VyomiDestructiveButton(
                text = "Sign Out of Terminal",
                onClick = onSignOut
            )

            Text(
                text = "Vyomi Club Management • Android v1.2.0-prod",
                fontSize = 11.sp,
                color = TextMuted,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = Dimens.SpacingMedium)
            )
        }
    }
}

@Composable
private fun MoreMenuItem(
    title: String,
    subtitle: String,
    badge: String? = null,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
            .padding(horizontal = Dimens.SpacingStandard, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextPrimary
                )
                if (badge != null) {
                    Box(
                        modifier = Modifier
                            .padding(start = 6.dp)
                            .background(SurfaceCard, RoundedCornerShape(4.dp))
                            .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(text = badge, fontSize = 10.sp, color = ChampagneAccent)
                    }
                }
            }
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = TextSecondary,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
        Text(text = "➔", fontSize = 14.sp, color = TextMuted)
    }
}

