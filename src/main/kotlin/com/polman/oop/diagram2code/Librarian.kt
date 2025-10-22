package com.polman.oop.diagram2code

/**
 * Merepresentasikan Pustakawan.
 * Turunan dari [Person].
 * +staffCode: String
 *
 * Catatan:
 * - staffCode non-blank
 * - calculateFee() selalu 0.0
 */
class Librarian(
    id: String,
    name: String,
    val staffCode: String
) : Person(id, name) {

    init {
        // TODO: validasi staffCode non-blank
        require(staffCode.isNotBlank()) { "Staff code tidak boleh kosong" }
    }

    /**
     * Menampilkan info Librarian.
     * Contoh: "Librarian[id=L001, name=Sari, staffCode=LIB-777]"
     */
    override fun showInfo(): String {
        // TODO: kembalikan string informatif
        return "Librarian[id=$id, name=$name, staffCode=$staffCode]"
    }

    /**
     * Pustakawan tidak pernah dikenai denda.
     * Selalu mengembalikan 0.0.
     */
    override fun calculateFee(daysLate: Int): Double {
        // TODO: kembalikan 0.0
        // Mematuhi kontrak: daysLate >= 0
        require(daysLate >= 0) { "Hari keterlambatan tidak boleh negatif" }
        return 0.0
    }
}