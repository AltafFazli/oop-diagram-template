package com.polman.oop.diagram2code

fun main() {
    println("========= MEMULAI PENGUJIAN STUDI KASUS =========")
    val mReg = Member("M001", "Ani Regular", MemberLevel.REGULAR)
    val mGold = Member("M002", "Budi Gold", MemberLevel.GOLD)
    val mPlat = Member("M003", "Cici Platinum", MemberLevel.PLATINUM)
    val lib = Librarian("L001", "Dewi Staff", "LIB-001")
    val allPersons = listOf(mReg, mGold, mPlat, lib)
    val b1 = Book("B001", "Clean Code", "RCM", 2008, 2)
    val b2 = Book("B002", "Effective Java", "JB", 2017, 1)
    val allBooks = listOf(b1, b2)

    // STUDI KASUS 1: Registrasi Peran & Informasi Dasar
    println("\n=== STUDI KASUS 1: Registrasi Peran & Informasi Dasar ===")
    println("--- Hasil showInfo() ---")
    allPersons.forEach { println(it.showInfo()) }
    println("\n--- Uji Setter Name (trim & validasi) ---")
    try {
        mReg.name = "  Ani (Setelah Diedit)  "
        println("OK: Setter berhasil: '${mReg.name}'")
    } catch (e: Exception) {
        println("FAIL: Setter gagal: ${e.message}")
    }
    println("\n--- Uji Gagal name=\"\" (Setter) ---")
    try {
        mReg.name = "   " // Coba set dengan spasi
        println("FAIL: Validasi setter tidak melempar exception untuk input blank")
    } catch (e: IllegalArgumentException) {
        println("OK: Exception tertangkap (Setter): ${e.message}")
    }
    println("\n--- Uji Gagal name=\"\" (Constructor) ---")
    try {
        val mFail = Member("M-FAIL", "", MemberLevel.REGULAR)
        println("FAIL: Validasi constructor tidak melempar exception untuk input kosong")
    } catch (e: IllegalArgumentException) {
        println("OK: Exception tertangkap (Constructor): ${e.message}")
    }

    // STUDI KASUS 2: Denda Polimorfik
    println("\n=== STUDI KASUS 2: Denda Polimorfik ===")
    val daysToTest = listOf(0, 1, 3, 10)
    for (person in allPersons) {
        val personType = person::class.simpleName ?: "Person"
        print("$personType (${person.id}): ")
        val fees = daysToTest.map { "[$it hari: ${person.calculateFee(it)}]" }
        println(fees.joinToString(", "))
    }

    // STUDI KASUS 3: Peminjaman Buku
    println("\n=== STUDI KASUS 3: Peminjaman Buku ===")
    println("--- Tes b1 (stok=2) -> dipinjam 3x ---")
    println("Info Awal: ${b1.info()} | inStock: ${b1.inStock()}")
    println("  Loan 1 (sukses): ${b1.loan(mReg)} -> Stok: ${b1.available()} | inStock: ${b1.inStock()}")
    println("  Loan 2 (sukses): ${b1.loan(mGold)} -> Stok: ${b1.available()} | inStock: ${b1.inStock()}")
    println("  Loan 3 (gagal): ${b1.loan(mPlat)} -> Stok: ${b1.available()} | inStock: ${b1.inStock()}")
    println("\n--- Tes b2 (stok=1) -> dipinjam 2x ---")
    println("Info Awal: ${b2.info()} | inStock: ${b2.inStock()}")
    println("  Loan 1 (sukses): ${b2.loan(mReg)} -> Stok: ${b2.available()} | inStock: ${b2.inStock()}")
    println("  Loan 2 (gagal): ${b2.loan(mGold)} -> Stok: ${b2.available()} | inStock: ${b2.inStock()}")

    // STUDI KASUS 4: Pengembalian & Over-Capacity
    println("\n=== STUDI KASUS 4: Pengembalian & Over-Capacity ===")
    println("--- Tes b1 (stok saat ini=0) -> dikembalikan 3x ---")
    println("Stok Awal: ${b1.available()}")
    b1.returnOne()
    println("  Return 1 -> Stok: ${b1.available()}") // Stok jadi 1
    b1.returnOne()
    println("  Return 2 (penuh) -> Stok: ${b1.available()}") // Stok jadi 2
    try {
        b1.returnOne()
        println("  Return 3 -> FAIL: Tidak ada exception over-capacity!")
    } catch (e: IllegalArgumentException) {
        println("  Return 3 -> OK: Exception tertangkap: ${e.message}")
    }
    println("Stok Final (Invarian): ${b1.available()}") // Harus tetap 2

    // STUDI KASUS 5: Laporan Ringkas & Konsistensi
    println("\n=== STUDI KASUS 5: Laporan Ringkas & Konsistensi ===")
    println("\n--- === PERSONS === ---")
    allPersons.forEach { println(it.showInfo()) }
    println("\n--- === BOOKS === ---")
    allBooks.forEach { println(it.info()) }
    println("\n========= PENGUJIAN STUDI KASUS SELESAI =========")
}
