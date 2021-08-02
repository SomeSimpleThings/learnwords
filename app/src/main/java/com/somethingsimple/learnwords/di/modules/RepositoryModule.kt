package com.somethingsimple.learnwords.di.modules

import com.somethingsimple.learnwords.data.api.SkyengApi
import com.somethingsimple.learnwords.data.datasource.DataSource
import com.somethingsimple.learnwords.data.datasource.remote.RetrofitDataSource
import com.somethingsimple.learnwords.data.repo.Repository
import com.somethingsimple.learnwords.data.repo.RepositoryImpl
import com.somethingsimple.learnwords.data.vo.Word
import com.somethingsimple.learnwords.di.NAME_REMOTE
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRemoteRepository(
        @Named(NAME_REMOTE) dataSourceRemote: DataSource<List<Word>>
    ): Repository<List<Word>> =
        RepositoryImpl(dataSourceRemote)


//    @Provides
//    @Singleton
//    @Named(NAME_REMOTE)
//    internal fun provideRemoteDataSource(retrofitDataSource: RetrofitDataSource): DataSource<List<Word>> =
//        RemoteDatasource(retrofitDataSource)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRetrofitDataSource(skyengApi: SkyengApi): DataSource<List<Word>> =
        RetrofitDataSource(skyengApi)

}
