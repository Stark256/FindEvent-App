package com.esketit.myapp.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import com.esketit.myapp.application.App
import com.esketit.myapp.R


open class BaseDialog: DialogFragment() {

    var positiveBtn: Button? = null
    var negativeBtn: Button? = null
    var titleText: TextView? = null
    var messageText: TextView? = null
    var checkTextView: TextView? = null
    var checkBox: CheckBox? = null

    var title: String? = null
    var message: String? = null
    var positiveText: String? = null
    var negativeText: String? = null
    var checkText: String? = null
    var negativeAction: ((View) -> Unit)? = null
    var positiveAction: ((View) -> Unit)? = null
    var cancelAction: ((View) -> Unit)? = null
    var checkAction: ((View, Boolean) -> Unit)?= null

    @LayoutRes
    private var resource: Int = R.layout.dialog_base

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(true)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val view = inflater.inflate(getLayoutResource(), container, false)
        initView(view)

        return view
    }

    fun getLayoutResource(): Int{
        return resource
    }

    class Builder {
        private val baseDialog: BaseDialog =
            BaseDialog()

        fun setOnCheckListener(checkText: String, checkAction: ((View, Boolean) -> Unit)?): Builder {
            baseDialog.checkText = checkText
            baseDialog.checkAction = checkAction
            return this
        }

        fun setPositiveButton(@StringRes textId: Int, positiveAction: ((View) -> Unit)?): Builder {
            return setPositiveButton(App.instance.getString(textId), positiveAction)
        }

        fun setPositiveButton(text: String, positiveAction: ((View) -> Unit)?): Builder {
            baseDialog.positiveText = text
            baseDialog.positiveAction = positiveAction
            return this
        }

        fun setNegativeButton(@StringRes textId: Int, negativeAction: ((View) -> Unit)?): Builder {
            return setNegativeButton(App.instance.getString(textId), negativeAction)
        }

        fun setNegativeButton(text: String, negativeAction: ((View) -> Unit)?): Builder {
            baseDialog.negativeText = text
            baseDialog.negativeAction = negativeAction
            return this
        }

        fun setOnCancelListener(cancelAction: ((View) -> Unit)?): Builder {
            baseDialog.cancelAction = cancelAction
            return this
        }

        fun setMessage(text: String): Builder {
            baseDialog.message = text
            return this
        }

        fun setMessage(@StringRes textId: Int): Builder {
            return setMessage(App.instance.getString(textId))
        }

        fun setTitle(title: String): Builder {
            baseDialog.title = title
            return this
        }

        fun setTitle(@StringRes textId: Int): Builder {
            return setTitle(App.instance.getString(textId))
        }

        fun build(): BaseDialog {
            return baseDialog
        }
    }

    protected fun initView(view: View) {
        positiveBtn = view.findViewById(R.id.positiveButton)
        negativeBtn = view.findViewById(R.id.negativeButton)
        titleText = view.findViewById(R.id.titleTextView)
        messageText = view.findViewById(R.id.messageTextView)
        checkTextView = view.findViewById(R.id.checkboxTextView)
        checkBox = view.findViewById(R.id.checkbox)

        if (positiveAction == null) {
            positiveBtn?.visibility = View.INVISIBLE
        } else {
            positiveBtn?.text = positiveText
            positiveBtn?.setOnClickListener { positiveAction?.invoke(view) }
        }


        if (negativeAction == null) {
            negativeBtn?.visibility = View.INVISIBLE
        } else {
            negativeBtn?.text = negativeText
            negativeBtn?.setOnClickListener { negativeAction?.invoke(view) }

            titleText?.text = title
            messageText?.text = message

        }

        if(checkAction == null){
            checkBox?.visibility = View.GONE
            checkTextView?.visibility = View.GONE
        }else{
            checkTextView?.text = checkText
            checkBox?.setOnCheckedChangeListener { _view, isChecked -> checkAction?.invoke(_view, isChecked) }
        }
    }

}