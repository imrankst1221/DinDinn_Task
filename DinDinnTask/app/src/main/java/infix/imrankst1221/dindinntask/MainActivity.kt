package infix.imrankst1221.dindinntask

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.airbnb.mvrx.BaseMvRxActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvRxActivity(){
    lateinit var mActivity: Activity
    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)

        mActivity = this
        mContext = this
    }
}
