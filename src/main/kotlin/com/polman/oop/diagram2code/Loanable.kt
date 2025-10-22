package com.polman.oop.diagram2code

/**
 * Interface untuk objek yang dapat dipjam.
 * +loan(to: Member): Boolean
 *
 * Kontrak:
 * - Mengembalikan true jika peminjaman berhasil, false jika stok habis (bukan exception).
 */
interface Loanable {
    /**
     * Mencoba meminjamkan item ini ke [to] Member.
     * @return true jika berhasil, false jika gagal (misal: stok habis).
     */
    fun loan(to: Member): Boolean
}