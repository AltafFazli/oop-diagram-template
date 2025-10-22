package com.polman.oop.diagram2code

/**
 * Merepresentasikan Anggota perpustakaan.
 * Turunan dari [Person].
 * +level: MemberLevel
 */
class Member(
    id: String,
    name: String,
    val level: MemberLevel
) : Person(id, name) {

    /**
     * Menampilkan info Member.
     * Contoh: "Member[id=M001, name=Ani, level=REGULAR]"
     */
    override fun showInfo(): String {
        // TODO: kembalikan string informatif
        return "Member[id=$id, name=$name, level=$level]"
    }

    /**
     * Menghitung denda berdasarkan level member.
     * Asumsi tarif (sesuai file test):
     * - REGULAR: 2000 / hari
     * - GOLD: 1500 / hari
     * - PLATINUM: 1000 / hari
     * - 0 jika daysLate = 0.
     */
    override fun calculateFee(daysLate: Int): Double {
        // TODO: Implement kebijakan denda per level.
        // Kontrak: daysLate >= 0
        require(daysLate >= 0) { "Hari keterlambatan tidak boleh negatif" }

        if (daysLate == 0) return 0.0

        // Menggunakan asumsi tarif dari file unit test
        val rate = when (level) {
            MemberLevel.REGULAR -> 2000.0
            MemberLevel.GOLD -> 1500.0
            MemberLevel.PLATINUM -> 1000.0
        }
        return rate * daysLate
    }
}