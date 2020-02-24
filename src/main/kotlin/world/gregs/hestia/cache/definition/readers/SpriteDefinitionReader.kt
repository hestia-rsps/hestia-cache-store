package world.gregs.hestia.cache.definition.readers

import org.displee.CacheLibrary
import world.gregs.hestia.cache.Indices.SPRITES
import world.gregs.hestia.cache.definition.DefinitionReader
import world.gregs.hestia.cache.definition.definitions.SpriteDefinition
import java.util.concurrent.ConcurrentHashMap

class SpriteDefinitionReader(cacheStore: CacheLibrary) : DefinitionReader<SpriteDefinition> {

    override val index = cacheStore.getIndex(SPRITES)

    override val cache = ConcurrentHashMap<Int, SpriteDefinition>()

    override fun create(id: Int, member: Boolean) = SpriteDefinition().apply {
        val data = index.getArchive(id).getFile(0).data
        if(data != null) {
            decode(data)
        }
    }
}