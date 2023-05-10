package edu.fullerton.fz.finalproject411
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import edu.fullerton.fz.finalproject411.databinding.ActivityMainBinding


class Settings : Fragment() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        username = view.findViewById(R.id.username)
        password = view.findViewById(R.id.password)
        loginButton = view.findViewById(R.id.loginButton)

        loginButton.setOnClickListener {

            val client = OkHttpClient()

            // change "localhost" to your machine's IP address when testing on a real device
            val url = "http://10.67.35.119:3000/login" // replace with current IP

            val jsonObject = JSONObject()
            jsonObject.put("username", username.text.toString())
            jsonObject.put("password", password.text.toString())
            val body = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), jsonObject.toString())

            val request = Request.Builder()
                .url(url)
                .post(body)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                    activity?.runOnUiThread {
                        Toast.makeText(requireActivity(), "Network error!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    activity?.runOnUiThread {
                        if (response.isSuccessful) {
                            val myResponse = response.body?.string()
                            if (myResponse != null) {
                                Log.d("Response", myResponse)
                            }
                            val jsonResponse = JSONObject(myResponse)
                            email.text = "Email: ${jsonResponse.getString("email")}"
                            phone.text = "Phone: ${jsonResponse.getString("phone")}"
                            favoriteCrypto.text = "Favorite Crypto: ${jsonResponse.getString("favoriteCrypto")}"
                            Toast.makeText(requireActivity(), "Login Successful!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireActivity(), "Login Failed!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }
}