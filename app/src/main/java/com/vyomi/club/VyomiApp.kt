package com.vyomi.club

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.vyomi.club.data.model.UserRole
import com.vyomi.club.ui.components.VyomiBottomNavigationBar
import com.vyomi.club.ui.navigation.Screen
import com.vyomi.club.ui.screens.auth.AccountMoreScreen
import com.vyomi.club.ui.screens.auth.LaunchScreen
import com.vyomi.club.ui.screens.auth.SignInScreen
import com.vyomi.club.ui.screens.frontdesk.ClientDetailScreen
import com.vyomi.club.ui.screens.frontdesk.ClientDirectoryScreen
import com.vyomi.club.ui.screens.frontdesk.ClientRegistrationScreen
import com.vyomi.club.ui.screens.frontdesk.FrontDeskHomeScreen
import com.vyomi.club.ui.screens.mastertrainer.AssignTrainerScreen
import com.vyomi.club.ui.screens.mastertrainer.AssignmentQueueScreen
import com.vyomi.club.ui.screens.mastertrainer.MasterTrainerHomeScreen
import com.vyomi.club.ui.screens.mastertrainer.TrainerDirectoryScreen
import com.vyomi.club.ui.screens.owner.AuditTrailScreen
import com.vyomi.club.ui.screens.owner.MembershipConfigScreen
import com.vyomi.club.ui.screens.owner.OwnerOverviewScreen
import com.vyomi.club.ui.screens.owner.SixMonthReviewScreen
import com.vyomi.club.ui.screens.owner.StaffGovernanceScreen
import com.vyomi.club.ui.screens.owner.TrainerEarningsScreen
import com.vyomi.club.ui.screens.payments.PaymentDetailScreen
import com.vyomi.club.ui.screens.payments.PaymentListScreen
import com.vyomi.club.ui.screens.payments.RecordPaymentScreen
import com.vyomi.club.ui.screens.proposed.ProposedFeaturesScreen
import com.vyomi.club.ui.screens.trainer.SessionHistoryScreen
import com.vyomi.club.ui.screens.trainer.SessionVerificationScreen
import com.vyomi.club.ui.screens.trainer.TrainerHomeScreen
import com.vyomi.club.ui.screens.trainer.TrainerMyClientsScreen
import com.vyomi.club.ui.theme.DarkBackground
import com.vyomi.club.ui.theme.VyomiTheme

@Composable
fun VyomiApp() {
    VyomiTheme {
        var currentRole by remember { mutableStateOf(UserRole.FRONT_DESK) }
        var currentScreen by remember { mutableStateOf<String>(Screen.FrontDeskHome.route) }
        var selectedClientId by remember { mutableStateOf("VY-2026-089") }
        var selectedPaymentId by remember { mutableStateOf("PAY-9921") }

        val isAuthScreen = currentScreen == Screen.Launch.route || currentScreen == Screen.SignIn.route
        val isRegistrationWizard = currentScreen == Screen.ClientRegistration.route
        val isSessionVerification = currentScreen == Screen.SessionVerification.route
        val isProposedScreen = currentScreen == Screen.ProposedFeatures.route

        val showBottomNav = !isAuthScreen && !isRegistrationWizard && !isSessionVerification && !isProposedScreen

        Scaffold(
            bottomBar = {
                if (showBottomNav) {
                    VyomiBottomNavigationBar(
                        role = currentRole,
                        currentRoute = currentScreen,
                        onTabSelected = { currentScreen = it }
                    )
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkBackground)
                    .padding(paddingValues)
            ) {
                when (currentScreen) {
                    Screen.Launch.route -> {
                        LaunchScreen(
                            onNavigateToSignIn = { currentScreen = Screen.SignIn.route }
                        )
                    }
                    Screen.SignIn.route -> {
                        SignInScreen(
                            onSignInSuccess = { role ->
                                currentRole = role
                                currentScreen = when (role) {
                                    UserRole.FRONT_DESK -> Screen.FrontDeskHome.route
                                    UserRole.MASTER_TRAINER -> Screen.MasterTrainerHome.route
                                    UserRole.TRAINER -> Screen.TrainerHome.route
                                    UserRole.OWNER_ADMIN -> Screen.OwnerOverview.route
                                }
                            }
                        )
                    }
                    Screen.AccountMore.route -> {
                        AccountMoreScreen(
                            currentRole = currentRole,
                            onNavigateToProposed = { currentScreen = Screen.ProposedFeatures.route },
                            onSignOut = { currentScreen = Screen.SignIn.route }
                        )
                    }

                    // FRONT DESK
                    Screen.FrontDeskHome.route -> {
                        FrontDeskHomeScreen(
                            onNavigateToRegistration = { currentScreen = Screen.ClientRegistration.route },
                            onNavigateToDirectory = { currentScreen = Screen.ClientDirectory.route },
                            onClientClick = { id ->
                                selectedClientId = id
                                currentScreen = Screen.ClientDetail.route
                            }
                        )
                    }
                    Screen.ClientDirectory.route -> {
                        ClientDirectoryScreen(
                            onClientClick = { id ->
                                selectedClientId = id
                                currentScreen = Screen.ClientDetail.route
                            },
                            onNavigateToRegistration = { currentScreen = Screen.ClientRegistration.route },
                            onBackClick = { currentScreen = Screen.FrontDeskHome.route }
                        )
                    }
                    Screen.ClientRegistration.route -> {
                        ClientRegistrationScreen(
                            onNavigateBack = { currentScreen = Screen.FrontDeskHome.route },
                            onRegistrationCompleted = { id ->
                                selectedClientId = id
                                currentScreen = Screen.ClientDetail.route
                            }
                        )
                    }
                    Screen.ClientDetail.route -> {
                        ClientDetailScreen(
                            clientId = selectedClientId,
                            onNavigateToRecordPayment = { currentScreen = Screen.RecordPayment.route },
                            onBackClick = { currentScreen = Screen.ClientDirectory.route }
                        )
                    }

                    // MASTER TRAINER
                    Screen.MasterTrainerHome.route -> {
                        MasterTrainerHomeScreen(
                            onNavigateToQueue = { currentScreen = Screen.AssignmentQueue.route },
                            onNavigateToTrainers = { currentScreen = Screen.TrainerDirectory.route }
                        )
                    }
                    Screen.AssignmentQueue.route -> {
                        AssignmentQueueScreen(
                            onAssignTrainerClick = { id ->
                                selectedClientId = id
                                currentScreen = Screen.AssignTrainer.route
                            },
                            onBackClick = { currentScreen = Screen.MasterTrainerHome.route }
                        )
                    }
                    Screen.AssignTrainer.route -> {
                        AssignTrainerScreen(
                            clientId = selectedClientId,
                            onConfirmAssignment = { _ ->
                                currentScreen = Screen.AssignmentQueue.route
                            },
                            onBackClick = { currentScreen = Screen.AssignmentQueue.route }
                        )
                    }
                    Screen.TrainerDirectory.route -> {
                        TrainerDirectoryScreen(
                            onTrainerClick = { /* Trainer detail */ },
                            onBackClick = { currentScreen = Screen.MasterTrainerHome.route }
                        )
                    }

                    // TRAINER
                    Screen.TrainerHome.route -> {
                        TrainerHomeScreen(
                            onNavigateToVerifySession = { currentScreen = Screen.SessionVerification.route },
                            onNavigateToMyClients = { currentScreen = Screen.MyClients.route },
                            onNavigateToEarningsDetail = { currentScreen = Screen.TrainerEarningsDetail.route }
                        )
                    }
                    Screen.MyClients.route -> {
                        TrainerMyClientsScreen(
                            onVerifySessionClick = { _ ->
                                currentScreen = Screen.SessionVerification.route
                            },
                            onBackClick = { currentScreen = Screen.TrainerHome.route }
                        )
                    }
                    Screen.SessionVerification.route -> {
                        SessionVerificationScreen(
                            onNavigateBack = { currentScreen = Screen.TrainerHome.route },
                            onSessionVerifiedCompleted = { currentScreen = Screen.TrainerHome.route }
                        )
                    }
                    Screen.SessionHistory.route -> {
                        SessionHistoryScreen(
                            onBackClick = { currentScreen = Screen.TrainerHome.route }
                        )
                    }

                    // PAYMENTS
                    Screen.PaymentList.route -> {
                        PaymentListScreen(
                            onNavigateToRecordPayment = { currentScreen = Screen.RecordPayment.route },
                            onPaymentClick = { id ->
                                selectedPaymentId = id
                                currentScreen = Screen.PaymentDetail.route
                            },
                            onBackClick = { currentScreen = Screen.FrontDeskHome.route }
                        )
                    }
                    Screen.RecordPayment.route -> {
                        RecordPaymentScreen(
                            onPaymentStored = { id ->
                                selectedPaymentId = id
                                currentScreen = Screen.PaymentDetail.route
                            },
                            onBackClick = { currentScreen = Screen.PaymentList.route }
                        )
                    }
                    Screen.PaymentDetail.route -> {
                        PaymentDetailScreen(
                            paymentId = selectedPaymentId,
                            onBackClick = { currentScreen = Screen.PaymentList.route }
                        )
                    }

                    // OWNER / ADMIN
                    Screen.OwnerOverview.route -> {
                        OwnerOverviewScreen(
                            onNavigateToEarnings = { currentScreen = Screen.TrainerEarnings.route },
                            onNavigateToSixMonthReview = { currentScreen = Screen.SixMonthReview.route },
                            onNavigateToStaff = { currentScreen = Screen.StaffGovernance.route },
                            onNavigateToMemberships = { currentScreen = Screen.MembershipCatalogue.route },
                            onNavigateToAudit = { currentScreen = Screen.AuditActivity.route }
                        )
                    }
                    Screen.TrainerEarnings.route -> {
                        TrainerEarningsScreen(
                            onTrainerDetailClick = { _ -> currentScreen = Screen.TrainerEarningsDetail.route },
                            onBackClick = { currentScreen = Screen.OwnerOverview.route }
                        )
                    }
                    Screen.TrainerEarningsDetail.route -> {
                        PaymentDetailScreen(
                            paymentId = "PAY-9921",
                            onBackClick = { currentScreen = Screen.TrainerEarnings.route }
                        )
                    }
                    Screen.SixMonthReview.route -> {
                        SixMonthReviewScreen(
                            onApproveReview = { currentScreen = Screen.OwnerOverview.route },
                            onBackClick = { currentScreen = Screen.OwnerOverview.route }
                        )
                    }
                    Screen.StaffGovernance.route -> {
                        StaffGovernanceScreen(
                            onBackClick = { currentScreen = Screen.OwnerOverview.route }
                        )
                    }
                    Screen.MembershipCatalogue.route -> {
                        MembershipConfigScreen(
                            onBackClick = { currentScreen = Screen.OwnerOverview.route }
                        )
                    }
                    Screen.AuditActivity.route -> {
                        AuditTrailScreen(
                            onBackClick = { currentScreen = Screen.OwnerOverview.route }
                        )
                    }

                    // PROPOSED
                    Screen.ProposedFeatures.route -> {
                        ProposedFeaturesScreen(
                            onBackClick = { currentScreen = Screen.AccountMore.route }
                        )
                    }

                    else -> {
                        FrontDeskHomeScreen(
                            onNavigateToRegistration = { currentScreen = Screen.ClientRegistration.route },
                            onNavigateToDirectory = { currentScreen = Screen.ClientDirectory.route },
                            onClientClick = { currentScreen = Screen.ClientDetail.route }
                        )
                    }
                }
            }
        }
    }
}

