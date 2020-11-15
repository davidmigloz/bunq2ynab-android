package app.bunq2ynab.data.bunq.main.remote.mapper

import app.bunq2ynab.data.bunq.main.remote.dto.BunqDeviceRegistrationDto
import app.bunq2ynab.data.utils.mapper.Mapper
import app.bunq2ynab.data.utils.mapper.mappingTerminalError
import app.bunq2ynab.domain.model.bunq.BunqDevice
import javax.inject.Inject

internal class BunqDeviceMapper @Inject constructor() : Mapper<BunqDeviceRegistrationDto, BunqDevice> {

    override suspend fun invoke(
        input: BunqDeviceRegistrationDto
    ) = BunqDevice(
        id = input.id ?: mappingTerminalError("Device id was null")
    )
}
