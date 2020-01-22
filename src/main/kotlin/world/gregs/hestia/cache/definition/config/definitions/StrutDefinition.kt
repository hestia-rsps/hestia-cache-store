package world.gregs.hestia.cache.definition.config.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.network.packet.Packet

class StrutDefinition : Definition {

    var params: HashMap<Long, Any>? = null

    override fun readValues(opcode: Int, packet: Packet, member: Boolean) {
        if (opcode == 249) {
            val length = packet.readUnsignedByte()
            if (params == null) {
                val initialCapacity = calculateCapacity(length)
                params = HashMap(initialCapacity)
            }
            repeat(length) {
                val isString = packet.readUnsignedBoolean()
                val id = packet.readMedium()
                params!![id.toLong()] = if (isString) {
                    packet.readString()
                } else {
                    packet.readInt()
                }
            }
        }
    }
}