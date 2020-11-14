package app.bunq2ynab.data.bunq.remote.converter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Bunq objects are wrapped into another object. This class models that wrapping
 * in a generic way, so that the wrapped object can easily be deserialized.
 *
 * E.g.
 * > Input:
 * {
 *   "Token": {
 *     "id": 13171166,
 *     "created": "2020-10-24 12:36:41.989830",
 *     "updated": "2020-10-24 12:36:41.989830",
 *     "token": "f4fe70d3076e61b6e....8c26c334ca2ca9a83abaa9118e"
 *   }
 * }
 * > Output:
 * BunqObjectWrapperAdapter(
 *   obj = BunqInstallationTokenDto(
 *       id = 13171166
 *       created = "2020-10-24 12:36:41.989830"
 *       updated = "2020-10-24 12:36:41.989830"
 *       token = "f4fe70d3076e61b6e....8c26c334ca2ca9a83abaa9118e"
 *   )
 * )
 */
internal data class BunqObjectWrapper<out T>(val obj: T?)

@Singleton
internal class BunqObjectWrapperAdapterFactory @Inject constructor() : JsonAdapter.Factory {

    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        return if (type !is ParameterizedType || type.rawType != BunqObjectWrapper::class.java || annotations.isNotEmpty()) {
            null
        } else {
            BunqObjectWrapperAdapter(
                objectAdapter = moshi.adapter<Any>(type.actualTypeArguments[0]),
            )
        }
    }

    internal class BunqObjectWrapperAdapter<T>(
        private val objectAdapter: JsonAdapter<T>
    ) : JsonAdapter<BunqObjectWrapper<T>>() {

        override fun fromJson(reader: JsonReader): BunqObjectWrapper<T>? {
            return try {
                reader.beginObject()
                reader.skipName()
                val obj = objectAdapter.fromJson(reader)
                reader.endObject()
                BunqObjectWrapper(obj)
            } catch (e: Exception) {
                null
            }
        }

        override fun toJson(writer: JsonWriter, value: BunqObjectWrapper<T>?) {
            throw UnsupportedOperationException()
        }
    }
}
