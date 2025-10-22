package com.polman.oop.diagram2code

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BookLoanAndReturnTest {

    @Test
    fun `loan menurunkan stok hingga nol lalu gagal jika habis`() {
        val m1 = Member("M001", "Ani", MemberLevel.REGULAR)
        val m2 = Member("M002", "Budi", MemberLevel.PLATINUM)
        val b = Book("B003", "Kotlin in Action", "Jemerov", 2017, totalCount = 2)

        // 2 -> 1
        assertTrue(b.loan(m1))
        assertEquals(1, b.available())

        // 1 -> 0
        assertTrue(b.loan(m2))
        assertEquals(0, b.available())

        // habis -> false, stok tetap 0
        assertFalse(b.loan(m1))
        assertEquals(0, b.available())
        assertFalse(b.inStock())
    }

    @Test
    fun `returnOne menambah stok jika belum penuh dan melempar exception jika over-capacity`() {
        val m = Member("M010", "Danu", MemberLevel.REGULAR)
        val b = Book("B010", "Refactoring", "Martin Fowler", 1999, totalCount = 1)

        // pinjam dulu agar stok 0
        assertTrue(b.loan(m))
        assertEquals(0, b.available())

        // return sah: 0 -> 1
        b.returnOne()
        assertEquals(1, b.available())

        // over-capacity: stok sudah 1/1, return lagi -> exception
        val ex = assertThrows<IllegalArgumentException> {
            b.returnOne()
        }

        // Cek pesan exception
        val msg = ex.message.orEmpty()
        assertTrue(msg.contains("sudah tersedia") || msg.contains("penuh") || msg.contains("over"))

        // Pastikan stok tidak bertambah
        assertEquals(1, b.available())
    }

    @Test
    fun `siklus pinjam-kembali berulang menjaga invarian 0 - totalCount`() {
        val m = Member("M020", "Eka", MemberLevel.GOLD)
        val b = Book("B020", "Clean Architecture", "Robert C. Martin", 2017, totalCount = 2)

        repeat(5) {
            // pinjam sampai habis
            val res1 = b.loan(m) // true
            val res2 = b.loan(m) // true
            val res3 = b.loan(m) // false

            assertTrue(res1 || res2 || !res3) // Setidaknya salah satu pinjaman berhasil atau gagal
            assertEquals(0, b.available())

            // kembalikan 1
            if (b.available() < 2) b.returnOne() // 0 -> 1
            // kembalikan 1 lagi bila belum penuh
            if (b.available() < 2) b.returnOne() // 1 -> 2

            // Pastikan invarian
            assertTrue(b.available() in 0..2)
        }

        assertEquals(2, b.available()) // Stok akhir harus penuh
    }
}