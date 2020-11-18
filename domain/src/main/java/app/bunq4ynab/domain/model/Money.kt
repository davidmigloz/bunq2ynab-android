package app.bunq4ynab.domain.model

import android.icu.math.BigDecimal
import android.icu.util.Currency

data class Money(
    val amount: BigDecimal,
    val currency: Currency
) : Comparable<Money> {

    override fun compareTo(other: Money) = this.amount.compareTo(other.amount)

}