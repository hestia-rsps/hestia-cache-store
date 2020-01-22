package world.gregs.hestia.cache.definition.config.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.network.packet.Packet

class ItemContainerDefinition : Definition {

    var length = 0
    var ids: IntArray? = null
    var amounts: IntArray? = null

    override fun readValues(opcode: Int, packet: Packet, member: Boolean) {
        when(opcode) {
            2 -> length = packet.readUnsignedShort()
            4 -> {
                val size = packet.readUnsignedByte()
                ids = IntArray(size)
                amounts = IntArray(size)
                repeat(size) { i ->
                    ids!![i] = packet.readUnsignedShort()
                    amounts!![i] = packet.readUnsignedShort()
                }
            }
        }
    }

}