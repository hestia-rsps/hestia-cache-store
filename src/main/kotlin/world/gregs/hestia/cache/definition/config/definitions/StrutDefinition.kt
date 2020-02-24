package world.gregs.hestia.cache.definition.config.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.io.Reader

class StrutDefinition : Definition {

    var params: HashMap<Long, Any>? = null

    override fun readValues(opcode: Int, buffer: Reader, member: Boolean) {
        if (opcode == 249) {
            val length = buffer.readUnsignedByte()
            if (params == null) {
                val initialCapacity = calculateCapacity(length)
                params = HashMap(initialCapacity)
            }
            repeat(length) {
                val isString = buffer.readUnsignedBoolean()
                val id = buffer.readMedium()
                params!![id.toLong()] = if (isString) {
                    buffer.readString()
                } else {
                    buffer.readInt()
                }
            }
        }
    }
}