package com.example.green_snack

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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


class DetailFragment : Fragment() {
    private val client = OkHttpClient()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val recept = view.findViewById<TextView>(R.id.recept)
        val receptDetails = view.findViewById<TextView>(R.id.receptDetails)
        val proba = view.findViewById<Button>(R.id.proba)
        proba.setOnClickListener {
            val question = "Kako si"
            getResponse(question){response ->
                requireActivity().runOnUiThread{
                    receptDetails.text = response
                }
            }
        }

        return view
    }

    fun getResponse(s: String, callback: (String) -> Unit){
        val url = "https://api.openai.com/v1/chat/completions"
        val secretKey = "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"

        val requestBody = """
        {
            "model": "gpt-4",
            "messages": [
                {"role": "user", "content": "$s"}
            ],
            "max_tokens": 7,
            "temperature": 0
        }
    """.trimIndent()

        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $secretKey")
            .post(requestBody.toRequestBody("application/json".toMediaTypeOrNull()))
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



}