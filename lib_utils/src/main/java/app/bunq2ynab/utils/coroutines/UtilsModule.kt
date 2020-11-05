package app.bunq2ynab.utils.coroutines

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UtilsModule {

    @Singleton
    @Binds
    abstract fun bindCoroutineContextProvider(
        impl: CoroutineContextProvider.Default
    ): CoroutineContextProvider
}
