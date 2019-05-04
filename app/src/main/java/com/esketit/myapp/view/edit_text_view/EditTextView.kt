package com.esketit.myapp.view.edit_text_view

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import com.esketit.myapp.R

class EditTextView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0): LinearLayout(context, attrs, defStyleAttr){

    private val root: LinearLayout? by lazy { findViewById<LinearLayout>(R.id.root_edit_text) }
    private val inputLayout: TextInputLayout? by lazy { findViewById<TextInputLayout>(R.id.text_input_layout) }
    private val editText: TextInputEditText? by lazy { findViewById<TextInputEditText>(R.id.text_input_edit_text) }

    private var validateListener: ValidateListener? = null
    private var clickListener: ClickListener? = null

    val hasError: Boolean
        get() { return inputLayout?.error != null }


    private var imeOption: Int = EditorInfo.IME_ACTION_NEXT

    var text: String?
        get() { return editText?.text?.toString() }
        set(value) { editText?.setText(value) }

    val hasText: Boolean
        get() { return editText?.text?.toString()?.isNotBlank() ?: false }

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.view_edit_text, this, true)
    }

    fun initBuilder(@StringRes hintRes: Int? = null,
                    hintString: String? = null,
                    drawable: Drawable? = null,
                    @DrawableRes drawableRes: Int? = null,
                    @ColorRes colorRes: Int? = null,
                    focusable: Boolean? = null,
                    inputType: Int? = null,
                    imeOption: Int? = null,
                    clickListener: ClickListener? = null,
                    validateListener: ValidateListener? = null) {

        hintRes?.let { setHint(it) }
        hintString?.let { setHint(it) }
        drawable?.let { setRootBackgroundDrawable(it) }
        drawableRes?.let { setRootBackgroundDrawable(it) }
        colorRes?.let { setRootBackgroundColor(it) }
        focusable?.let { setEditFocusable(it) }
        inputType?.let { setInputType(it) }
        imeOption?.let { setImeOption(it) }
        clickListener?.let { setClickListener(it) }
        validateListener?.let { setValidateListener(it) }

    }

    fun setHint(@StringRes hintText: Int) {
        inputLayout?.hint = context.getString(hintText)
    }

    fun setHint(hintString: String) {
        inputLayout?.hint = hintString
    }

    fun setRootBackgroundDrawable(@DrawableRes drawableRes: Int) {
        root?.background = ContextCompat.getDrawable(context, drawableRes)
    }

    fun setRootBackgroundDrawable(drawable: Drawable) {
        root?.background = drawable
    }

    fun setRootBackgroundColor(@ColorRes colorRes: Int) {
        root?.setBackgroundColor(ContextCompat.getColor(context, colorRes))
    }

    fun setEditFocusable(focusable: Boolean) {
        editText?.setFocusable(focusable)
    }


    fun setError(error: String?) {
        inputLayout?.isErrorEnabled = error != null
        inputLayout?.error = error
    }

    fun setInputType(inputType: Int) {
        editText?.inputType = inputType
    }

    fun setImeOption(imeOption: Int) {
        editText?.imeOptions = imeOption
    }

    fun setValidateListener(validateListener: ValidateListener) {
        this.validateListener = validateListener

        editText?.setOnFocusChangeListener { _, hasFocus ->
            validateListener.onFocusChanged(hasFocus)
        }
        editText?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == imeOption) { validateListener.onActionPressed() }
            false
        }
    }

    fun editTextPressed(pressed: ()-> Unit) {
        editText?.setOnClickListener {
            pressed.invoke()
        }
    }

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener

        editText?.setOnClickListener {
            clickListener.editTextPressed()
        }
    }

    fun onFocusChanged(hasFocus: (Boolean) -> Unit) {
        editText?.setOnFocusChangeListener { _, hasFocusR ->
            hasFocus.invoke(hasFocusR)
        }
    }
    fun onActionPressed(pressed: ()-> Unit) {
        editText?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == imeOption) { pressed.invoke() }
            false
        }
    }

    interface ValidateListener {
        fun onFocusChanged(hasFocus: Boolean)
        fun onActionPressed()
    }

    interface ClickListener {
        fun editTextPressed()
    }
}
