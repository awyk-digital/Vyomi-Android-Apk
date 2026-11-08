package com.vyomi.club.ui.screens.mastertrainer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vyomi.club.data.model.Trainer
import com.vyomi.club.ui.components.VyomiTopAppBar
import com.vyomi.club.ui.components.VyomiTrainerRowCard
import com.vyomi.club.ui.theme.DarkBackground
import com.vyomi.club.ui.theme.Dimens

@Composable
fun TrainerDirectoryScreen(
    onTrainerClick: (String) -> Unit,
    onBackClick: (() -> Unit)? = null
) {
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
    ) {
        VyomiTopAppBar(
            title = "Trainer Roster",
            subtitle = "12 Active",
            onBackClick = onBackClick
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.SpacingStandard, vertical = Dimens.SpacingSmall),
            verticalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall)
        ) {
            items(trainers) { trainer ->
                VyomiTrainerRowCard(
                    trainer = trainer,
                    onClick = { onTrainerClick(trainer.id) }
                )
            }
        }
    }
}

