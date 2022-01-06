package murat.tfkb.presentation.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import murat.tfkb.R
import murat.tfkb.databinding.ActivityMainBinding
import murat.tfkb.domain.model.ResultState
import murat.tfkb.presentation.viewmodel.MainActivityViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.allResults.observe(this, {
            lifecycleScope.launch(Dispatchers.Main) {
                when (it) {
                    is ResultState.LOADING -> {
                        binding.txtMain.text = "Yükleniyooor.."
                    }
                    is ResultState.ERROR -> {
                        binding.txtMain.text = "Hata: ${it.exception.message}"
                    }
                    is ResultState.SUCCESS -> {
                        binding.txtMain.text = it.data.toString()
                    }
                }
            }
        })

        //viewModel.trys()
        viewModel.fetchAllResults("Ricky", 3, "")
    }
}