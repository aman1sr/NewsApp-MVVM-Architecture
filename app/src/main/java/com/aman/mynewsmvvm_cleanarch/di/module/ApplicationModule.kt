package com.aman.mynewsmvvm_cleanarch.di.module

import android.content.Context
import com.aman.mynewsmvvm_cleanarch.BuildConfig
import com.aman.mynewsmvvm_cleanarch.data.api.AuthTokenInterceptor
import com.aman.mynewsmvvm_cleanarch.data.api.NetworkService
import com.aman.mynewsmvvm_cleanarch.di.BASEURL
import com.aman.mynewsmvvm_cleanarch.di.NetworkAPIKey
import com.aman.mynewsmvvm_cleanarch.utils.network.NetworkHelper
import com.aman.mynewsmvvm_cleanarch.utils.network.NetworkHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideNetworkConnection(@ApplicationContext context: Context): NetworkHelper =
        NetworkHelperImpl(context)

    @Provides
    @Singleton
    @BASEURL
    fun provideNetworkBaseUrl(): String = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun provideConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()


    @Provides
    @Singleton
    fun provideHttpLogginInterceptor(): HttpLoggingInterceptor{ // todo: usecase of Http Logging
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG){
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }else{
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    @NetworkAPIKey
    fun provideNetworkAPIKey(): String = "7522d3bfb4d847fd94a529f3a08731f3"

    @Provides
    @Singleton
    fun provideAuthTokenInterceptor(@NetworkAPIKey apiKey : String): AuthTokenInterceptor = AuthTokenInterceptor(apiKey)

    @Provides
    @Singleton
    fun provideOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    authTokenInterceptor: AuthTokenInterceptor
    ) : OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(authTokenInterceptor)
        .build()


    @Provides
    @Singleton
    fun provideNetowrkService(
        @BASEURL baseUrl : String,
        gsonConverterFactory: GsonConverterFactory,
        okhttpClient: OkHttpClient
    ): NetworkService = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .client(okhttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()
        .create(NetworkService::class.java)
}