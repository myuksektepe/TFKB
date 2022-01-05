package murat.tfkb.domain.use_case

import kotlinx.coroutines.flow.Flow
import murat.tfkb.data.repository.RemoteDataSource
import murat.tfkb.domain.model.SearchResultItem
import javax.inject.Inject

class GetSearchResult @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    operator fun invoke(term: String, limit: Int, entity: String):
            Flow<MutableList<SearchResultItem>> =
        remoteDataSource.getSearchResults(term, limit, entity)
}