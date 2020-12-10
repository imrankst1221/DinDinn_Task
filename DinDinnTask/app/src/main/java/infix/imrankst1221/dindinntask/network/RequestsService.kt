package infix.imrankst1221.dindinntask.network

import infix.imrankst1221.dindinntask.model.FoodMenu
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface FoodApiService{
    @Headers("Content-Type: application/json")
    @GET("user-envato-tracking")
    fun getFoodMenu(
        //@Body foodMenuRequest: FoodMenuRequest
    ): Observable<FoodMenu>
}