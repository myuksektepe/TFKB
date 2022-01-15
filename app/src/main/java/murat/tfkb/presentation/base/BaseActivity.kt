package murat.tfkb.presentation.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import murat.tfkb.R

abstract class BaseActivity<T: BaseViewModel, B: ViewDataBinding>: AppCompatActivity() {

    abstract val layoutRes: Int
    abstract val viewModel: T
    abstract var viewLifeCycleOwner: LifecycleOwner
    abstract fun obverseViewModel()

    private var _binding: B? = null
    val binding get() = _binding!!

    fun initBinding(){
        _binding?.lifecycleOwner = this
        viewLifeCycleOwner = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.inflate(layoutInflater, layoutRes, null, false)
        setContentView(_binding!!.root)

        initBinding()
        obverseViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private val loadingAlertDialog by lazy {
        this.let {
            Dialog(it).apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                setContentView(R.layout.dialog_loading)
                setCancelable(false)
            }
        }
    }

    fun showLoading1() = loadingAlertDialog.show()
    fun hideLoading1() = loadingAlertDialog.hide()
}