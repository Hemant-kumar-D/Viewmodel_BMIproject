package com.example.bmiclaculatermui.View_model

import android.icu.text.DateTimePatternGenerator.DisplayWidth
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
//import kotlinx.coroutines.NonCancellable.message
import kotlin.math.round


class ViewmodelMainActivity:ViewModel() {
     var value01:MutableLiveData<Float> = MutableLiveData()
    var text:MutableLiveData<String> = MutableLiveData()





    fun calculatebmi( hi:Double, wi:Int) {
        val hieght:Double = hi / 100
        var  BMI = wi / (hieght * hieght)
        var total= (round(BMI * 100) / 100.0)
        value01.value =total.toFloat()// this is the bmi value

        if (total < 18) {
             text.value = "you are under weight"
        } else if (total >= 18 && total < 25) {
             text.value = "you are healthy"
        } else  {
             text.value = "you are over weight"
        }


    }





}