package app.bunq2ynab.bunq.connect

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.bunq2ynab.bunq.connect.ConnectBunqViewModel.ViewModelParams
import app.bunq2ynab.domain.model.Event
import app.bunq2ynab.domain.usecase.bunq.CreateBunqApiContextUseCase
import app.bunq2ynab.domain.usecase.bunq.ExchangeBunqOAuthTokenUseCase
import app.bunq2ynab.domain.usecase.bunq.GetBunqOAuthAuthorizationRequestUrlUseCase
import app.bunq2ynab.domain.usecase.bunq.GetBunqOAuthAuthorizationRequestUrlUseCase.UseCaseParams
import app.bunq2ynab.utils.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val REDIRECT_URI = "https://bunq2ynab.app/connect/bunq"

class ConnectBunqViewModel @ViewModelInject constructor(
    private val getBunqOAuthAuthorizationRequestUrlUseCase: GetBunqOAuthAuthorizationRequestUrlUseCase,
    private val exchangeBunqOAuthTokenUseCase: ExchangeBunqOAuthTokenUseCase,
    private val createBunqApiContextUseCase: CreateBunqApiContextUseCase
) : BaseViewModel<ViewModelParams>() {

    private val _openOAuthFlowEvent = MutableLiveData<Event<String>>()
    val openOAuthFlowEvent: LiveData<Event<String>>
        get() = _openOAuthFlowEvent

    override fun start(params: ViewModelParams) {
        params.code?.let {
            viewModelScope.launch {
                exchangeBunqOAuthTokens(it, params.state ?: "")
            }
        }
    }

    fun onConnectClicked() {
        viewModelScope.launch {
            createBunqApiContextUseCase(CreateBunqApiContextUseCase.UseCaseParams("")).collect()



//            val result = getBunqOAuthAuthorizationRequestUrlUseCase(
//                UseCaseParams(
//                    redirectUri = REDIRECT_URI
//                )
//            )
//            result.fold(
//                success = { url ->
//                    _openOAuthFlowEvent.postValue(Event(url))
//                },
//                failure = {
//
//                }
//            )

        }
    }

    private suspend fun exchangeBunqOAuthTokens(
        code: String,
        state: String
    ) {
        val result = exchangeBunqOAuthTokenUseCase(
            ExchangeBunqOAuthTokenUseCase.UseCaseParams(
                redirectUri = REDIRECT_URI,
                authorizationCode = code,
                state = state
            )
        )
        result.fold(
            success = {
                Log.d("ConnectBunqViewModel", "success")
            },
            failure = {
                Log.d("ConnectBunqViewModel", "failure")
            }
        )
    }

    data class ViewModelParams(
        val code: String?,
        val state: String?
    )
}
