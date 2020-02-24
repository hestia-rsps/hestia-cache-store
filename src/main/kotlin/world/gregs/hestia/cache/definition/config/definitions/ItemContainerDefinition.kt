package world.gregs.hestia.cache.definition.config.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.io.Reader

class ItemContainerDefinition : Definition {

    var length = 0
    var ids: IntArray? = null
    var amounts: IntArray? = null

    override fun readValues(opcode: Int, buffer: Reader, member: Boolean) {
        when(opcode) {
            2 -> length = buffer.readUnsignedShort()
            4 -> {
                val size = buffer.readUnsignedByte()
                ids = IntArray(size)
                amounts = IntArray(size)
                repeat(size) { i ->
                    ids!![i] = buffer.readUnsignedShort()
                    amounts!![i] = buffer.readUnsignedShort()
                }
            }
        }
    }

}