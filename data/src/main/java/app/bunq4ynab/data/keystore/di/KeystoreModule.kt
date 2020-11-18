package app.bunq4ynab.data.keystore.di

import app.bunq4ynab.data.keystore.KeyStoreRepositoryImpl
import app.bunq4ynab.domain.repository.KeyStoreRepository
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
