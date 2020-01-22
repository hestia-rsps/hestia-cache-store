package world.gregs.hestia.cache.definition.readers

import world.gregs.hestia.cache.CacheStore
import world.gregs.hestia.cache.definition.DefinitionReader
import world.gregs.hestia.network.packet.PacketReader
import world.gregs.hestia.cache.definition.definitions.InterfaceComponentDefinition
import world.gregs.hestia.cache.definition.definitions.InterfaceDefinition
import java.util.concurrent.ConcurrentHashMap

class InterfaceDefinitionReader(cacheStore: CacheStore) : DefinitionReader<InterfaceDefinition> {

    override val index = cacheStore.getIndex(3)

    override val cache = ConcurrentHashMap<Int, InterfaceDefinition>()

    override fun create(id: Int, member: Boolean): InterfaceDefinition {
        val size = index.getLastFileId(id) + 1
        val components = (0 until size).map { i ->
            Pair(i, InterfaceComponentDefinition().apply {
                this.id = i + (id shl 16)
                val data = index.getFile(id, i)
                if (data != null) {
                    readValueLoop(PacketReader(data), member)
                }
            })
        }
        return InterfaceDefinition(components.toTypedArray())
    }
}