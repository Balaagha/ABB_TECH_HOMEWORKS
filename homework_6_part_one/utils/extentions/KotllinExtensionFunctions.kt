package com.example.todoapp.utils.extentions

import android.os.Build
import android.text.Editable
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.view.animation.Animation
import androidx.core.text.HtmlCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.data.base.models.ApiErrorResponseModel
import com.example.data.base.models.FailureBehavior
import com.example.data.base.models.FailureType
import com.example.data.base.models.ModelWrapper
import retrofit2.Response

fun <T>checkNetworkResultIsSuccess(data: Response<T>?): Boolean {
    return when(val code = data?.code()) {
        in 200 .. 299 -> {
            when (val resultBody = data?.body()) {
                null -> {
                    false
                }
                is List<*> -> {
                    (resultBody as List<*>).isNotEmpty()
                }
                else -> {
                    true
                }
            }
        }
        else -> {
            false
        }
    }
}

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            removeObserver(this)
            observer.onChanged(t)
        }
    })
}

fun Animation.setOnAnimationEnd(function: () -> Unit) {
    this.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {
            //not used
        }

        override fun onAnimationEnd(animation: Animation?) {
            function.invoke()
        }

        override fun onAnimationStart(animation: Animation?) {
            //not used
        }
    })
}

fun String.toEditable(): Editable = Editable.Factory
    .getInstance().newEditable(this)

@Suppress("DEPRECATION")
fun String?.convertHtmlTagToLink(htmlFlag: Int): Spanned {
    return when {
        this.isNullOrEmpty() -> {
            SpannableString("")
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
            HtmlCompat.fromHtml(
                this.replace("\n", "<br />"),
                htmlFlag
            )
        }
        else -> {
            Html.fromHtml(this.replace("\n", "<br />"))
        }
    }
}
