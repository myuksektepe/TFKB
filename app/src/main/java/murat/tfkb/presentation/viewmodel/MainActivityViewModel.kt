package murat.tfkb.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import murat.tfkb.domain.model.ResultState
import murat.tfkb.domain.use_case.GetSearchResult
import murat.tfkb.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getSearchResult: GetSearchResult
) : BaseViewModel() {

    private var searchJob: Job? = null

    private val _allResults = MutableLiveData<ResultState<Any>>()
    val allResults: LiveData<ResultState<Any>>
        get() = _allResults

    fun fetchAllResults(term: String, limit: Int, entity: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            getSearchResult(term, limit, entity).collect {
                _allResults.postValue(it)
            }
        }
    }

}