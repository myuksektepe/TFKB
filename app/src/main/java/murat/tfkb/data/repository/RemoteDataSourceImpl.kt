package murat.tfkb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import murat.tfkb.data.network.retrofit.RetrofitApi
import murat.tfkb.domain.model.ResultState
import murat.tfkb.domain.model.SearchResultItem
import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(
    private val searchApi: RetrofitApi
) : RemoteDataSource {
    override fun getSearchResults(term: String, limit: Int, entity: String): Flow<MutableList<SearchResultItem>> = flow {
        try {
            val results = searchApi.getResults(term, limit, entity)
            ResultState.SUCCESS(results)
        } catch (e: Exception) {
            ResultState.ERROR(e)
        }
    }
}