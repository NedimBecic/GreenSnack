package com.example.green_snack

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class Home : Fragment() {


    private val client = OkHttpClient()
    private lateinit var user: User
    private var brojStavki : Int = 0
    private var shoppingItems: MutableList<ShoppingItem> = mutableListOf()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val dorucakView = view.findViewById<TextView>(R.id.dorucak)
        val rucakView = view.findViewById<View>(R.id.rucak)
        val veceraView = view.findViewById<View>(R.id.vecera)
        val imeTitle = view.findViewById<TextView>(R.id.imeTitle)

        val firstName = arguments?.getString("firstName") ?: "Nedim"

        imeTitle.text = firstName

        val lastName = arguments?.getString("lastName") ?: ""
        val starosnaDob = arguments?.getInt("starosnaDob", 0) ?: 0
        val kilaza = arguments?.getDouble("kilaza", 0.0) ?: 0.0
        val visina = arguments?.getDouble("visina", 0.0) ?: 0.0
        val spol = arguments?.getString("spol") ?: ""
        val bolesti = arguments?.getString("bolesti")
        val alergije = arguments?.getString("alergije")
        val brojJelaUDanu = arguments?.getInt("brojJelaUDanu", 0) ?: 0
        val jelaUDanu = arguments?.getStringArrayList("jelaUDanu") ?: arrayListOf()
        val selectedCategories = arguments?.getStringArrayList("selectedCategories") ?: arrayListOf()
        val budzet = arguments?.getDouble("budzet", 0.0) ?: 0.0

        user = User(
            ime = firstName,
            prezime = lastName,
            spol = spol,
            starosnaDob = starosnaDob,
            kilaza = kilaza,
            visina = visina,
            bolesti = bolesti,
            alergije = alergije,
            brojJelaUDanu = brojJelaUDanu,
            jelaUDanu = jelaUDanu,
            kategorijeJela = selectedCategories,
            budzet = budzet
        )


        val showShoppingListButton = view.findViewById<TextView>(R.id.view_full_list_button)
        showShoppingListButton.setOnClickListener {
            showShoppingListBottomSheet()
        }

        dorucakView.setOnClickListener {
            getResponse(planIshraneProfil(user, "Doručak", null)) { response ->
                requireActivity().runOnUiThread {
                    try {
                        val dorucakResponse = parsePlanObroka(response)
                        addIngredientsToShoppingList(dorucakResponse.sastojci)
                        showMealBottomSheet(
                            dorucakResponse.jelo ?: "No recipe available.",
                            dorucakResponse.sastojci,
                            dorucakResponse.poboljsanja
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                        showMealBottomSheet(
                            "Doručak",
                            listOf("No ingredients available."),
                            listOf("No benefits available.")
                        )
                    }
                }
            }
        }

        rucakView.setOnClickListener {
            getResponse(planIshraneProfil(user, "Ručak", null)) { response ->
                requireActivity().runOnUiThread {
                    try {
                        val rucakResponse = parsePlanObroka(response)
                        addIngredientsToShoppingList(rucakResponse.sastojci)
                        showMealBottomSheet(
                            rucakResponse.jelo ?: "No recipe available.",
                            rucakResponse.sastojci,
                            rucakResponse.poboljsanja
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                        showMealBottomSheet(
                            "Ručak",
                            listOf("No ingredients available."),
                            listOf("No benefits available.")
                        )
                    }
                }
            }
        }

        veceraView.setOnClickListener {
            getResponse(planIshraneProfil(user, "Večera", null)) { response ->
                requireActivity().runOnUiThread {
                    try {
                        val veceraResponse = parsePlanObroka(response)
                        addIngredientsToShoppingList(veceraResponse.sastojci)
                        showMealBottomSheet(
                            veceraResponse.jelo ?: "No recipe available.",
                            veceraResponse.sastojci,
                            veceraResponse.poboljsanja
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                        showMealBottomSheet(
                            "Večera",
                            listOf("No ingredients available."),
                            listOf("No benefits available.")
                        )
                    }
                }
            }
        }

        return view
    }

    private fun showMealBottomSheet(title: String, ingredients: List<String>, benefits: List<String>) {
        val bottomSheet = MealBottomSheetFragment.newInstance(title, ingredients, benefits)
        bottomSheet.show(parentFragmentManager, bottomSheet.tag)
    }


    fun getResponse(s: String, callback: (String) -> Unit){
        val url = "https://api.openai.com/v1/chat/completions"
        val secretKey = "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"

        val messageObject = JSONObject().apply {
            put("role", "user")
            put("content", s)
        }

        val requestBodyJson = JSONObject().apply {
            put("model", "gpt-4")
            put("messages", JSONArray().put(messageObject))
            put("max_tokens", 500)
            put("temperature", 0)
        }

        val requestBody = requestBodyJson.toString().toRequestBody("application/json".toMediaTypeOrNull())

        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $secretKey")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("error", "Api failed: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                if (body != null) {
                    Log.v("data", body)
                    val jsonObject = JSONObject(body)
                    val choicesArray: JSONArray = jsonObject.getJSONArray("choices")
                    val textResult = choicesArray.getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content")
                    callback(textResult)
                } else {
                    Log.v("data", "empty")
                }
            }
        })
    }

    fun planIshraneProfil(userProfile: User, obrok: String, ciljGubitkaTezine: String?): String {
        return """
        Na osnovu sljedećeg profila korisnika, kreiraj plan obroka za ${obrok} s ciljem smanjenja emisije CO2 i minimiziranja otpada od hrane.

        **Profil korisnika:**
        - Spol: ${userProfile.spol}
        - Starost: ${userProfile.starosnaDob} godina
        - Težina: ${userProfile.kilaza} kg
        - Visina: ${userProfile.visina} cm
        - Bolesti/Stanja: ${userProfile.bolesti ?: "Nema"}
        - Alergije: ${userProfile.alergije ?: "Nema"}
        - Broj obroka dnevno: ${userProfile.brojJelaUDanu}
        - Vrste obroka i vremena: ${userProfile.jelaUDanu.joinToString(", ")}
        - Preferirane kategorije jela: ${userProfile.kategorijeJela.joinToString(", ")}
        **Ciljevi**:
        - Smanjenje otpada od hrane i emisije CO2 kroz izbor namirnica, porcije i načine pripreme.
        - ${ciljGubitkaTezine ?: "Nije postavljen specifičan cilj gubitka težine"}
        **Zahtjevi za plan ishrane**:
        - Obavezno navesti sastojke i količine za svaki obrok.
        - Osigurati raznovrsnost tokom dana, poštujući zdravstvene potrebe korisnika.
        - Koristiti što je moguće više lokalne namirnice dostupne u Bosni i Hercegovini.
        - Uključiti preferirane kategorije jela koliko god je moguće, uz balansiranje nutritivnih potreba.
        **Struktura odgovora**
        - Primjer izgleda odgovora:
            {
            "obrok": "${obrok}",
			"jelo": "naziv jela",
            "sastojci": [
                "sastojak 1",
                "sastojak 2",
                ...],
            "poboljsanja": [
                "smanjujete CO2 emisije za x%",
                "štedite x% KM u odnosu na prosječan obrok",
                "ostvarili ste x% manje otpada hrane nego inače"]
            }
            
    """.trimIndent()
    }

    fun parsePlanObroka(jsonString: String): GPTResponse {
        try {
            val jsonObject = JSONObject(jsonString)

            val obrok = jsonObject.getString("obrok")

            // Izdvojite naziv doručka
            val nazivDorucka = jsonObject.getString("jelo")


            // Izdvajanje sastojaka za doručak
            val sastojciArray = jsonObject.getJSONArray("sastojci")
            val sastojci = List(sastojciArray.length()) { sastojciArray.getString(it) }
            brojStavki = sastojci.size

            try {
                val poboljsanjaArray = jsonObject.getJSONArray("poboljsanja")
                val poboljsanja = List(poboljsanjaArray.length()) { poboljsanjaArray.getString(it) }
                return GPTResponse(obrok, nazivDorucka, sastojci, poboljsanja)
            }catch (e: Exception){
                val poboljsanjaArray = jsonObject.getJSONArray("poboljšanja")
                val poboljsanja = List(poboljsanjaArray.length()) { poboljsanjaArray.getString(it) }
                return GPTResponse(obrok, nazivDorucka, sastojci, poboljsanja)
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }
        return GPTResponse("Nema dovoljno podataka","", listOf(""),listOf(""))
    }

    fun recept(jelo: String): String {
        return """Napisi detaljan recept za pripremu ${jelo}.""".trimIndent()
    }

    private fun addIngredientsToShoppingList(ingredients: List<String>) {
        for (ingredient in ingredients) {
            if (!shoppingItems.any { it.name.equals(ingredient, ignoreCase = true) }) {
                shoppingItems.add(ShoppingItem(name = ingredient, isChecked = false))
            }
        }
    }

    private fun showShoppingListBottomSheet() {
        val shoppingListBottomSheet = ShoppingListBottomSheetDialogFragment.newInstance(ArrayList(shoppingItems))
        shoppingListBottomSheet.show(parentFragmentManager, shoppingListBottomSheet.tag)
    }
}