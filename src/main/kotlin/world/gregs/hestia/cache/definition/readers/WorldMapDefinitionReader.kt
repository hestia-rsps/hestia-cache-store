package world.gregs.hestia.cache.definition.readers

import world.gregs.hestia.cache.CacheStore
import world.gregs.hestia.cache.definition.DefinitionReader
import world.gregs.hestia.cache.definition.definitions.WorldMapDefinition
import java.util.concurrent.ConcurrentHashMap

class WorldMapDefinitionReader(cacheStore: CacheStore) : DefinitionReader<WorldMapDefinition> {

    override val index = cacheStore.getIndex(23)

    val archiveId = index.getArchiveId("details")

    override val cache = ConcurrentHashMap<Int, WorldMapDefinition>()

    override val size: Int
        get() = index.getLastFileId(archiveId)

    override fun create(id: Int, member: Boolean) = WorldMapDefinition().apply {
        this.id = id
        readData(archiveId, id)
        changeValues()
    }
}