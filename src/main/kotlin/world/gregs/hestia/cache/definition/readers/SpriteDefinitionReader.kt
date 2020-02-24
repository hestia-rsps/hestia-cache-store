package world.gregs.hestia.cache.definition.readers

import com.displee.cache.CacheLibrary
import world.gregs.hestia.cache.Indices.SPRITES
import world.gregs.hestia.cache.definition.DefinitionReader
import world.gregs.hestia.cache.definition.definitions.SpriteDefinition
import java.util.concurrent.ConcurrentHashMap

class SpriteDefinitionReader(cacheStore: CacheLibrary) : DefinitionReader<SpriteDefinition> {

    override val index = cacheStore.index(SPRITES)

    override val cache = ConcurrentHashMap<Int, SpriteDefinition>()

    override fun create(id: Int, member: Boolean) = SpriteDefinition().apply {
        val data = index.archive(id)?.file(0)?.data
        if(data != null) {
            decode(data)
        }
    }
}