package world.gregs.hestia.cache.definition.config.readers

import world.gregs.hestia.cache.CacheStore
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.StrutDefinition
import java.util.concurrent.ConcurrentHashMap

class StrutDefinitionReader(cacheStore: CacheStore) : ConfigReader<StrutDefinition>(cacheStore) {

    override val archive = 26

    override val cache = ConcurrentHashMap<Int, StrutDefinition>()

    override fun create(id: Int, member: Boolean) = StrutDefinition().apply {
        readData(archive, id)
    }
}