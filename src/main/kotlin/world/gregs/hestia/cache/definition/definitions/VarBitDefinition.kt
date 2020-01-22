package world.gregs.hestia.cache.definition.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.network.packet.Packet

class VarBitDefinition : Definition {
    var leastSignificantBit = 0
    var mostSignificantBit = 0
    var index = 0

    override fun readValues(opcode: Int, packet: Packet, member: Boolean) {
        if (opcode == 1) {
            index = packet.readShort()
            leastSignificantBit = packet.readUnsignedByte()
            mostSignificantBit = packet.readUnsignedByte()
        }
    }
}