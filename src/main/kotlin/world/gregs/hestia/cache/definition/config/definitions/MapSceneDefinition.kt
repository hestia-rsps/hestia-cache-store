package world.gregs.hestia.cache.definition.config.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.io.Reader

class MapSceneDefinition : Definition {
    var colour = 0
    var sprite = 0
    var aBoolean1741 = false

    override fun readValues(opcode: Int, buffer: Reader, member: Boolean) {
        when (opcode) {
            1 -> sprite = buffer.readShort()
            2 -> colour = buffer.readMedium()
            3 -> aBoolean1741 = true
            4 -> sprite = -1
        }
    }
}