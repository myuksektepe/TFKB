package murat.tfkb.presentation.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import murat.tfkb.R
import murat.tfkb.ViewExtensions.hideDialog
import murat.tfkb.ViewExtensions.showAlert
import murat.tfkb.ViewExtensions.showLoading
import murat.tfkb.databinding.ActivityMainBinding
import murat.tfkb.domain.model.ResultState
import murat.tfkb.domain.model.SearchResultItem
import murat.tfkb.presentation.adapter.ItemAdapter
import murat.tfkb.presentation.viewmodel.MainActivityViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: MainActivityViewModel by viewModels()
    lateinit var itemAdapter: ItemAdapter
    lateinit var rcyItems: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        rcyItems = binding.rcyItems
        itemAdapter = ItemAdapter(listOf())

        viewModel.allResults.observe(this, {
            lifecycleScope.launch(Dispatchers.Main) {
                when (it) {
                    is ResultState.LOADING -> {
                        this@MainActivity.showLoading()
                    }
                    is ResultState.ERROR -> {
                        this@MainActivity.showAlert(it.exception.message.toString())
                    }
                    is ResultState.SUCCESS -> {
                        hideDialog()
                        val list = it.data as List<SearchResultItem>
                        if (list.isNotEmpty()) {
                            itemAdapter.update(list)
                            rcyItems.apply {
                                adapter = itemAdapter
                                layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                                setHasFixedSize(true)
                            }
                        }
                    }
                }
            }
        })

        //viewModel.trys()
        viewModel.fetchAllResults("Dance", 100, "")
    }
}