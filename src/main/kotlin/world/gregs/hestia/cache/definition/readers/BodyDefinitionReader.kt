package world.gregs.hestia.cache.definition.readers

import world.gregs.hestia.cache.CacheStore
import world.gregs.hestia.cache.definition.DefinitionReader
import world.gregs.hestia.cache.definition.definitions.BodyDefinition
import world.gregs.hestia.network.packet.PacketReader
import java.util.concurrent.ConcurrentHashMap

class BodyDefinitionReader(cacheStore: CacheStore) : DefinitionReader<BodyDefinition> {

    override val index = cacheStore.getIndex(28)

    override val cache = ConcurrentHashMap<Int, BodyDefinition>()

    override fun create(id: Int, member: Boolean) = BodyDefinition().apply {
        val data = index.getFile(6)
        if (data != null) {
            readValueLoop(PacketReader(data), member)
        }
    }
}