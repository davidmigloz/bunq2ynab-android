package app.bunq2ynab.data.bunq.remote.converter

import com.squareup.moshi.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Wraps a Bunq Response that contains three objects.
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
 *       },
 *       {
 *         "ServerPublicKey": {
 *           "server_public_key": "-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0B....jE+H0bTHkIRyU\n4wIDAQAB\n-----END PUBLIC KEY-----\n"
 *         }
 *       }
 *     ]
 *   }
 * > Output:
 * BunqPair(
 *   first: BunqInstallationIdDto(
 *       id = 2551766
 *   ),
 *   second: BunqInstallationTokenDto(
 *       id = 13171166
 *       created = "2020-10-24 12:36:41.989830"
 *       updated = "2020-10-24 12:36:41.989830"
 *       token = "f4fe70d3076e61b6e....8c26c334ca2ca9a83abaa9118e"
 *   ),
 *   third: BunqInstallationServerPublicKeyDto(
 *       server_public_key = "-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0B....jE+H0bTHkIRyU\n4wIDAQAB\n-----END PUBLIC KEY-----\n"
 *   )
 * )
 */
internal data class BunqTriple<out A, out B, out C>(
    val first: A? = null,
    val second: B? = null,
    val third: C? = null
)

@Suppress("UNCHECKED_CAST")
@Singleton
internal class BunqTripleAdapterFactory @Inject constructor() : JsonAdapter.Factory {

    override fun create(
        type: Type,
        annotations: MutableSet<out Annotation>,
        moshi: Moshi
    ): JsonAdapter<*>? {
        return if (type !is ParameterizedType || type.rawType != BunqTriple::class.java || annotations.isNotEmpty()) {
            null
        } else {
            BunqTripleAdapter(
                firstAdapter = moshi.adapter<Any>(
                    Types.newParameterizedType(BunqObjectWrapper::class.java, type.actualTypeArguments[0])
                ) as JsonAdapter<BunqObjectWrapper<Any>>,
                secondAdapter = moshi.adapter<Any>(
                    Types.newParameterizedType(BunqObjectWrapper::class.java, type.actualTypeArguments[1])
                ) as JsonAdapter<BunqObjectWrapper<Any>>,
                thirdAdapter = moshi.adapter<Any>(
                    Types.newParameterizedType(BunqObjectWrapper::class.java, type.actualTypeArguments[2])
                ) as JsonAdapter<BunqObjectWrapper<Any>>
            )
        }
    }

    private class BunqTripleAdapter<A, B, C>(
        private val firstAdapter: JsonAdapter<BunqObjectWrapper<A>>,
        private val secondAdapter: JsonAdapter<BunqObjectWrapper<B>>,
        private val thirdAdapter: JsonAdapter<BunqObjectWrapper<C>>
    ) : JsonAdapter<BunqTriple<A, B, C>>() {

        override fun fromJson(reader: JsonReader): BunqTriple<A, B, C>? {
            var first: BunqObjectWrapper<A>? = null
            var second: BunqObjectWrapper<B>? = null
            var third: BunqObjectWrapper<C>? = null
            reader.beginArray()
            if (reader.hasNext()) first = firstAdapter.fromJson(reader)
            if (reader.hasNext()) second = secondAdapter.fromJson(reader)
            if (reader.hasNext()) third = thirdAdapter.fromJson(reader)
            while (reader.hasNext()) reader.skipValue()
            reader.endArray()
            return BunqTriple(first?.obj, second?.obj, third?.obj)
        }

        override fun toJson(writer: JsonWriter, value: BunqTriple<A, B, C>?) {
            throw UnsupportedOperationException()
        }
    }
}
