package app.bunq4ynab.utils

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<in Params> : ViewModel() {

    abstract fun start(params: Params)
}

internal typealias NoParams = Unit
