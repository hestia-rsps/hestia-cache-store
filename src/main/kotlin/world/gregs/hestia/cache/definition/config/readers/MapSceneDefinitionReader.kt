package world.gregs.hestia.cache.definition.config.readers

import org.displee.CacheLibrary
import world.gregs.hestia.cache.Configs.MAP_SCENES
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.MapSceneDefinition
import java.util.concurrent.ConcurrentHashMap

class MapSceneDefinitionReader(cacheStore: CacheLibrary) : ConfigReader<MapSceneDefinition>(cacheStore) {

    override val archive: Int = MAP_SCENES

    override val cache = ConcurrentHashMap<Int, MapSceneDefinition>()

    override fun create(id: Int, member: Boolean) = MapSceneDefinition().apply {
        readData(archive, id)
    }
}