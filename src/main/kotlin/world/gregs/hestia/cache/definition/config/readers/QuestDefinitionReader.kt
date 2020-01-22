package world.gregs.hestia.cache.definition.config.readers

import world.gregs.hestia.cache.CacheStore
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.QuestDefinition
import java.util.concurrent.ConcurrentHashMap

class QuestDefinitionReader(cacheStore: CacheStore) : ConfigReader<QuestDefinition>(cacheStore) {

    override val archive = 35

    override val cache = ConcurrentHashMap<Int, QuestDefinition>()

    override fun create(id: Int, member: Boolean) = QuestDefinition().apply {
        readData(archive, id)
        changeValues()
    }
}