package com.esketit.myapp.managers

import android.content.Context
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.widget.Button
import com.esketit.myapp.R

class ThemesManager{

    var currentTheme = Theme.LIGHT


    fun customizeToolbar(context: Context, toolbar: Toolbar){
        when(currentTheme){
            Theme.DARK -> { toolbarDark(context, toolbar)}
            Theme.LIGHT -> { toolbarLight(context, toolbar) }
        }
    }

    fun customizeButton(context: Context, btn: Button){
        when(currentTheme){
            Theme.DARK -> { buttonDark(context, btn)}
            Theme.LIGHT -> { buttonLight(context, btn) }
        }
    }

    fun customizeTextInputLayout(context: Context, inputLayout: TextInputLayout){
        when(currentTheme){
            Theme.DARK -> { textInputLayoutDark(context, inputLayout) }
            Theme.LIGHT -> { textInputLayoutLight(context, inputLayout) }
        }
    }

    fun customizeTextInputEditText(context: Context, editText: TextInputEditText){
        when(currentTheme){
            Theme.DARK -> { textInputEditTextDark(context, editText) }
            Theme.LIGHT -> { textInputEditTextLight(context, editText) }
        }
    }

    /*   DARK   */

    private fun toolbarDark(context: Context, toolbar: Toolbar){
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.gray))
        toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.white))
    }

    private fun buttonDark(context: Context, btn: Button){
        btn.background = context.getDrawable(R.drawable.background_dark_button)
        btn.setTextColor(ContextCompat.getColor(context, R.color.white))
        btn.isAllCaps = false
    }

    private fun textInputLayoutDark(context: Context, inputLayout: TextInputLayout){
        inputLayout.background = context.getDrawable(R.drawable.background_dark_edit_text)
        inputLayout.defaultHintTextColor = ContextCompat.getColorStateList(context, R.color.white)
    }

    private fun textInputEditTextDark(context: Context, editText: TextInputEditText){
        editText.setTextColor(ContextCompat.getColor(context, R.color.white))
    }

    /*   LIGHT   */

    private fun toolbarLight(context: Context, toolbar: Toolbar){
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.blue))
        toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.white))
    }

    private fun buttonLight(context: Context, btn: Button){
        btn.background = context.getDrawable(R.drawable.background_light_button)
        btn.setTextColor(ContextCompat.getColor(context, R.color.white))
        btn.isAllCaps = false
    }

    private fun textInputLayoutLight(context: Context, inputLayout: TextInputLayout){
        inputLayout.background = context.getDrawable(R.drawable.background_light_edit_text)
        inputLayout.defaultHintTextColor = ContextCompat.getColorStateList(context, R.color.white)
    }

    private fun textInputEditTextLight(context: Context, editText: TextInputEditText){
        editText.setTextColor(ContextCompat.getColor(context, R.color.white))
    }




    enum class Theme(var value: Int){
        DARK(1), LIGHT(2)
    }
}
