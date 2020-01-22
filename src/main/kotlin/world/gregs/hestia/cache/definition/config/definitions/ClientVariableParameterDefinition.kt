package world.gregs.hestia.cache.definition.config.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.network.packet.Packet

class ClientVariableParameterDefinition : Definition {
    var anInt3208 = 1
    var aChar3210 = 0.toChar()

    override fun readValues(opcode: Int, packet: Packet, member: Boolean) {
        if (opcode == 1) {
            aChar3210 = byteToChar(packet.readByte().toByte())
        } else if (opcode == 2) {
            anInt3208 = 0
        }
    }
}