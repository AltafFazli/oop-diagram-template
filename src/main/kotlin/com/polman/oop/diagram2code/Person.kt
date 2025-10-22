package com.polman.oop.diagram2code

/**
 * Kelas abstrak dasar untuk semua orang di sistem perpustakaan.
 *
 * Invarian:
 * - id tidak boleh kosong (non-blank).
 * - name harus 2..100 karakter setelah di-trim. validateName() dipanggil di init & setter.
 */
abstract class Person(
    val id: String,
    name: String // Parameter konstruktor
) {
    /**
     * Properti nama dengan custom setter untuk validasi dan trim.
     */
    var name: String = name
        set(value) {
            // TODO: panggil validateName(value) dan terapkan trim
            validateName(value)
            field = value.trim()
        }

    init {
        // TODO: validasi id non-blank, validasi name menggunakan validateName()
        require(id.isNotBlank()) { "ID tidak boleh kosong" }
        // Validasi nama awal menggunakan validateName()
        validateName(name)
        // Pastikan nilai awal juga di-trim saat diinisialisasi
        this.name = name.trim()
    }

    /**
     * Validasi nama:
     * - tidak kosong setelah trim
     * - panjang 2..100
     * - Lempar IllegalArgumentException jika tidak valid.
     */
    protected fun validateName(n: String) {
        // TODO: implement validasi sesuai catatan diagram
        val trimmedName = n.trim()
        require(trimmedName.isNotBlank()) { "Nama tidak boleh kosong (setelah trim)" }
        require(trimmedName.length in 2..100) { "Nama harus 2..100 karakter" }
    }

    /**
     * Ringkasan identitas untuk audit/log.
     * Implementasi di subclass.
     */
    abstract fun showInfo(): String

    /**
     * Hitung denda keterlambatan:
     * - Kontrak: daysLate >= 0 -> hasil >= 0.0
     * - Implementasi di subclass (polimorfik).
     */
    abstract fun calculateFee(daysLate: Int): Double
}
