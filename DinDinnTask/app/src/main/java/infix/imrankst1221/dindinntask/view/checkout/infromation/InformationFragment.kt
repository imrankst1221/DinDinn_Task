package infix.imrankst1221.dindinntask.view.checkout.infromation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import infix.imrankst1221.dindinntask.R
import infix.imrankst1221.dindinntask.view.payment.PaymentViewModel

class InformationFragment : Fragment() {

    companion object {
        fun newInstance() = InformationFragment()
    }

    private lateinit var viewModel: PaymentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PaymentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}