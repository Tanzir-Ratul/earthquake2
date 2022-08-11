package com.example.earthquakeapp.di

import com.example.earthquakeapp.api.RetrofitUtils.retrofitInstance
import com.example.earthquakeapp.api.endpoint.network.ApiService
import com.example.earthquakeapp.ui.helper.Constant
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
	@Provides
	fun provideBaseUrl() = Constant.BASEURL


	@Provides
	@Singleton
	fun provideRetrofitInstance(baseUrl:String,gson: Gson,httpClient: OkHttpClient): ApiService {
		return retrofitInstance(baseUrl,gson,httpClient)
			.create(ApiService::class.java)
	}
}