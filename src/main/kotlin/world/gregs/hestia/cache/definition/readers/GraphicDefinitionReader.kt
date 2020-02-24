package world.gregs.hestia.cache.definition.readers

import com.displee.cache.CacheLibrary
import world.gregs.hestia.cache.Indices.GRAPHICS
import world.gregs.hestia.cache.definition.DefinitionReader
import world.gregs.hestia.cache.definition.definitions.GraphicDefinition
import java.util.concurrent.ConcurrentHashMap

class GraphicDefinitionReader(cacheStore: CacheLibrary) : DefinitionReader<GraphicDefinition> {

    override val index = cacheStore.index(GRAPHICS)

    override val cache = ConcurrentHashMap<Int, GraphicDefinition>()

    override fun create(id: Int, member: Boolean) = GraphicDefinition().apply {
        this.id = id
        readData(id ushr 8, id and 0xff)
    }
}