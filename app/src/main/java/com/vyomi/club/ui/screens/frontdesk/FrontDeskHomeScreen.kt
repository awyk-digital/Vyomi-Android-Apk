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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vyomi.club.data.model.Client
import com.vyomi.club.ui.components.VyomiClientRowCard
import com.vyomi.club.ui.components.VyomiStatCard
import com.vyomi.club.ui.components.VyomiTopAppBar
import com.vyomi.club.ui.theme.ActionIvory
import com.vyomi.club.ui.theme.ActionIvoryText
import com.vyomi.club.ui.theme.ChampagneAccent
import com.vyomi.club.ui.theme.DarkBackground
import com.vyomi.club.ui.theme.Dimens
import com.vyomi.club.ui.theme.SurfaceBorder
import com.vyomi.club.ui.theme.SurfaceElevated
import com.vyomi.club.ui.theme.TextMuted
import com.vyomi.club.ui.theme.TextPrimary
import com.vyomi.club.ui.theme.TextSecondary

@Composable
fun FrontDeskHomeScreen(
    onNavigateToRegistration: () -> Unit,
    onNavigateToDirectory: () -> Unit,
    onClientClick: (String) -> Unit
) {
    val sampleRecentClients = listOf(
        Client(
            id = "VY-2026-089",
            name = "Dev Sharma",
            phone = "+91 98765 •••••",
            rawPhone = "9876543210",
            membershipTier = "Quarterly Gold",
            membershipDuration = "3 Months",
            agreedPrice = 18000.0,
            startDate = "08 Nov 2026",
            endDate = "07 Feb 2027",
            isPhoneVerified = true,
            assignedTrainerName = "Kabir Roy",
            registeredBy = "Priya Patel",
            registeredAt = "10:14 AM"
        ),
        Client(
            id = "VY-2026-088",
            name = "Ananya Kapoor",
            phone = "+91 98201 •••••",
            rawPhone = "9820123456",
            membershipTier = "Annual Platinum",
            membershipDuration = "12 Months",
            agreedPrice = 60000.0,
            startDate = "07 Nov 2026",
            endDate = "06 Nov 2027",
            isPhoneVerified = true,
            assignedTrainerName = "Sneha Rao",
            registeredBy = "Priya Patel",
            registeredAt = "09:30 AM"
        ),
        Client(
            id = "VY-2026-087",
            name = "Rishi Mehta",
            phone = "+91 97114 •••••",
            rawPhone = "9711456789",
            membershipTier = "Monthly Core",
            membershipDuration = "1 Month",
            agreedPrice = 7500.0,
            startDate = "07 Nov 2026",
            endDate = "06 Dec 2026",
            isPhoneVerified = false,
            assignedTrainerName = null,
            registeredBy = "Priya Patel",
            registeredAt = "08:45 AM"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
    ) {
        VyomiTopAppBar(
            title = "Vyomi Front Desk",
            subtitle = "Desk 01",
            trailingContent = {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(SurfaceElevated, CircleShape)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("FD", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ChampagneAccent)
                }
            }
        )

        Column(modifier = Modifier.padding(Dimens.SpacingStandard)) {
            // Compact greeting
            Text(
                text = "Wednesday, 08 Nov 2026",
                fontSize = 12.sp,
                color = TextSecondary
            )
            Text(
                text = "Good Morning, Priya",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                modifier = Modifier.padding(top = 2.dp, bottom = 16.dp)
            )

            // Primary Registration Action (Ivory surface, dark text, >= 48dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(Dimens.CornerRadiusLarge))
                    .background(ActionIvory)
                    .clickable { onNavigateToRegistration() }
                    .padding(horizontal = Dimens.SpacingStandard, vertical = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(DarkBackground, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("+", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = ActionIvory)
                    }
                    Column(modifier = Modifier.padding(start = Dimens.SpacingMedium)) {
                        Text(
                            text = "Register New Client",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = ActionIvoryText
                        )
                        Text(
                            text = "3-step photo capture & OTP enrollment",
                            fontSize = 12.sp,
                            color = ActionIvoryText.copy(alpha = 0.7f)
                        )
                    }
                }
                Text("➔", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = ActionIvoryText)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Summary Metrics
            Text(
                text = "TODAY'S OPERATIONS",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextSecondary,
                modifier = Modifier.padding(start = 2.dp, bottom = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall)
            ) {
                VyomiStatCard(value = "4", label = "Registered Today", modifier = Modifier.weight(1f))
                VyomiStatCard(value = "182", label = "Active Members", highlight = true, modifier = Modifier.weight(1f))
                VyomiStatCard(value = "2", label = "Pending OTP", modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Recent Registrations Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "RECENT REGISTRATIONS",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextSecondary
                )
                Text(
                    text = "View All (182)",
                    fontSize = 12.sp,
                    color = ChampagneAccent,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable { onNavigateToDirectory() }
                )
            }

            // Client Cards
            sampleRecentClients.forEach { client ->
                VyomiClientRowCard(
                    client = client,
                    onClick = { onClientClick(client.id) },
                    modifier = Modifier.padding(bottom = Dimens.SpacingSmall)
                )
            }
        }
    }
}

