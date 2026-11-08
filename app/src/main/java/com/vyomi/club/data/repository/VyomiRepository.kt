package com.vyomi.club.data.repository

import com.vyomi.club.data.model.Client
import com.vyomi.club.data.model.IncentiveReview
import com.vyomi.club.data.model.PaymentRecord
import com.vyomi.club.data.model.SessionRecord
import com.vyomi.club.data.model.StaffUser
import com.vyomi.club.data.model.Trainer
import com.vyomi.club.data.model.UserRole
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VyomiRepository private constructor() {

    // Initial Fictional Seed Data with Strict Internal Mathematical Consistency
    private val _clients = MutableStateFlow(
        listOf(
            Client(
                id = "VY-2026-089",
                name = "Dev Sharma",
                phone = "+91 98765 •••••",
                rawPhone = "9876543210",
                email = "dev.sharma@example.com",
                photoUrl = null,
                membershipTier = "Quarterly Gold",
                membershipDuration = "3 Months",
                agreedPrice = 18000.0,
                startDate = "08 Nov 2026",
                endDate = "07 Feb 2027",
                isPhoneVerified = true,
                assignedTrainerId = "TR-01",
                assignedTrainerName = "Kabir Roy",
                registeredBy = "Priya Patel",
                registeredAt = "10:14 AM"
            ),
            Client(
                id = "VY-2026-088",
                name = "Ananya Kapoor",
                phone = "+91 98201 •••••",
                rawPhone = "9820123456",
                email = null,
                photoUrl = null,
                membershipTier = "Annual Platinum",
                membershipDuration = "12 Months",
                agreedPrice = 60000.0,
                startDate = "07 Nov 2026",
                endDate = "06 Nov 2027",
                isPhoneVerified = true,
                assignedTrainerId = "TR-02",
                assignedTrainerName = "Sneha Rao",
                registeredBy = "Priya Patel",
                registeredAt = "Yesterday"
            ),
            Client(
                id = "VY-2026-087",
                name = "Rishi Mehta",
                phone = "+91 97114 •••••",
                rawPhone = "9711456789",
                email = null,
                photoUrl = null,
                membershipTier = "Monthly Core",
                membershipDuration = "1 Month",
                agreedPrice = 7500.0,
                startDate = "07 Nov 2026",
                endDate = "06 Dec 2026",
                isPhoneVerified = false,
                assignedTrainerId = null,
                assignedTrainerName = null,
                registeredBy = "Priya Patel",
                registeredAt = "Yesterday"
            ),
            Client(
                id = "VY-2026-086",
                name = "Pooja Hegde",
                phone = "+91 99230 •••••",
                rawPhone = "9923011223",
                email = null,
                photoUrl = null,
                membershipTier = "Half-Yearly Elite",
                membershipDuration = "6 Months",
                agreedPrice = 32000.0,
                startDate = "06 Nov 2026",
                endDate = "05 May 2027",
                isPhoneVerified = true,
                assignedTrainerId = "TR-03",
                assignedTrainerName = "Vikram Malhotra",
                registeredBy = "Priya Patel",
                registeredAt = "06 Nov"
            ),
            Client(
                id = "VY-2026-085",
                name = "Karan Singhania",
                phone = "+91 98331 •••••",
                rawPhone = "9833199887",
                email = null,
                photoUrl = null,
                membershipTier = "Quarterly Gold",
                membershipDuration = "3 Months",
                agreedPrice = 18000.0,
                startDate = "05 Nov 2026",
                endDate = "04 Feb 2027",
                isPhoneVerified = true,
                assignedTrainerId = "TR-01",
                assignedTrainerName = "Kabir Roy",
                registeredBy = "Priya Patel",
                registeredAt = "05 Nov"
            )
        )
    )
    val clients: StateFlow<List<Client>> = _clients.asStateFlow()

    private val _trainers = MutableStateFlow(
        listOf(
            Trainer("TR-01", "Kabir Roy", "Active", "Strength & Hypertrophy", 8, 142, 50.0),
            Trainer("TR-02", "Sneha Rao", "Active", "Functional & HIIT", 6, 98, 50.0),
            Trainer("TR-03", "Vikram Malhotra", "Active", "Athletic Conditioning", 12, 210, 50.0),
            Trainer("TR-04", "Aditi Nair", "Active", "Mobility & Core", 5, 76, 50.0)
        )
    )
    val trainers: StateFlow<List<Trainer>> = _trainers.asStateFlow()

    private val _payments = MutableStateFlow(
        listOf(
            PaymentRecord("PAY-9921", "VY-2026-089", "Dev Sharma", "TR-01", "Kabir Roy", 18000.0, "08 Nov 2026", "UPI", "UPI/6120984", 50.0, 9000.0, "Priya Patel", true),
            PaymentRecord("PAY-9920", "VY-2026-088", "Ananya Kapoor", "TR-02", "Sneha Rao", 60000.0, "07 Nov 2026", "Bank Transfer", "NEFT/890123", 50.0, 30000.0, "Priya Patel", true),
            PaymentRecord("PAY-9919", "VY-2026-086", "Pooja Hegde", "TR-03", "Vikram Malhotra", 32000.0, "06 Nov 2026", "Credit Card", "TXN/44123", 50.0, 16000.0, "Priya Patel", true),
            PaymentRecord("PAY-9918", "VY-2026-085", "Karan Singhania", "TR-01", "Kabir Roy", 18000.0, "05 Nov 2026", "UPI", "UPI/554901", 50.0, 9000.0, "Priya Patel", true)
        )
    )
    val payments: StateFlow<List<PaymentRecord>> = _payments.asStateFlow()

    private val _sessions = MutableStateFlow(
        listOf(
            SessionRecord("SES-8901", "VY-2026-089", "Dev Sharma", "TR-01", "Kabir Roy", "08 Nov 2026", "11:15 AM", true),
            SessionRecord("SES-8900", "VY-2026-079", "Sahil Kapoor", "TR-01", "Kabir Roy", "08 Nov 2026", "09:30 AM", true),
            SessionRecord("SES-8899", "VY-2026-085", "Karan Singhania", "TR-01", "Kabir Roy", "08 Nov 2026", "07:45 AM", true),
            SessionRecord("SES-8895", "VY-2026-072", "Tarun Varma", "TR-01", "Kabir Roy", "07 Nov 2026", "06:10 PM", true)
        )
    )
    val sessions: StateFlow<List<SessionRecord>> = _sessions.asStateFlow()

    private val _sixMonthReview = MutableStateFlow(
        IncentiveReview(
            reviewId = "REV-2026-H2",
            trainerId = "TR-01",
            trainerName = "Kabir Roy",
            cyclePeriod = "01 Jun 2026 – 30 Nov 2026",
            eligibleCollections = 540000.0,
            verifiedSessionsCount = 312,
            accruedShare = 270000.0,
            currentPercent = 50.0,
            proposedPercent = 55.0,
            effectiveDate = "01 December 2026",
            reviewNotes = "Consistently high client retention and 100% verified attendance punctuality. Approved +5% incentive hike for next half-year.",
            isApproved = false
        )
    )
    val sixMonthReview: StateFlow<IncentiveReview> = _sixMonthReview.asStateFlow()

    // Operational Actions
    fun registerNewClient(client: Client) {
        _clients.update { listOf(client) + it }
    }

    fun assignTrainerToClient(clientId: String, trainerId: String, trainerName: String) {
        _clients.update { list ->
            list.map {
                if (it.id == clientId) it.copy(assignedTrainerId = trainerId, assignedTrainerName = trainerName)
                else it
            }
        }
        _trainers.update { list ->
            list.map {
                if (it.id == trainerId) it.copy(assignedClientCount = it.assignedClientCount + 1)
                else it
            }
        }
    }

    fun recordDailyAttendanceSession(session: SessionRecord) {
        _sessions.update { listOf(session) + it }
    }

    fun recordClientPayment(payment: PaymentRecord) {
        _payments.update { listOf(payment) + it }
    }

    fun approveIncentiveReview() {
        _sixMonthReview.update { it.copy(isApproved = true) }
    }

    companion object {
        val instance by lazy { VyomiRepository() }
    }
}

