package world.gregs.hestia.cache.definition.config

import com.displee.cache.CacheLibrary
import world.gregs.hestia.cache.Configs
import world.gregs.hestia.cache.Indices.CONFIGS
import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.cache.definition.DefinitionReader

abstract class ConfigReader<T : Definition>(cacheStore: CacheLibrary) : DefinitionReader<T> {

    override val index = cacheStore.index(CONFIGS)

    override val size: Int
        get() = index.archive(archive)?.last()?.id ?: 0

    abstract val archive: Int

}