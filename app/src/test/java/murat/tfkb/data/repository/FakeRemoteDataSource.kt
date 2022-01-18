package murat.tfkb.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import murat.tfkb.data.network.retrofit.FakeRetrofitApi
import murat.tfkb.domain.model.ResultState
import murat.tfkb.domain.model.SearchResultItem
import murat.tfkb.util.TAG

class FakeRemoteDataSource: RemoteDataSource {

    private val searchResult = mutableListOf<SearchResultItem>()
    private val fakeRetrofitApi = FakeRetrofitApi()

    override fun getSearchResults(term: String, limit: Int, entity: String): Flow<ResultState<MutableList<SearchResultItem>>> {
        return flow {
            emit(ResultState.LOADING())
            try {
                val r = fakeRetrofitApi.getResults(term, limit, entity)
                emit(ResultState.SUCCESS(r.results))
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
                emit(ResultState.ERROR(e))
            }
        }
    }
}