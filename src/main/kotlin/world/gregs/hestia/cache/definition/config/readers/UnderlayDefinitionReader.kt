package world.gregs.hestia.cache.definition.config.readers

import org.displee.CacheLibrary
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.UnderlayDefinition
import java.util.concurrent.ConcurrentHashMap

class UnderlayDefinitionReader(cacheStore: CacheLibrary) : ConfigReader<UnderlayDefinition>(cacheStore) {

    override val archive: Int = 1

    override val cache = ConcurrentHashMap<Int, UnderlayDefinition>()

    override fun create(id: Int, member: Boolean) = UnderlayDefinition().apply {
        readData(archive, id)
    }
}