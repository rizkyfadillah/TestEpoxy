package com.rizkyfadillah.testepoxy

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass(layout = R.layout.view_holder_item)
abstract class CustomViewHolder : EpoxyModelWithHolder<Holder>() {

    @EpoxyAttribute
    lateinit var model1: Model1

    override fun bind(holder: Holder) {
        holder.text1.text = model1.text1
        holder.text2.text = model1.text2
    }

}

class Holder : EpoxyHolder() {

    lateinit var text1: TextView
    lateinit var text2: TextView

    override fun bindView(itemView: View) {
        text1 = itemView.findViewById(R.id.text1)
        text2 = itemView.findViewById(R.id.text2)
    }

}
