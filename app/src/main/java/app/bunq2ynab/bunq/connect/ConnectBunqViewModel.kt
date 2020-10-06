package app.bunq2ynab.bunq.connect

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.bunq2ynab.domain.model.Event
import app.bunq2ynab.domain.usecase.bunq.GetBunqOAuthAuthorizationRequestUrlUseCase
import app.bunq2ynab.domain.usecase.bunq.GetBunqOAuthAuthorizationRequestUrlUseCase.Params
import kotlinx.coroutines.launch

class ConnectBunqViewModel @ViewModelInject constructor(
   private val getBunqOAuthAuthorizationRequestUrlUseCase: GetBunqOAuthAuthorizationRequestUrlUseCase
) : ViewModel() {

    private val _openOAuthFlowEvent = MutableLiveData<Event<String>>()
    val openOAuthFlowEvent: LiveData<Event<String>>
        get() = _openOAuthFlowEvent

    fun start(code: String?, state: String?) {
        Log.d("ConnectBunqViewModel", "code:$code state:$state")
    }

    fun onConnectClicked() {
        viewModelScope.launch {
            val result = getBunqOAuthAuthorizationRequestUrlUseCase(Params("https://bunq2ynab.app/connect/bunq"))
            result.fold(
                success = { url ->
                    _openOAuthFlowEvent.postValue(Event(url))
                },
                failure = {

                }
            )

        }
    }
}
