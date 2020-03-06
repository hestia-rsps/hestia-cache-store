package world.gregs.hestia.cache.definition.readers

import com.displee.cache.CacheLibrary
import world.gregs.hestia.cache.Indices.INTERFACES
import world.gregs.hestia.cache.definition.DefinitionReader
import world.gregs.hestia.cache.definition.definitions.InterfaceComponentDefinition
import world.gregs.hestia.cache.definition.definitions.InterfaceDefinition
import world.gregs.hestia.io.BufferReader
import java.util.concurrent.ConcurrentHashMap

class InterfaceDefinitionReader(cacheStore: CacheLibrary) : DefinitionReader<InterfaceDefinition> {

    override val index = cacheStore.index(INTERFACES)

    override val cache = ConcurrentHashMap<Int, InterfaceDefinition>()

    override fun create(id: Int, member: Boolean): InterfaceDefinition {
        val size = (index.archive(id)?.last()?.id ?: 0) + 1
        val components = (0 until size).map { i ->
            Pair(i, InterfaceComponentDefinition().apply {
                this.id = i + (id shl 16)
                val data = index.archive(id)?.file(i)?.data
                if (data != null) {
                    readValueLoop(BufferReader(data), member)
                }
            })
        }
        return InterfaceDefinition(components.toTypedArray())
    }
}