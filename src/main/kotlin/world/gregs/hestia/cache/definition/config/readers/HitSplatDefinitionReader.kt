package world.gregs.hestia.cache.definition.config.readers

import org.displee.CacheLibrary
import world.gregs.hestia.cache.Configs.HIT_SPLATS
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.HitSplatDefinition
import java.util.concurrent.ConcurrentHashMap

class HitSplatDefinitionReader(cacheStore: CacheLibrary) : ConfigReader<HitSplatDefinition>(cacheStore) {

    override val archive = HIT_SPLATS

    override val cache = ConcurrentHashMap<Int, HitSplatDefinition>()

    override fun create(id: Int, member: Boolean) = HitSplatDefinition().apply {
        readData(archive, id)
    }
}