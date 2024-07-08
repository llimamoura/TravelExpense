package com.example.travelexpenses

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.travelexpenses.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.buttonCalcule.setOnClickListener {
            calc()
        }
    }

    private fun isValid(): Boolean {
        return (binding.editTextDistance.text.toString() != ""
                && binding.editTextPrice.text.toString() != ""
                && binding.editTextAutonomy.text.toString() != ""
                && binding.editTextAutonomy.text.toString().toFloat() != 0f)
    }

    private fun calc() {
        if (isValid()) {
            val distance = binding.editTextDistance.text.toString().toFloat()
            val price = binding.editTextPrice.text.toString().toFloat()
            val autonomy = binding.editTextAutonomy.text.toString().toFloat()
            val result = (distance * price) / autonomy
            binding.textResultTotal.text = "R$ ${"%.2f".format(result)}"
        } else {
            Toast.makeText(this, getString(R.string.toastMessage), Toast.LENGTH_SHORT).show()
        }

    }
}