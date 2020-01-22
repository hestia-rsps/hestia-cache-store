package world.gregs.hestia.cache.definition.config

import world.gregs.hestia.cache.CacheStore
import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.cache.definition.DefinitionReader

abstract class ConfigReader<T : Definition>(cacheStore: CacheStore) : DefinitionReader<T> {

    override val index = cacheStore.getIndex(2)

    override val size: Int
        get() = index.getLastFileId(archive)

    abstract val archive: Int

}