package murat.tfkb.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import murat.tfkb.data.network.retrofit.RetrofitApi
import murat.tfkb.domain.model.ResultState
import murat.tfkb.domain.model.SearchResultItem
import murat.tfkb.util.TAG
import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(
    private val searchApi: RetrofitApi
) : RemoteDataSource {
    override fun getSearchResults(term: String, limit: Int, entity: String): Flow<ResultState<MutableList<SearchResultItem>>> = flow {
        emit(ResultState.LOADING())
        try {
            val r = searchApi.getResults(term, limit, entity)
            emit(ResultState.SUCCESS(r.results))
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
            emit(ResultState.ERROR(e))
        }
    }
}