package world.gregs.hestia.cache.definition.readers

import org.displee.CacheLibrary
import world.gregs.hestia.cache.Indices.INTERFACES
import world.gregs.hestia.cache.definition.DefinitionReader
import world.gregs.hestia.cache.definition.definitions.InterfaceComponentDefinition
import world.gregs.hestia.cache.definition.definitions.InterfaceDefinition
import world.gregs.hestia.io.BufferReader
import java.util.concurrent.ConcurrentHashMap

class InterfaceDefinitionReader(cacheStore: CacheLibrary) : DefinitionReader<InterfaceDefinition> {

    override val index = cacheStore.getIndex(INTERFACES)

    override val cache = ConcurrentHashMap<Int, InterfaceDefinition>()

    override fun create(id: Int, member: Boolean): InterfaceDefinition {
        val size = index.getArchive(id).lastFile.id + 1
        val components = (0 until size).map { i ->
            Pair(i, InterfaceComponentDefinition().apply {
                this.id = i + (id shl 16)
                val data = index.getArchive(i).getFile(id).data
                if (data != null) {
                    readValueLoop(BufferReader(data), member)
                }
            })
        }
        return InterfaceDefinition(components.toTypedArray())
    }
}