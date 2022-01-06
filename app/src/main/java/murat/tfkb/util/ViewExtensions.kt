package murat.tfkb

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import java.util.*


object ViewExtensions {

    var alertDialog: AlertDialog? = null
    var loadingDialog: AlertDialog? = null

    fun randomColorHex(): String {
        val random = Random()
        val nextInt: Int = random.nextInt(0xffffff + 1)
        val colorCode = String.format("#%06x", nextInt)

        return colorCode
    }

    // Alert & Dialog
    fun Context.showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    fun Context.showAlert(message: String) {
        hideDialog()

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val alertDialogBuilder: AlertDialog.Builder = builder
            .setTitle(this.getString(R.string.warning))
            .setMessage(message)
        alertDialog = alertDialogBuilder.create()
        alertDialog?.let {
            it.getWindow()?.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            it.window!!.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
            it.window!!.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
            it.show()

        }
    }

    fun Context.showDialog(
        title: String,
        message: String,
        positiveButtonText: String?,
        negativeButtonText: String?,
        positiveButtonCallback: () -> (Unit)?,
        negativeButtonCallback: () -> (Unit)?,
        cancelable: Boolean?
    ) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val alertDialogBuilder: AlertDialog.Builder = builder
            .setTitle(title)
            .setMessage(message)

        cancelable?.let {
            alertDialogBuilder.setCancelable(it)
        }

        if (!positiveButtonText.isNullOrBlank()) {
            alertDialogBuilder.setPositiveButton(positiveButtonText) { _, _ -> positiveButtonCallback() }
        }

        if (!negativeButtonText.isNullOrBlank()) {
            alertDialogBuilder.setNegativeButton(negativeButtonText) { _, _ -> negativeButtonCallback() }
        }

        alertDialog = alertDialogBuilder.create()
        alertDialog?.let {
            it.getWindow()?.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            it.window!!.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
            it.window!!.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
            it.show()

            val negativeButton = it.getButton(AlertDialog.BUTTON_NEGATIVE)
            val positiveButton = it.getButton(AlertDialog.BUTTON_POSITIVE)

            negativeButton.setTextColor(resources.getColor(R.color.text_color))
            positiveButton.setTextColor(resources.getColor(R.color.white))
            positiveButton.setBackgroundColor(resources.getColor(R.color.primary))

        }
    }


    fun Activity.showLoading() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val inflater: LayoutInflater = this.layoutInflater
        builder.setView(inflater.inflate(R.layout.dialog_loading, null))
        builder.setCancelable(false)
        loadingDialog = builder.create()
        loadingDialog?.show()
    }

    fun hideDialog() {
        alertDialog?.let {
            if (it.isShowing) {
                it.hide()
                it.dismiss()
            }
        }

        loadingDialog?.let {
            if (it.isShowing) {
                it.hide()
                it.dismiss()
            }
        }
    }

    // View & View Group
    fun ViewGroup.disableEnableControls(enable: Boolean) {
        this.isEnabled = enable
        //if (!enable) this.alpha = .5f
        for (i in 0 until this.childCount) {
            val child = this.getChildAt(i)
            child.isEnabled = enable
            if (child is ViewGroup) {
                child.disableEnableControls(enable)
            }
        }
    }


    // Keyboard
    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    // View Pager
    fun ViewPager2.reduceDragSensitivity() {
        val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
        recyclerViewField.isAccessible = true
        val recyclerView = recyclerViewField.get(this) as RecyclerView

        val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
        touchSlopField.isAccessible = true
        val touchSlop = touchSlopField.get(recyclerView) as Int
        touchSlopField.set(recyclerView, touchSlop * 6)       // "8" was obtained experimentally
    }

    // Drawable
    @SuppressLint("UseCompatLoadingForDrawables")
    fun getDrawableFromName(c: Context, imageName: String?): Drawable? {
        return c.resources.getDrawable(c.resources.getIdentifier(imageName, "drawable", c.packageName))
    }
}