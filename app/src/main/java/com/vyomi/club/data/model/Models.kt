package com.vyomi.club.data.model

enum class UserRole(val label: String) {
    FRONT_DESK("Front Desk"),
    MASTER_TRAINER("Master Trainer"),
    TRAINER("Trainer"),
    OWNER_ADMIN("Owner / Administrator")
}

data class StaffUser(
    val id: String,
    val name: String,
    val role: UserRole,
    val terminalId: String,
    val isActive: Boolean = true
)

data class Client(
    val id: String,
    val name: String,
    val phone: String, // Masked representation: +91 98765 •••••
    val rawPhone: String,
    val email: String? = null,
    val photoUrl: String? = null,
    val membershipTier: String,
    val membershipDuration: String,
    val agreedPrice: Double,
    val startDate: String,
    val endDate: String,
    val isPhoneVerified: Boolean,
    val assignedTrainerId: String? = null,
    val assignedTrainerName: String? = null,
    val registeredBy: String,
    val registeredAt: String
)

data class Trainer(
    val id: String,
    val name: String,
    val status: String = "Active",
    val specialization: String? = null,
    val assignedClientCount: Int,
    val verifiedSessionCount: Int,
    val appliedSharePercent: Double = 50.0 // Baseline 50%
)

data class SessionRecord(
    val sessionId: String,
    val clientId: String,
    val clientName: String,
    val trainerId: String,
    val trainerName: String,
    val sessionDate: String,
    val verifiedAt: String,
    val isLocked: Boolean = true
)

data class PaymentRecord(
    val paymentId: String,
    val clientId: String,
    val clientName: String,
    val trainerId: String,
    val trainerName: String,
    val collectedAmount: Double,
    val paymentDate: String,
    val paymentMode: String, // UPI, Cash, Card, Bank Transfer
    val transactionRef: String,
    val trainerSharePercent: Double = 50.0, // Snapshot
    val trainerShareAmount: Double,
    val recordedBy: String,
    val isConfirmed: Boolean = true
)

data class IncentiveReview(
    val reviewId: String,
    val trainerId: String,
    val trainerName: String,
    val cyclePeriod: String,
    val eligibleCollections: Double,
    val verifiedSessionsCount: Int,
    val accruedShare: Double,
    val currentPercent: Double = 50.0,
    val proposedPercent: Double = 55.0,
    val effectiveDate: String,
    val reviewNotes: String,
    val isApproved: Boolean = false
)

enum class VyomiStatusType {
    VERIFIED,
    PENDING_OTP,
    UNASSIGNED,
    OVERDUE,
    LOCKED,
    ACTIVE
}

