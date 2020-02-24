package world.gregs.hestia.cache.definition.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.io.Reader

class InterfaceDefinition(components: Array<Pair<Int, InterfaceComponentDefinition>>) : Map<Int, InterfaceComponentDefinition> by mapOf(*components),
    Definition {

    override fun readValues(opcode: Int, buffer: Reader, member: Boolean) {
    }
}