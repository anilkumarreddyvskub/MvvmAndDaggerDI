package com.anil.retrodi.di

import com.anil.retrodi.BuildConfig
import com.anil.retrodi.data.UseCaseImpl.MovieUseCaseImpl
import com.anil.retrodi.data.remote.MovieApi
import com.anil.retrodi.domain.MovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    } else OkHttpClient.Builder().build()


    @Provides
    @Singleton
    fun providesMovieApi(okHttpClient: OkHttpClient): MovieApi {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
            .baseUrl("https://howtodoandroid.com/").build().create(MovieApi::class.java)
    }


    @Provides
    @Singleton
    fun providesMovieUseCase(movieApi: MovieApi):MovieUseCase{
        return MovieUseCaseImpl(movieApi)
    }


}