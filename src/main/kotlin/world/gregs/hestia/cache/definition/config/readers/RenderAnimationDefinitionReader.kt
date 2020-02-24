package world.gregs.hestia.cache.definition.config.readers

import com.displee.cache.CacheLibrary
import world.gregs.hestia.cache.Configs.RENDER_ANIMATIONS
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.RenderAnimationDefinition
import java.util.concurrent.ConcurrentHashMap

class RenderAnimationDefinitionReader(cacheStore: CacheLibrary) : ConfigReader<RenderAnimationDefinition>(cacheStore) {

    override val archive: Int = RENDER_ANIMATIONS

    override val cache = ConcurrentHashMap<Int, RenderAnimationDefinition>()

    override fun create(id: Int, member: Boolean) = RenderAnimationDefinition().apply {
        readData(archive, id)
    }
}