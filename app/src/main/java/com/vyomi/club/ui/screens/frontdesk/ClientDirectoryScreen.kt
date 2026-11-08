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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vyomi.club.data.model.Client
import com.vyomi.club.ui.components.VyomiClientRowCard
import com.vyomi.club.ui.components.VyomiTextField
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
fun ClientDirectoryScreen(
    onClientClick: (String) -> Unit,
    onNavigateToRegistration: () -> Unit,
    onBackClick: (() -> Unit)? = null
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("All") }

    val sampleClients = listOf(
        Client("VY-2026-089", "Dev Sharma", "+91 98765 •••••", "9876543210", "dev@example.com", null, "Quarterly Gold", "3 Months", 18000.0, "08 Nov 2026", "07 Feb 2027", true, "TR-01", "Kabir Roy", "Priya Patel", "Today"),
        Client("VY-2026-088", "Ananya Kapoor", "+91 98201 •••••", "9820123456", null, null, "Annual Platinum", "12 Months", 60000.0, "07 Nov 2026", "06 Nov 2027", true, "TR-02", "Sneha Rao", "Priya Patel", "Yesterday"),
        Client("VY-2026-087", "Rishi Mehta", "+91 97114 •••••", "9711456789", null, null, "Monthly Core", "1 Month", 7500.0, "07 Nov 2026", "06 Dec 2026", false, null, null, "Priya Patel", "Yesterday"),
        Client("VY-2026-086", "Pooja Hegde", "+91 99230 •••••", "9923011223", null, null, "Half-Yearly Elite", "6 Months", 32000.0, "06 Nov 2026", "05 May 2027", true, "TR-03", "Vikram Malhotra", "Priya Patel", "06 Nov"),
        Client("VY-2026-085", "Karan Singhania", "+91 98331 •••••", "9833199887", null, null, "Quarterly Gold", "3 Months", 18000.0, "05 Nov 2026", "04 Feb 2027", true, "TR-01", "Kabir Roy", "Priya Patel", "05 Nov")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        VyomiTopAppBar(
            title = "Client Directory",
            onBackClick = onBackClick,
            trailingContent = {
                Box(
                    modifier = Modifier
                        .background(ActionIvory, RoundedCornerShape(Dimens.CornerRadiusSmall))
                        .clickable { onNavigateToRegistration() }
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text("+ New Client", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ActionIvoryText)
                }
            }
        )

        Column(modifier = Modifier.padding(horizontal = Dimens.SpacingStandard, vertical = Dimens.SpacingSmall)) {
            // Search Field
            VyomiTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = "Search by Name or Registered Phone",
                placeholder = "e.g. Dev Sharma or 98765"
            )

            Spacer(modifier = Modifier.height(Dimens.SpacingSmall))

            // Filter Pills
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("All (182)", "Active (168)", "Pending OTP (2)").forEach { filter ->
                    val isSelected = selectedFilter == filter.substringBefore(" ")
                    Box(
                        modifier = Modifier
                            .background(
                                if (isSelected) ActionIvory else SurfaceElevated,
                                RoundedCornerShape(Dimens.CornerRadiusFull)
                            )
                            .border(
                                Dimens.BorderWidthStandard,
                                if (isSelected) ActionIvory else SurfaceBorder,
                                RoundedCornerShape(Dimens.CornerRadiusFull)
                            )
                            .clickable { selectedFilter = filter.substringBefore(" ") }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = filter,
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) ActionIvoryText else TextSecondary
                        )
                    }
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.SpacingStandard, vertical = Dimens.SpacingSmall),
            verticalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall)
        ) {
            items(sampleClients) { client ->
                VyomiClientRowCard(
                    client = client,
                    onClick = { onClientClick(client.id) }
                )
            }
        }
    }
}

