package com.hydok.expandablefloatingbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.hydok.expandablefloatingbutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(LayoutInflater.from(this)) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.floatingButton
            .setIcon(image = R.drawable.icon, widthDp = 40)
            .setTitle(title = "안녕하세요!!!", color = R.color.white, size = 18f)
            .setColor(R.color.blue)
            .setExpandDuration(300)

        var isEx = false
        binding.text.setOnClickListener {
            isEx = if (isEx) {
                binding.floatingButton.setExpanded()
                false
            } else {
                binding.floatingButton.setCollapsed()
                true
            }

        }
    }
}