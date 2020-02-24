package world.gregs.hestia.cache.definition.readers

import org.displee.CacheLibrary
import world.gregs.hestia.cache.Indices.DEFAULTS
import world.gregs.hestia.cache.definition.DefinitionReader
import world.gregs.hestia.cache.definition.definitions.BodyDefinition
import world.gregs.hestia.io.BufferReader
import java.util.concurrent.ConcurrentHashMap

class BodyDefinitionReader(cacheStore: CacheLibrary) : DefinitionReader<BodyDefinition> {

    override val index = cacheStore.getIndex(DEFAULTS)

    override val cache = ConcurrentHashMap<Int, BodyDefinition>()

    override fun create(id: Int, member: Boolean) = BodyDefinition().apply {
        val data = index.getArchive(6).getFile(0).data
        if (data != null) {
            readValueLoop(BufferReader(data), member)
        }
    }
}