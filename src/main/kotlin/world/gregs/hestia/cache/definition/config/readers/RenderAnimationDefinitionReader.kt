package world.gregs.hestia.cache.definition.config.readers

import world.gregs.hestia.cache.CacheStore
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.RenderAnimationDefinition
import java.util.concurrent.ConcurrentHashMap

class RenderAnimationDefinitionReader(cacheStore: CacheStore) : ConfigReader<RenderAnimationDefinition>(cacheStore) {

    override val archive: Int = 32

    override val cache = ConcurrentHashMap<Int, RenderAnimationDefinition>()

    override fun create(id: Int, member: Boolean) = RenderAnimationDefinition().apply {
        readData(archive, id)
    }
}