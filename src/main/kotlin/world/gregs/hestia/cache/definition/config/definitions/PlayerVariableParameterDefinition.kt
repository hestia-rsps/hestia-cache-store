package world.gregs.hestia.cache.definition.config.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.io.Reader

class PlayerVariableParameterDefinition : Definition {

    var id = 0

    override fun readValues(opcode: Int, buffer: Reader, member: Boolean) {
        if (opcode == 5) {
            id = buffer.readShort()
        }
    }
}