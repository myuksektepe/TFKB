package murat.tfkb.util.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import murat.tfkb.data.network.retrofit.RetrofitApi
import murat.tfkb.data.repository.RemoteDataSource
import murat.tfkb.data.repository.RemoteDataSourceImpl
import murat.tfkb.domain.use_case.GetSearchResult
import murat.tfkb.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideRetrofitApi(retrofit: Retrofit): RetrofitApi = retrofit.create()


    @Provides
    fun provideRemoteDataSource(retrofitApi: RetrofitApi): RemoteDataSource {
        return RemoteDataSourceImpl(retrofitApi)
    }

    @Provides
    fun provideGetSearchResult(remoteDataSource: RemoteDataSource): GetSearchResult {
        return GetSearchResult(remoteDataSource)
    }
}