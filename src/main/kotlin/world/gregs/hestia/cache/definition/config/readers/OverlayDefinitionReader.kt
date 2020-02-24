package world.gregs.hestia.cache.definition.config.readers

import com.displee.cache.CacheLibrary
import world.gregs.hestia.cache.Configs.FLOOR_OVERLAY
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.OverlayDefinition
import java.util.concurrent.ConcurrentHashMap

class OverlayDefinitionReader(cacheStore: CacheLibrary) : ConfigReader<OverlayDefinition>(cacheStore) {

    override val archive: Int = FLOOR_OVERLAY

    override val cache = ConcurrentHashMap<Int, OverlayDefinition>()

    override fun create(id: Int, member: Boolean) = OverlayDefinition().apply {
        this.id = id
        readData(archive, id)
        changeValues()
    }
}