package app.bunq2ynab.utils.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface CoroutineContextProvider {

    /**
     * Optimized for CPU intensive work off the main thread.
     * This is the default dispatcher used by all coroutine builders if no dispatcher is specified.
     * It is backed by a thread-pool with as many threads as there are CPU cores in the system,
     * making sure that CPU-bound code can saturate all physical resources as needed.
     * Use this to: sort a list, parse a JSON, DiffUtils, etc.
     */
    val default: CoroutineDispatcher
        get() = Dispatchers.Default

    /**
     * Main thread on Android.
     * Use this to: interact with the UI and perform light work.
     */
    val main: CoroutineDispatcher
        get() = Dispatchers.Main

    /**
     * Optimized for disk and network IO off the main thread.
     * IO-bound code does not actually consume CPU resources. IO dispatcher allocates additional threads
     * on top of the ones allocated to the default dispatcher, so we can do blocking IO and fully utilize
     * machine’s CPU resources at the same time. It is backed by a shared pool of thread configured to
     * support 64 concurrent operations.
     * Use this to: database access, reading/writing files, networking, etc.
     */
    val io: CoroutineDispatcher
        get() = Dispatchers.IO

    class Default @Inject constructor() : CoroutineContextProvider
}
