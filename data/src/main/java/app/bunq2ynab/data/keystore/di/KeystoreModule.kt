package app.bunq2ynab.data.keystore.di

import app.bunq2ynab.data.keystore.KeyStoreRepositoryImpl
import app.bunq2ynab.domain.repository.KeyStoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
internal abstract class KeystoreModule {

    @Singleton
    @Binds
    abstract fun bindBunqRepository(
        impl: KeyStoreRepositoryImpl
    ): KeyStoreRepository
}
