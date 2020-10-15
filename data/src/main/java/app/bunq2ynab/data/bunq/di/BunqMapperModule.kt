package app.bunq2ynab.data.bunq.di

import app.bunq2ynab.data.bunq.remote.mapper.BunqDataMappersFacade
import app.bunq2ynab.data.bunq.remote.mapper.OAuthTokenExchangeResultMapper
import app.bunq2ynab.data.bunq.remote.mapper.oAuthTokenExchangeResultMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Note: @JvmSuppressWildcards is required to be able to provide lambdas.
 */
@Module
@InstallIn(SingletonComponent::class)
internal class BunqMapperModule {

    @JvmSuppressWildcards
    @Provides
    fun provideOAuthTokenExchangeResultMapper(): OAuthTokenExchangeResultMapper = { oAuthTokenExchangeResultMapper(it) }

    @JvmSuppressWildcards
    @Provides
    fun provideBunqDataMappersFacade(
        oAuthTokenExchangeResultMapper: OAuthTokenExchangeResultMapper
    ) = BunqDataMappersFacade(
        oAuthTokenExchangeResultMapper = oAuthTokenExchangeResultMapper
    )
}
