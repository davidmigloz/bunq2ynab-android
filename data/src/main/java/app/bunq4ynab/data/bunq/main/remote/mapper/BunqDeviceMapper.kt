package app.bunq4ynab.data.bunq.main.remote.mapper

import app.bunq4ynab.data.bunq.main.remote.dto.BunqDeviceRegistrationDto
import app.bunq4ynab.data.utils.mapper.Mapper
import app.bunq4ynab.data.utils.mapper.mappingTerminalError
import app.bunq4ynab.domain.model.bunq.BunqDevice
import javax.inject.Inject

internal class BunqDeviceMapper @Inject constructor() : Mapper<BunqDeviceRegistrationDto, BunqDevice> {

    override suspend fun invoke(
        input: BunqDeviceRegistrationDto
    ) = BunqDevice(
        id = input.id ?: mappingTerminalError("Device id was null")
    )
}
