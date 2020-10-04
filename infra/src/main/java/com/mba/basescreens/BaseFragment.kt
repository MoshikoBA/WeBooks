package com.mba.basescreens

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.textfield.TextInputEditText
import com.mba.extensions.R
import com.mba.extensions.setHorizontalMarginDP
import com.mba.extensions.setPaddingOnAllSidesDP
import com.mba.extensions.showOrGone
import com.mba.toolbar.ActionButton
import kotlinx.android.synthetic.main.fragment_base.*
import kotlinx.android.synthetic.main.fragment_base.view.*

abstract class BaseFragment : DialogFragment(), BaseScreen {
    lateinit var swipeToRefresh: SwipeRefreshLayout

    val baseActivity: BaseActivity
        get() = activity as BaseActivity

    override fun getContext(): Context {
        return baseActivity.baseContext
    }

    companion object {
        const val PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val baseView = inflater.inflate(R.layout.fragment_base, container, false) as ViewGroup
        val view = LayoutInflater.from(context).inflate(contentViewLayoutResId, null)

        swipeToRefresh = baseView.findViewById(R.id.swipeToRefresh)
        swipeToRefresh.isEnabled = enableSwipeToRefresh()
        swipeToRefresh.setOnRefreshListener { onSwipeToRefresh() }
        baseView.baseLayout.addView(view)

        return baseView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.back.showOrGone(showBackButton())
        back?.setOnClickListener { finish() }
        getColorDrawable()?.let {
            back?.imageTintMode = PorterDuff.Mode.SRC_IN
            back?.imageTintList = ColorStateList.valueOf(it)
        }

        initUi()
        initToolbarButtons()

        toolbarTitle.text = getName()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnKeyListener { dialog, keyCode, event ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                onBackButtonPressed()
                return@setOnKeyListener true
            }

            return@setOnKeyListener false
        }
        return dialog
    }

    protected fun onBackButtonPressed() {
        finish()
    }

    abstract val contentViewLayoutResId: Int

    abstract fun getName(): String

    abstract fun enableSwipeToRefresh(): Boolean

    abstract fun initUi()

    open fun onSwipeToRefresh() {
        swipeToRefresh.isRefreshing = false
    }

    open fun getProgressColor(): Int = R.color.colorPrimary


//    open fun onSuccess() {
//        loadIndicator.stop()
//        progressContainer.gone()
//    }
//
//    open fun onLoading() {
//        loadIndicator.start()
//        progressContainer.show()
//    }
//
//    open fun onError() {
//        loadIndicator.stop()
//        progressContainer.gone()
//    }
//
//    open fun onDone() {
//        loadIndicator.stop()
//        progressContainer.gone()
//    }

    open fun showBackButton(): Boolean = true

    open fun getActionButtons(): ArrayList<ActionButton>? = null

    open fun getColorDrawable(): Int? = null

    protected fun checkPermission(): Boolean {
        return (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED)
    }

    protected fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA),
            PERMISSION_REQUEST_CODE
        )
    }

    protected fun initToolbarButtons() {
        getActionButtons()?.forEach {
            when {
                it.iconRes != null -> {
                    val iv = AppCompatImageView(context)
                    iv.setImageResource(it.iconRes)
                    iv.tag = it.id
                    iv.background = getDrawable(R.drawable.ripple)
                    iv.adjustViewBounds = true
                    iv.setOnClickListener(it.callback)
                    iv.setPaddingOnAllSidesDP(10)
                    actionBarButtonsContainer.addView(iv)
                }

                it.title != null -> {
                    val tv = AppCompatTextView(context)
                    tv.text = it.title
                    tv.tag = it.id
                    tv.background = getDrawable(R.drawable.ripple)
                    tv.setTextColor(Color.parseColor("#004A8E"))
                    tv.setOnClickListener(it.callback)
                    tv.setPaddingOnAllSidesDP(5)
                    tv.setHorizontalMarginDP(10)

                    actionBarButtonsContainer.addView(tv)
                }
            }
        }
    }

    fun getDrawable(@DrawableRes res: Int) = ResourcesCompat.getDrawable(resources, res, null)

    fun finish() = activity?.finish()

    fun finishWithResult() {
        activity?.apply {
            setResult(RESULT_OK)
            finish()
        }
    }

    fun finishWithResult(data: Intent) {
        activity?.apply {
            setResult(RESULT_OK, data)
            finish()
        }
    }

    fun hideKeyboard(textInput: TextInputEditText) {
        (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(textInput.windowToken, 0)
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getSerializableExtra(key: String): T? {
        return (activity?.intent?.getSerializableExtra(key) as? T)
    }

    fun getString(key: String): String? {
        return activity?.intent?.getStringExtra(key)
    }
}