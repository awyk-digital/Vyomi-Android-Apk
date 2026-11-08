package com.vyomi.club.ui.screens.proposed

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
import com.vyomi.club.ui.components.VyomiTopAppBar
import com.vyomi.club.ui.theme.ChampagneAccent
import com.vyomi.club.ui.theme.DarkBackground
import com.vyomi.club.ui.theme.Dimens
import com.vyomi.club.ui.theme.StatusPending
import com.vyomi.club.ui.theme.StatusPendingBorder
import com.vyomi.club.ui.theme.StatusPendingContainer
import com.vyomi.club.ui.theme.SurfaceBorder
import com.vyomi.club.ui.theme.SurfaceCard
import com.vyomi.club.ui.theme.SurfaceElevated
import com.vyomi.club.ui.theme.TextMuted
import com.vyomi.club.ui.theme.TextPrimary
import com.vyomi.club.ui.theme.TextSecondary

data class ProposedFeatureItem(
    val title: String,
    val subtitle: String,
    val scope: String,
    val statusBadge: String = "Proposed — Requires Confirmation"
)

@Composable
fun ProposedFeaturesScreen(
    onBackClick: () -> Unit
) {
    val proposals = listOf(
        ProposedFeatureItem(
            title = "1. Membership Renewal and Freeze Flows",
            subtitle = "Enable front-desk staff to extend expiring memberships or freeze valid days for travel/medical reasons.",
            scope = "Requires confirmation on freeze day caps, fee waivers, and impact on trainer active client counts."
        ),
        ProposedFeatureItem(
            title = "2. Trainer Payout Tracking",
            subtitle = "Track actual bank disbursements made to trainers against accrued revenue share liabilities.",
            scope = "Requires confirmation on bank API integration, payment schedules (e.g. 5th of each month), and tax deductions."
        ),
        ProposedFeatureItem(
            title = "3. Refund and Partial-Payment Management",
            subtitle = "Manage 2-stage installment payments and clawback of trainer revenue shares upon client cancellation.",
            scope = "Requires business policy on non-refundable registration fees and revenue share clawback limits."
        ),
        ProposedFeatureItem(
            title = "4. Website Trial-Enquiry Import",
            subtitle = "Automatically ingest VIP trial workout requests from the public Vyomi website into the front-desk queue.",
            scope = "Requires webhook or API integration with the web host and lead qualification rules."
        ),
        ProposedFeatureItem(
            title = "5. Report Export (CSV / PDF)",
            subtitle = "Export monthly trainer compensation statements and session verification audit trails for accounting.",
            scope = "Requires confirmation on template layout, accounting software compatibility, and signed auditor certificates."
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        VyomiTopAppBar(
            title = "Proposed Specifications",
            subtitle = "Unapproved Scope",
            onBackClick = onBackClick
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.SpacingStandard, vertical = Dimens.SpacingSmall),
            verticalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(Dimens.CornerRadiusSmall))
                        .background(StatusPendingContainer)
                        .border(Dimens.BorderWidthStandard, StatusPendingBorder, RoundedCornerShape(Dimens.CornerRadiusSmall))
                        .padding(12.dp)
                ) {
                    Text(
                        text = "Notice: The features below are proposed design extensions kept separate from the approved core flow. They must receive business owner sign-off prior to engineering rollout.",
                        fontSize = 11.sp,
                        color = StatusPending,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            items(proposals) { item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(Dimens.CornerRadiusMedium))
                        .background(SurfaceElevated)
                        .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(Dimens.CornerRadiusMedium))
                        .padding(Dimens.SpacingMedium)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(item.title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                        Box(
                            modifier = Modifier
                                .background(SurfaceCard, RoundedCornerShape(4.dp))
                                .border(Dimens.BorderWidthStandard, SurfaceBorder, RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text("Proposed", fontSize = 10.sp, color = ChampagneAccent)
                        }
                    }
                    Text(item.subtitle, fontSize = 12.sp, color = TextSecondary, modifier = Modifier.padding(top = 4.dp))
                    Text(
                        text = "Dependency: ${item.scope}",
                        fontSize = 11.sp,
                        color = TextMuted,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
            }
        }
    }
}

