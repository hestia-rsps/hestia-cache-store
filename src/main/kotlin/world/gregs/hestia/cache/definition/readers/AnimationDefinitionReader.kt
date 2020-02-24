package world.gregs.hestia.cache.definition.readers

import org.displee.CacheLibrary
import world.gregs.hestia.cache.Indices.ANIMATIONS
import world.gregs.hestia.cache.definition.DefinitionReader
import world.gregs.hestia.cache.definition.definitions.AnimationDefinition
import java.util.concurrent.ConcurrentHashMap

class AnimationDefinitionReader(cacheStore: CacheLibrary) : DefinitionReader<AnimationDefinition> {

    override val index = cacheStore.getIndex(ANIMATIONS)

    override val cache = ConcurrentHashMap<Int, AnimationDefinition>()

    override fun create(id: Int, member: Boolean) = AnimationDefinition().apply {
        this.id = id
        readData(id ushr 7, 0x7f and id)
        changeValues()
    }
}