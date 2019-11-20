package com.rizkyfadillah.testepoxy

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val textView1: TextView
    private val textView2: TextView

    init {
        inflate(context, R.layout.view_custom, this)
        textView1 = (findViewById(R.id.text1))
        textView2 = (findViewById(R.id.text2))
    }

    @ModelProp
    lateinit var model: Model

    @AfterPropsSet
    fun useProps() {
        // This is optional, and is called after the annotated properties above are set.
        // This is useful for using several properties in one method to guarantee they are all set first.
        textView1.text = model.text1
        textView2.text = model.text2
    }

}
