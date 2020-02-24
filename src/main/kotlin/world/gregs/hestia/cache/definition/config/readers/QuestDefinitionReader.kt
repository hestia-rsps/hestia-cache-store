package world.gregs.hestia.cache.definition.config.readers

import com.displee.cache.CacheLibrary
import world.gregs.hestia.cache.Configs.QUESTS
import world.gregs.hestia.cache.definition.config.ConfigReader
import world.gregs.hestia.cache.definition.config.definitions.QuestDefinition
import java.util.concurrent.ConcurrentHashMap

class QuestDefinitionReader(cacheStore: CacheLibrary) : ConfigReader<QuestDefinition>(cacheStore) {

    override val archive = QUESTS

    override val cache = ConcurrentHashMap<Int, QuestDefinition>()

    override fun create(id: Int, member: Boolean) = QuestDefinition().apply {
        readData(archive, id)
        changeValues()
    }
}