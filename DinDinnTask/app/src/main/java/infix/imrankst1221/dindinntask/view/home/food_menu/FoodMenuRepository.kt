/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package infix.imrankst1221.dindinntask.view.home.food_menu

import android.content.Context
import infix.imrankst1221.dindinntask.Constants
import infix.imrankst1221.dindinntask.model.Category
import infix.imrankst1221.dindinntask.model.FoodMenu
import infix.imrankst1221.dindinntask.model.Root
import infix.imrankst1221.dindinntask.network.ApiService
import infix.imrankst1221.dindinntask.utils.UtilMethods.isConnectedToInternet
import infix.imrankst1221.dindinntask.utils.UtilMethods.showLongToast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FoodMenuRepository {
    private val foodMenuList = mutableListOf<Root>()

    fun getFoodMenuList(context: Context): Observable<List<Root>> = Observable.fromCallable<List<Root>> {
        foodMenuList.addAll(listOf())
        var isWaitForData = true

        if(isConnectedToInternet(context)){
            val dataObservable = ApiService.getFoodMenu(Constants.API_BASE_URL).getFoodMenu()

            dataObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
               isWaitForData  = false
               foodMenuList.addAll(response.root)
            }) { error ->
               isWaitForData  = false
               showLongToast(context, error.message.toString())
            }
        }else{
            isWaitForData  = false
            showLongToast(context, "No Internet!")
        }

        while (isWaitForData) try {
            Thread.sleep(1000)
        } catch (ignore: InterruptedException) {
        }

          foodMenuList
    }.subscribeOn(Schedulers.io())

    fun addToCart(categoryId: Int, itemId: Int){

    }

    fun removeFromCart(categoryId: Int, itemId: Int){

    }
}
