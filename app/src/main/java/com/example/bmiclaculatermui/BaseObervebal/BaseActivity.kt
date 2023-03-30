package com.example.bmiclaculatermui.BaseObervebal

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR


class BaseActivity:BaseObservable()
{
    @get:Bindable
    var height: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.height)
            //notifyPropertyChanged(BR.height)
        }

    @get:Bindable
    var weight: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.weight)

        }

}