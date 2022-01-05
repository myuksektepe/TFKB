package murat.tfkb.presentation.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import murat.tfkb.R
import murat.tfkb.databinding.ActivityMainBinding
import murat.tfkb.domain.model.ResultState
import murat.tfkb.presentation.viewmodel.MainActivityViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.fetchAllResults("rick", 10, "")

        lifecycleScope.launchWhenCreated {
            viewModel.allResults.observe(this@MainActivity) {
                when (it) {
                    is ResultState.LOADING -> {
                        binding.txtMain.text = "Loading"
                    }
                    is ResultState.ERROR -> {
                        binding.txtMain.text = it.exception.message
                    }
                    is ResultState.SUCCESS -> {
                        binding.txtMain.text = it.data.toString()
                    }
                }
            }
        }

    }
}