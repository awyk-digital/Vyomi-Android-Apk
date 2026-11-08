# Vyomi Club Management — Android Application

A luxury, staff-facing Android operational mobile application for **Vyomi Fitness Club**. Built with modern Android standards (**Jetpack Compose**, **Kotlin Coroutines / StateFlow**, **Material 3**) targeting Android 14+ (API 34, minSdk 26).

---

## 🏛️ Design System & Aesthetic

- **Design Philosophy:** Luxury, clean, practical, and highly polished dark aesthetic (*Obsidian Concierge*).
- **Background:** `#0B0B0C` (Obsidian canvas)
- **Elevated Surfaces:** `#171719`
- **Borders & Dividers:** `#303034` (Hairline subtle containment)
- **Primary Text:** `#F5F5F2`
- **Secondary / Supporting Text:** `#B8B8BF`
- **Primary CTAs:** Solid Ivory (`#F5F5F2`) containers with dark bold typography (`#0B0B0C`)
- **Accents:** Restrained Champagne Gold (`#D4AF37`)
- **Multi-Modal Status Chips:** Every status pairs an icon + label (`● Verified`, `⏳ Pending OTP`, `🔒 Locked Record`, `⚠ Overdue`) — never communicating status via color alone.
- **Form Factors:** 412 × 915 logical dp portrait, ≥48dp touch targets, strict safe area and keyboard handling.

---

## 👥 Role-Based Workflows

1. **Front Desk:**
   - Real-time desk metrics: Registrations today, active memberships, pending OTP verification.
   - Clean, single-page **Client Registration** featuring client name, residential address, mobile phone with inline OTP verification (`[ VERIFY WITH OTP ]` + discrete 6-digit cells), and membership tier selection (Quarterly Gold ₹18k, Annual Platinum ₹60k, Monthly Core ₹7.5k).
   - Searchable client directory with status chips.

2. **Master Trainer:**
   - Unassigned client queue monitoring.
   - Trainer capacity roster & live assignments.
   - Single-trainer binding with effective date & confirmation.
   - Audited trainer reassignment preserving history.

3. **Trainer:**
   - Personal assigned clients list and membership status.
   - Daily session attendance verification via client OTP.
   - Locked session record indicator upon successful verification.
   - Read-only historical verified session ledger.

4. **Owner / Administrator:**
   - Executive dashboard: Total offline collections, accrued trainer liability, active members, verified workouts.
   - Trainer compensation breakdown (50% baseline trainer share).
   - Semi-annual trainer performance reviews (e.g., 50% → 55% adjustment workflows).
   - Staff governance and role permissions.

---

## 🛠️ Tech Stack & Architecture

- **Language:** Kotlin 1.9.22
- **UI Framework:** Jetpack Compose (BOM 2024.02.00)
- **Architecture Pattern:** Unidirectional Data Flow (UDF), MVVM with Reactive `StateFlow`
- **Navigation:** Jetpack Navigation Compose
- **Design Tokens & Theme:** Custom Vyomi Theme (`Color.kt`, `Type.kt`, `Dimens.kt`, `Theme.kt`)
- **Google Stitch MCP Project:** [Vyomi Club Management on Google Stitch](https://stitch.withgoogle.com/projects/16920052596745078366)

---

## 📱 Project Directory Structure

```
app/src/main/java/com/vyomi/club/
├── data/
│   ├── model/Models.kt               # Client, Trainer, Session, Payment, Membership models
│   └── repository/VyomiRepository.kt  # Centralized reactive in-memory state repository
├── ui/
│   ├── components/                   # Reusable buttons, text fields, OTP inputs, status chips, app bars
│   ├── screens/
│   │   ├── auth/                     # Launch, Sign-In, Account & Role management
│   │   ├── frontdesk/                # Home, Directory, Client Registration, Client Detail
│   │   ├── mastertrainer/            # Queue, Trainer Directory, Assign & Reassign
│   │   ├── trainer/                  # Trainer Home, My Clients, Session OTP Verification, History
│   │   ├── payments/                 # Offline payment collection & 50% trainer share calculation
│   │   ├── owner/                    # Executive Overview, Earnings, 6-Month Review, Audit Trail
│   │   └── proposed/                 # Annotated proposed extensions (renewal freeze, refunds)
│   ├── theme/                        # Colors, typography, dimensions, theme wrappers
│   └── MainActivity.kt               # Single-activity container with role orchestration
```

---

## 🚀 Building & Running

1. Clone this repository:
   ```bash
   git clone https://github.com/awyk-digital/Vyomi-Android-Apk.git
   ```
2. Open the project in **Android Studio Hedgehog / Iguana / Jellyfish** or later.
3. Allow Gradle to sync dependencies.
4. Run on an Android Emulator or physical device (Pixel 7 / 8 / 9 recommended for 412 × 915dp portrait).
