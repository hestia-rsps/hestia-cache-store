package world.gregs.hestia.cache.definition.config.readers

import com.displee.cache.CacheLibrary
import world.gregs.hestia.cache.Configs.STRUTS
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.StrutDefinition
import java.util.concurrent.ConcurrentHashMap

class StrutDefinitionReader(cacheStore: CacheLibrary) : ConfigReader<StrutDefinition>(cacheStore) {

    override val archive = STRUTS

    override val cache = ConcurrentHashMap<Int, StrutDefinition>()

    override fun create(id: Int, member: Boolean) = StrutDefinition().apply {
        readData(archive, id)
    }
}