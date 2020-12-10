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

package infix.imrankst1221.dindinntask.view.home

import android.content.Context
import infix.imrankst1221.dindinntask.Constants
import infix.imrankst1221.dindinntask.model.FoodMenu
import infix.imrankst1221.dindinntask.network.ApiService
import infix.imrankst1221.dindinntask.utils.UtilMethods.isConnectedToInternet
import infix.imrankst1221.dindinntask.utils.UtilMethods.showLongToast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeRepository {

  private val foodMenuList = mutableListOf<FoodMenu>()

  fun getFoodMenuList(context: Context) = Observable.fromCallable<List<FoodMenu>> {
    foodMenuList.addAll(listOf())

    if(isConnectedToInternet(context)){
      val observable = ApiService.getFoodMenu(Constants.API_BASE_URL).getFoodMenu()

      observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ response ->
            foodMenuList.addAll(listOf(response))
        }) { error ->
          showLongToast(context, error.message.toString())
        }
    }else{
      showLongToast(context, "No Internet!")
    }

    foodMenuList
  }.subscribeOn(Schedulers.io())

}