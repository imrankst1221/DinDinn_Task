package infix.imrankst1221.dindinntask.network

/**
 * Created by Md Imran Choudhury on 10/Aug/2018.
 * All rights received InfixSoft
 * Contact email: imrankst1221@gmail.com
 */

import com.google.gson.GsonBuilder
import infix.imrankst1221.dindinntask.Constants
import okhttp3.*
import java.io.IOException
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

class ApiWorker(private var isInternet: Boolean) {
    private var mClient: OkHttpClient? = null
    private var mGsonConverter: GsonConverterFactory? = null
    private val TAG = "--ApiWorker"
    private val MEDIA_TYPE = MediaType.parse(Constants.API_CONTENT_TYPE)

    /**
     * Don't forget to remove Interceptors (or change Logging Level to NONE)
     * in production! Otherwise people will be able to see your request and response on Log Cat.
     */

    val client: OkHttpClient
        @Throws(NoSuchAlgorithmException::class, KeyManagementException::class)
        get() {
            if (mClient == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val httpBuilder = OkHttpClient.Builder()
                httpBuilder
                        .connectTimeout(3, TimeUnit.MINUTES)
                        .readTimeout(3, TimeUnit.MINUTES)
                        .readTimeout(3, TimeUnit.MINUTES)
                        .addNetworkInterceptor(CacheInterceptor(isInternet))
                        .addInterceptor(interceptor)  /// show all JSON in logCat

                mClient = httpBuilder.build()

            }
            return mClient!!
        }
   val gsonConverter: GsonConverterFactory
           get() {
               if(mGsonConverter == null){
                   mGsonConverter = GsonConverterFactory
                           .create(GsonBuilder()
                           .setLenient()
                           .disableHtmlEscaping()
                           .create())
               }
               return mGsonConverter!!
           }
}

class CacheInterceptor(private var isInternet: Boolean) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (isInternet) {
            request = request.newBuilder()
                    .removeHeader("Pragma")
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
        }

        val originalResponse = chain.proceed(request)
        val cacheControl = originalResponse.header("Cache-Control")

        if (isRemoteNoCache(cacheControl)) {
            val maxStale = 60 * 60 * 24 * 28  // tolerate 4-weeks stale
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=$maxStale")
                    .build()
        } else
            return originalResponse
    }

    private fun isRemoteNoCache(cacheControl: String?): Boolean =
            cacheControl == null ||
                    cacheControl.contains("no-store", true) ||
                    cacheControl.contains("no-cache", true) ||
                    cacheControl.contains("must-revalidate", true) ||
                    cacheControl.contains("max-age=0", true)

}

class AddHeaderInterceptor : Interceptor {
    private val TAG = "--ApiWorker"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val builder = chain.request().newBuilder()
        //builder.addHeader("Authorization", PreferenceUtils.getStringValue(Constants.API_AUTHORIZATION_KEY, "").toString())

        return chain.proceed(builder.build())
    }
}