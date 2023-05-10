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
            if (username.text.toString() == "user" && password.text.toString() == "1234") {
                Toast.makeText(requireActivity(), "Login Successful!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireActivity(), "Login Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}