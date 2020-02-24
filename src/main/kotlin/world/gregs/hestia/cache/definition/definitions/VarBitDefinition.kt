package world.gregs.hestia.cache.definition.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.io.Reader

class VarBitDefinition : Definition {
    var leastSignificantBit = 0
    var mostSignificantBit = 0
    var index = 0

    override fun readValues(opcode: Int, buffer: Reader, member: Boolean) {
        if (opcode == 1) {
            index = buffer.readShort()
            leastSignificantBit = buffer.readUnsignedByte()
            mostSignificantBit = buffer.readUnsignedByte()
        }
    }
}