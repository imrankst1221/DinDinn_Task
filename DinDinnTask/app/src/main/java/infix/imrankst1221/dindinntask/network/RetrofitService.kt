package infix.imrankst1221.dindinntask.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object ApiService {
    fun getFoodMenu(baseUrl: String) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(ApiWorker(true).gsonConverter)
        .client(ApiWorker(true).client)
        .build()
        .create(FoodApiService::class.java)!!
}