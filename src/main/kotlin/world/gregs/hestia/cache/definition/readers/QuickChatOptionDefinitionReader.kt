package world.gregs.hestia.cache.definition.readers

import org.displee.CacheLibrary
import world.gregs.hestia.cache.Indices.QUICK_CHAT_MENUS
import world.gregs.hestia.cache.Indices.QUICK_CHAT_MESSAGES
import world.gregs.hestia.cache.definition.DefinitionReader
import world.gregs.hestia.cache.definition.definitions.QuickChatOptionDefinition
import world.gregs.hestia.io.BufferReader
import java.util.concurrent.ConcurrentHashMap

class QuickChatOptionDefinitionReader(cacheStore: CacheLibrary) : DefinitionReader<QuickChatOptionDefinition> {

    override val index = cacheStore.getIndex(QUICK_CHAT_MESSAGES)

    val secondIndex = cacheStore.getIndex(QUICK_CHAT_MENUS)

    override val size: Int
        get() = (index.lastArchive.id * 256 + (index.lastArchive.fileIds.size)) + (secondIndex.lastArchive.id * 256 + (secondIndex.lastArchive.fileIds.size))

    override val cache = ConcurrentHashMap<Int, QuickChatOptionDefinition>()

    override fun create(id: Int, member: Boolean) = QuickChatOptionDefinition().apply {
        val data = if(id < 32768) {
            index.getArchive(0).getFile(id).data
        } else {
            secondIndex.getArchive(0).getFile(id and 0x7fff).data
        }

        if(data != null) {
            readValueLoop(BufferReader(data), member)
        }

        if(id >= 32768) {
            changeValues()
        }
    }
}