package murat.tfkb.presentation.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.jakewharton.rxbinding.widget.RxTextView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import murat.tfkb.R
import murat.tfkb.ViewExtensions.showAlert
import murat.tfkb.databinding.ActivityMainBinding
import murat.tfkb.domain.model.ResultState
import murat.tfkb.domain.model.SearchResultItem
import murat.tfkb.presentation.adapter.ItemAdapter
import murat.tfkb.presentation.base.BaseActivity
import murat.tfkb.presentation.viewmodel.MainActivityViewModel
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    override val layoutRes = R.layout.activity_main
    override var viewLifeCycleOwner: LifecycleOwner = this
    override val viewModel: MainActivityViewModel by viewModels()
    lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        itemAdapter = ItemAdapter(listOf())

        viewModel.fetchAllResults("Dance", 100, "")

        RxTextView
            .textChanges(binding.edtSearch)
            .debounce(1, TimeUnit.SECONDS)
            .subscribe {
                viewModel.fetchAllResults(it.toString(), 30, "")
            }
    }

    override fun obverseViewModel() {
        viewModel.allResults.observe(viewLifeCycleOwner, {
            lifecycleScope.launch(Dispatchers.Main) {
                when (it) {
                    is ResultState.LOADING -> {
                        showLoading1()
                    }
                    is ResultState.ERROR -> {
                        this@MainActivity.showAlert(it.exception.message.toString())
                    }
                    is ResultState.SUCCESS -> {
                        val list = it.data as List<SearchResultItem>
                        if (list.isNotEmpty()) {
                            itemAdapter.update(list)
                            binding.rcyItems.apply {
                                adapter = itemAdapter
                                layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                                setHasFixedSize(true)
                            }
                        }
                        hideLoading1()
                    }
                }
            }
        })
    }

}