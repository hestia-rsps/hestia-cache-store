package world.gregs.hestia.cache.definition.config.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.io.Reader

class ClientVariableParameterDefinition : Definition {
    var anInt3208 = 1
    var aChar3210 = 0.toChar()

    override fun readValues(opcode: Int, buffer: Reader, member: Boolean) {
        if (opcode == 1) {
            aChar3210 = byteToChar(buffer.readByte().toByte())
        } else if (opcode == 2) {
            anInt3208 = 0
        }
    }
}