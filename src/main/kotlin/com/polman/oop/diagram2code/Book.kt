package com.polman.oop.diagram2code

/**
 * Merepresentasikan sebuah eksemplar buku di perpustakaan.
 * Mengimplementasikan interface [Loanable].
 *
 * Invarian:
 * - 0 <= availableCount <= totalCount.
 * - year e [1400..2100].
 * - loan(): False jika stok habis.
 * - returnOne(): tidak boleh melebihi totalCount (lempar exception).
 */
class Book(
    val id: String,
    val title: String,
    val author: String,
    val year: Int,
    val totalCount: Int
) : Loanable {

    // Simpan stok tersedia saat ini (private), inisialisasi = totalCount
    private var availableCount: Int

    init {
        // TODO: validasi awal:
        // - id/title/author non-blank
        // - year in 1400..2100
        // - totalCount >= 0
        // - set availableCount = totalCount

        require(id.isNotBlank()) { "Book ID tidak boleh kosong" }
        require(title.isNotBlank()) { "Book title tidak boleh kosong" }
        require(author.isNotBlank()) { "Book author tidak boleh kosong" }

        require(year in 1400..2100) { "Tahun harus antara 1400 dan 2100, tahun '$year' tidak valid" }

        require(totalCount >= 0) { "Total count tidak boleh negatif" }

        this.availableCount = totalCount
    }

    /**
     * Cek ketersediaan stok.
     * @return true jika availableCount > 0.
     */
    fun inStock(): Boolean {
        // TODO: true jika availableCount > 0
        return availableCount > 0
    }

    /**
     * Mengakses stok terkini (getter untuk inspeksi/test).
     */
    fun available(): Int {
        // TODO: kembalikan availableCount
        return availableCount
    }

    /**
     * Mengurangi stok jika ada (mematuhi kontrak Loanable).
     * - jika inStock(): kurangi availableCount lalu return true
     * - jika tidak: return false (bukan exception)
     */
    override fun loan(to: Member): Boolean {
        // TODO: Implement Book.loan
        if (inStock()) {
            availableCount--
            return true // Peminjaman berhasil
        }
        return false // Gagal karena stok habis
    }

    /**
     * Menambah stok (pengembalian).
     * - tambah availableCount
     * - jika sudah penuh (availableCount >= totalCount) lalu dipaksa tambah
     * -> lempar *IllegalArgumentException* (sesuai file tes)
     */
    fun returnOne() {
        // TODO: Implement Book.returnOne (proteksi over-capacity)

        // DIPERBARUI: Menggunakan IllegalArgumentException sesuai file tes
        if (availableCount >= totalCount) {
            // Lempar exception jika stok sudah penuh
            throw IllegalArgumentException("Tidak bisa return: semua eksemplar sudah tersedia (stok=$availableCount/$totalCount)")
        }
        availableCount++
    }

    /**
     * Ringkasan info buku.
     * Contoh: "Book[id=B001, title=OOP, ..., stock=1/2]"
     */
    fun info(): String {
        // TODO: kembalikan ringkasan
        return "Book[id=$id, title=$title, author=$author, year=$year, stock=$availableCount/$totalCount]"
    }
}