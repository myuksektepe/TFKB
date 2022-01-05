package murat.tfkb.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import murat.tfkb.domain.model.ResultState
import murat.tfkb.domain.model.SearchResultItem
import murat.tfkb.domain.model.SearchResults
import murat.tfkb.domain.use_case.GetSearchResult
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getSearchResult: GetSearchResult
) : ViewModel() {

    private var searchJob: Job? = null

    private val _allResults = MutableLiveData<ResultState<MutableList<SearchResultItem>>>()
    val allResults : LiveData<ResultState<MutableList<SearchResultItem>>>
        get() = _allResults

    fun fetchAllResults(term: String, limit: Int, entity: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {

            getSearchResult(term, limit, entity).collectLatest {
                _allResults.postValue(it)
            }

        }
    }
}