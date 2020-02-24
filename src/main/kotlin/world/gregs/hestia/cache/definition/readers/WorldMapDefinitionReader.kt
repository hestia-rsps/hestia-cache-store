package world.gregs.hestia.cache.definition.readers

import org.displee.CacheLibrary
import world.gregs.hestia.cache.Indices.WORLD_MAP
import world.gregs.hestia.cache.definition.DefinitionReader
import world.gregs.hestia.cache.definition.definitions.WorldMapDefinition
import java.util.concurrent.ConcurrentHashMap

class WorldMapDefinitionReader(cacheStore: CacheLibrary) : DefinitionReader<WorldMapDefinition> {

    override val index = cacheStore.getIndex(WORLD_MAP)

    val archiveId = index.getArchiveId("details")

    override val cache = ConcurrentHashMap<Int, WorldMapDefinition>()

    override val size: Int
        get() = index.getArchive(archiveId).lastFile.id

    override fun create(id: Int, member: Boolean) = WorldMapDefinition().apply {
        this.id = id
        readData(archiveId, id)
        changeValues()
    }
}