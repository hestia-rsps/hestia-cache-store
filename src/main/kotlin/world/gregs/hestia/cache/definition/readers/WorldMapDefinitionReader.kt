package world.gregs.hestia.cache.definition.readers

import com.displee.cache.CacheLibrary
import world.gregs.hestia.cache.Indices.WORLD_MAP
import world.gregs.hestia.cache.definition.DefinitionReader
import world.gregs.hestia.cache.definition.definitions.WorldMapDefinition
import java.util.concurrent.ConcurrentHashMap

class WorldMapDefinitionReader(cacheStore: CacheLibrary) : DefinitionReader<WorldMapDefinition> {

    override val index = cacheStore.index(WORLD_MAP)

    val archiveId = index.archiveId("details")

    override val cache = ConcurrentHashMap<Int, WorldMapDefinition>()

    override val size: Int
        get() = index.archive(archiveId)?.last()?.id ?: 0

    override fun create(id: Int, member: Boolean) = WorldMapDefinition().apply {
        this.id = id
        readData(archiveId, id)
        changeValues()
    }
}