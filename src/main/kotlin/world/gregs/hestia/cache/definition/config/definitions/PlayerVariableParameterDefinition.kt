package world.gregs.hestia.cache.definition.config.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.network.packet.Packet

class PlayerVariableParameterDefinition : Definition {

    var id = 0

    override fun readValues(opcode: Int, packet: Packet, member: Boolean) {
        if (opcode == 5) {
            id = packet.readShort()
        }
    }
}