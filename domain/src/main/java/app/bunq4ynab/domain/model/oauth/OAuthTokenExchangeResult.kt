package app.bunq4ynab.domain.model.oauth

data class OAuthTokenExchangeResult(
    val accessToken: String,
    val tokenType: TokenType,
    val state: String
){
    enum class TokenType{
        Bearer
    }
}
