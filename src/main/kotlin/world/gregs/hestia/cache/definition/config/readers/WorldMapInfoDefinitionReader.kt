package world.gregs.hestia.cache.definition.config.readers

import com.displee.cache.CacheLibrary
import world.gregs.hestia.cache.Configs.WORLD_MAP_INFO
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.WorldMapInfoDefinition
import java.util.concurrent.ConcurrentHashMap

class WorldMapInfoDefinitionReader(cacheStore: CacheLibrary) : ConfigReader<WorldMapInfoDefinition>(cacheStore) {

    override val archive = WORLD_MAP_INFO

    override val cache = ConcurrentHashMap<Int, WorldMapInfoDefinition>()

    override fun create(id: Int, member: Boolean) = WorldMapInfoDefinition().apply {
        this.id = id
        readData(archive, id)
        changeValues()
    }
}