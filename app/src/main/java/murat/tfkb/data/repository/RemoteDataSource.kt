package murat.tfkb.data.repository

import kotlinx.coroutines.flow.Flow
import murat.tfkb.domain.model.ResultState
import murat.tfkb.domain.model.SearchResultItem

interface RemoteDataSource {
    fun getSearchResults(term: String, limit: Int, entity: String): Flow<ResultState<MutableList<SearchResultItem>>>
}