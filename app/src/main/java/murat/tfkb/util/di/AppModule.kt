package murat.tfkb.util.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import murat.tfkb.data.network.retrofit.RetrofitApi
import murat.tfkb.data.repository.RemoteDataSource
import murat.tfkb.data.repository.RemoteDataSourceImpl
import murat.tfkb.domain.use_case.GetSearchResult
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): RetrofitApi {
        return retrofit.create(RetrofitApi::class.java)
    }

    @Provides
    fun provideRemoteDataSource(retrofitApi: RetrofitApi): RemoteDataSource {
        return RemoteDataSourceImpl(retrofitApi)
    }

    @Provides
    fun provideGetSearchResult(remoteDataSource: RemoteDataSource): GetSearchResult {
        return GetSearchResult(remoteDataSource)
    }
}