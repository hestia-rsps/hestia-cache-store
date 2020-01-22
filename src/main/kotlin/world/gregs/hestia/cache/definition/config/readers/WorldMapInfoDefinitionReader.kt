package world.gregs.hestia.cache.definition.config.readers

import world.gregs.hestia.cache.CacheStore
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.WorldMapInfoDefinition
import java.util.concurrent.ConcurrentHashMap

class WorldMapInfoDefinitionReader(cacheStore: CacheStore) : ConfigReader<WorldMapInfoDefinition>(cacheStore) {

    override val archive = 36

    override val cache = ConcurrentHashMap<Int, WorldMapInfoDefinition>()

    override fun create(id: Int, member: Boolean) = WorldMapInfoDefinition().apply {
        this.id = id
        readData(archive, id)
        changeValues()
    }
}