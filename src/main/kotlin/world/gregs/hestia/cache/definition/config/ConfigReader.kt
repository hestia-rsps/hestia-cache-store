package world.gregs.hestia.cache.definition.config

import org.displee.CacheLibrary
import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.cache.definition.DefinitionReader

abstract class ConfigReader<T : Definition>(cacheStore: CacheLibrary) : DefinitionReader<T> {

    override val index = cacheStore.getIndex(2)

    override val size: Int
        get() = index.getArchive(archive).lastFile.id

    abstract val archive: Int

}