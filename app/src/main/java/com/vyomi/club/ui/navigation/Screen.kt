package com.vyomi.club.ui.navigation

sealed class Screen(val route: String) {
    // Auth & Global
    data object Launch : Screen("launch")
    data object SignIn : Screen("sign_in")
    data object AccountMore : Screen("account_more")

    // Front Desk Flow
    data object FrontDeskHome : Screen("fd_home")
    data object ClientDirectory : Screen("client_directory")
    data object ClientRegistration : Screen("client_registration")
    data object RegistrationSuccess : Screen("registration_success")
    data object ClientDetail : Screen("client_detail")

    // Master Trainer Flow
    data object MasterTrainerHome : Screen("mt_home")
    data object AssignmentQueue : Screen("assignment_queue")
    data object AssignTrainer : Screen("assign_trainer")
    data object TrainerDirectory : Screen("trainer_directory")

    // Trainer Flow
    data object TrainerHome : Screen("tr_home")
    data object MyClients : Screen("my_clients")
    data object SessionVerification : Screen("session_verification")
    data object SessionHistory : Screen("session_history")

    // Payments Flow
    data object PaymentList : Screen("payments_list")
    data object RecordPayment : Screen("record_payment")
    data object PaymentDetail : Screen("payment_detail")

    // Owner / Administrator Flow
    data object OwnerOverview : Screen("owner_overview")
    data object TrainerEarnings : Screen("trainer_earnings")
    data object TrainerEarningsDetail : Screen("trainer_earnings_detail")
    data object SixMonthReview : Screen("six_month_review")
    data object PercentageHistory : Screen("percentage_history")
    data object StaffGovernance : Screen("staff_governance")
    data object MembershipCatalogue : Screen("membership_catalogue")
    data object AuditActivity : Screen("audit_activity")

    // Proposed Features
    data object ProposedFeatures : Screen("proposed_features")
}

