package world.gregs.hestia.cache.definition.config.readers

import world.gregs.hestia.cache.CacheStore
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.HitSplatDefinition
import java.util.concurrent.ConcurrentHashMap

class HitSplatDefinitionReader(cacheStore: CacheStore) : ConfigReader<HitSplatDefinition>(cacheStore) {

    override val archive = 46

    override val cache = ConcurrentHashMap<Int, HitSplatDefinition>()

    override fun create(id: Int, member: Boolean) = HitSplatDefinition().apply {
        readData(archive, id)
    }
}