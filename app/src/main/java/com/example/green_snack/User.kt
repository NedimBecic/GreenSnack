package com.example.green_snack

data class User(
    val ime: String,
    val prezime: String,
    val spol: String,
    val starosnaDob: Int,
    val kilaza: Double,
    val visina: Double,
    val bolesti: String? = null,
    val alergije: String? = null,
    val brojJelaUDanu: Int,
    val jelaUDanu: List<String>,
    val kategorijeJela: List<String>,
    val budzet : Double
)
