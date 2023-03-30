package com.example.bmiclaculatermui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bmiclaculatermui.BaseObervebal.BaseActivity
import kotlin.math.round

class ViewModelActivity:ViewModel() {
    var value01: MutableLiveData<Float> = MutableLiveData()
    var text: MutableLiveData<String> = MutableLiveData()
    var message: MutableLiveData<String> = MutableLiveData()
    var baseObservable: BaseActivity = BaseActivity()





    fun calculatebmi( ) {
        if (baseObservable.height.isBlank()&& !baseObservable.weight.isBlank()) {
            message.value = "Please enter the height "
        } else if (baseObservable.weight.isBlank()&& !baseObservable.height.isBlank()) {
            message.value = "Please enter the weight"
        }
        else if (baseObservable.height.isBlank() && baseObservable.weight.isBlank()) {
            message.value = "please enter the height & weight "

        }


        else {
            val hieght = baseObservable.height.toInt() / 100
            var BMI = baseObservable.weight.toFloat() / (hieght * hieght)
            var total = (round(BMI * 100) / 100.0)
            value01.value = total.toFloat()// this is the bmi value

            if (total < 18) {
                text.value = "you are under weight"
            } else if (total >= 18 && total < 25) {
                text.value = "you are healthy"
            } else {
                text.value = "you are over weight"
            }


        }
    }
}