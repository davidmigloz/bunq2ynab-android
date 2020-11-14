package app.bunq2ynab.data.utils.mapper

internal interface Mapper<in Input, out Output> {

    suspend operator fun invoke(input: Input): Output
}
