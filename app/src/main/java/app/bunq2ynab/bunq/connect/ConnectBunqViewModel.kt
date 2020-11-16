package app.bunq2ynab.bunq.connect

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import app.bunq2ynab.bunq.connect.ConnectBunqViewModel.ViewModelParams
import app.bunq2ynab.domain.model.Event
import app.bunq2ynab.domain.usecase.bunq.ApiContextState
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

    val connectionState = MutableLiveData<String>()

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
        viewModelScope.launch { startBunqOAuthFlow() }
    }

    private suspend fun startBunqOAuthFlow() {
        val result = getBunqOAuthAuthorizationRequestUrlUseCase(UseCaseParams(REDIRECT_URI))
        result.fold(
            success = { url ->
                _openOAuthFlowEvent.postValue(Event(url))
            },
            failure = {
                Log.d("ConnectBunqViewModel", "getBunqOAuthAuthorizationRequestUrlUseCase(): failure")
                // TODO
            }
        )
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
                createBunqApiContext()
            },
            failure = { e ->
                Log.d("ConnectBunqViewModel", "exchangeBunqOAuthTokenUseCase(): failure")
                // TODO
            }
        )
    }

    private suspend fun createBunqApiContext() {
        createBunqApiContextUseCase().collect { result ->
            result.fold(
                success = { state: ApiContextState ->
                    val stateName = when (state) {
                        is ApiContextState.DeviceRsaKeyPairGeneration -> "Generating secure encryption key"
                        is ApiContextState.InstallationRegistration -> "Registering encryption key"
                        is ApiContextState.DeviceRegistration -> "Registering device"
                        is ApiContextState.SessionOpening -> "Opening a session"
                        is ApiContextState.ApiContextCreated -> "Ready to go!"
                    }
                    connectionState.postValue(stateName)
                },
                failure = { e ->
                    Log.d("ConnectBunqViewModel", "createBunqApiContextUseCase(): failure")
                    connectionState.postValue("Error")
                    // TODO
                }
            )
        }
    }

    data class ViewModelParams(
        val code: String?,
        val state: String?
    )
}
