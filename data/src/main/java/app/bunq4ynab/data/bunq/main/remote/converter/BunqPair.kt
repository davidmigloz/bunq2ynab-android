package app.bunq4ynab.data.bunq.main.remote.converter

import com.squareup.moshi.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Wraps a Bunq Response that contains two objects.
 *
 * E.g.
 * > Input
 *   {
 *     "Response": [
 *       {
 *         "Id": {
 *           "id": 2551766
 *         }
 *       },
 *       {
 *         "Token": {
 *           "id": 13171166,
 *           "created": "2020-10-24 12:36:41.989830",
 *           "updated": "2020-10-24 12:36:41.989830",
 *           "token": "f4fe70d3076e61b6e....8c26c334ca2ca9a83abaa9118e"
 *         }
 *       }
 *     ]
 *   }
 * > Output:
 * BunqPair(
 *   first: BunqInstallationIdDto(
 *     id = 2551766
 *   ),
 *   second: BunqInstallationTokenDto(
 *       id = 13171166
 *       created = "2020-10-24 12:36:41.989830"
 *       updated = "2020-10-24 12:36:41.989830"
 *       token = "f4fe70d3076e61b6e....8c26c334ca2ca9a83abaa9118e"
 *   )
 * )
 */
internal data class BunqPair<out A, out B>(
    val first: A? = null,
    val second: B? = null
)

@Suppress("UNCHECKED_CAST")
@Singleton
internal class BunqPairAdapterFactory @Inject constructor() : JsonAdapter.Factory {

    override fun create(
        type: Type,
        annotations: MutableSet<out Annotation>,
        moshi: Moshi
    ): JsonAdapter<*>? {
        return if (type !is ParameterizedType || type.rawType != BunqPair::class.java || annotations.isNotEmpty()) {
            null
        } else {
            BunqPairAdapter(
                firstAdapter = moshi.adapter<Any>(
                    Types.newParameterizedType(BunqObjectWrapper::class.java, type.actualTypeArguments[0])
                ) as JsonAdapter<BunqObjectWrapper<Any>>,
                secondAdapter = moshi.adapter<Any>(
                    Types.newParameterizedType(BunqObjectWrapper::class.java, type.actualTypeArguments[1])
                ) as JsonAdapter<BunqObjectWrapper<Any>>
            )
        }
    }

    private class BunqPairAdapter<A, B>(
        private val firstAdapter: JsonAdapter<BunqObjectWrapper<A>>,
        private val secondAdapter: JsonAdapter<BunqObjectWrapper<B>>
    ) : JsonAdapter<BunqPair<A, B>>() {

        override fun fromJson(reader: JsonReader): BunqPair<A, B>? {
            var first: BunqObjectWrapper<A>? = null
            var second: BunqObjectWrapper<B>? = null
            reader.beginArray()
            if (reader.hasNext()) first = firstAdapter.fromJson(reader)
            if (reader.hasNext()) second = secondAdapter.fromJson(reader)
            while (reader.hasNext()) reader.skipValue()
            reader.endArray()
            return BunqPair(first?.obj, second?.obj)
        }

        override fun toJson(writer: JsonWriter, value: BunqPair<A, B>?) {
            throw UnsupportedOperationException()
        }
    }
}
