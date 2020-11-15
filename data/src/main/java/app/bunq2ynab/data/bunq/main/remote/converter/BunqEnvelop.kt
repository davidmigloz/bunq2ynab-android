package app.bunq2ynab.data.bunq.main.remote.converter

import com.squareup.moshi.*
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Envelop to unwrap the (painful) Bunq API responses and easily map them to objects.
 *
 * All Bunq API JSON responses have one top level object that can be:
 * - Response: the value is always an array, even for responses that only contain one object. E.g.:
 *   {
 *     "Response": [
 *       {
 *         "Id": {
 *           "id": 2551766
 *         }
 *       },
 *       {
 *         "Token": {
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
 *
 * When defining the endpoint use:
 *  + Response<ObjectDto>: if the response array only contains one object.
 *  + Response<BunqPair<Object1Dto, Object2Dto>>: if the response array contains two objects.
 *  + Response<BunqTripe<Object1Dto, Object2Dto, Object3Dto>>: if the response array contains three objects.
 *
 * - Error: The contents of the array is a JSON object with the error_description and error_description_translated fields.
 *   {
 *       "Error": [
 *           {
 *               "error_description": "Error description in the English language",
 *               "error_description_translated": "User facing error description automatically translated into the language specified in the X-Bunq-Language header"
 *           }
 *       ]
 *   }
 *
 * Object Type indications: When the API returns different types of objects for the same field, they
 * will be nested in another JSON object that includes a specific field for each one of them. Within
 * bunq SDKs a BunqResponse object will be returned as the top level object.
 *
 * Docs: https://beta.doc.bunq.com/basics/api-conventions
 */
internal data class BunqEnvelop<out T>(
    val response: T?,
    val error: BunqError?
)

internal data class BunqError(
    val error_description: String,
    val error_description_translated: String
)

@Suppress("UNCHECKED_CAST")
@Singleton
internal class BunqEnvelopFactory @Inject constructor() : JsonAdapter.Factory {

    override fun create(
        type: Type,
        annotations: MutableSet<out Annotation>,
        moshi: Moshi
    ): JsonAdapter<*>? {
        return if (type !is ParameterizedType || type.rawType != BunqEnvelop::class.java || annotations.isNotEmpty()) {
            null
        } else {
            BunqEnvelopAdapter(
                responseAdapter = moshi.adapter(type.actualTypeArguments[0]),
                objectWrapperAdapter = moshi.adapter<Any>(
                    Types.newParameterizedType(BunqObjectWrapper::class.java, type.actualTypeArguments[0])
                ) as JsonAdapter<BunqObjectWrapper<Any>>
            )
        }
    }

    internal class BunqEnvelopAdapter<T>(
        private val responseAdapter: JsonAdapter<T>,
        private val objectWrapperAdapter: JsonAdapter<BunqObjectWrapper<T>>
    ) : JsonAdapter<BunqEnvelop<T>>() {

        override fun fromJson(reader: JsonReader): BunqEnvelop<T>? {
            return try {
                var response: T? = null
                reader.beginObject()
                while (reader.hasNext()) {
                    when (reader.nextName()) {
                        "Response" -> {
                            val previewResponseList = reader.peekJson().readJsonValue() as List<*>
                            response = when (previewResponseList.size) {
                                0 -> null  // Empty list -> treat it as null
                                1 -> { // Only one object -> unwrap object
                                    reader.beginArray()
                                    val wrapper = objectWrapperAdapter.fromJson(reader)
                                    reader.endArray()
                                    wrapper?.obj
                                }
                                2, 3 -> responseAdapter.fromJson(reader) // BunqPair or BunqTriple
                                else -> error("Response with >3 objects not supported")
                            }
                        }
                        "Error" -> {
                            reader.skipValue() // TODO
                        }
                        else -> {
                            reader.skipValue()
                        }
                    }
                }
                reader.endObject()
                BunqEnvelop(response = response, error = null)
            } catch (e: Exception) {
                null
            }
        }

        override fun toJson(writer: JsonWriter, value: BunqEnvelop<T>?) {
            throw UnsupportedOperationException()
        }
    }
}

@Singleton
class EnvelopConverter @Inject constructor() : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        val envelopeType = Types.newParameterizedType(BunqEnvelop::class.java, type)
        val delegate: Converter<ResponseBody, BunqEnvelop<*>> =
            retrofit.nextResponseBodyConverter(this, envelopeType, annotations)
        return Converter<ResponseBody, Any?> { body ->
            val envelope = delegate.convert(body) ?: return@Converter null
            return@Converter envelope.response
        }
    }
}
