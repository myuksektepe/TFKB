package murat.tfkb.data.network.retrofit

import murat.tfkb.domain.model.SearchResults
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("search?term={term}&limit={limit}&entity={entity}")
    fun getResults(
        @Query("term") term: String,
        @Query("limit") limit: Int,
        @Query("entity") entity: String,
    ): SearchResults
}