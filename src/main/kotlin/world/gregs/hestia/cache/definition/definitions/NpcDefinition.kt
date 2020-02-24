package world.gregs.hestia.cache.definition.definitions

import world.gregs.hestia.cache.definition.Definition
import world.gregs.hestia.io.Reader
import java.util.*

class NpcDefinition : Definition {
    var anInt2803 = -1
    var height = -1
    var headIcon = -1
    var anInt2809 = -1
    var anInt2810 = -1
    var size = 1
    var anInt2812 = -1
    var params: HashMap<Long?, Any?>? = null
    var armyIcon = -1
    var anInt2815 = -1
    var walkMask = 0.toByte()
    var slowWalk = true
    var recolourPalette: ByteArray? = null
    var name = "null"
    var originalColours: ShortArray? = null
    var priorityRender = false
    var aBoolean2825 = false
    var spriteId = -1
    var morphs: IntArray? = null
    var anInt2828 = 255
    var modifiedColours: ShortArray? = null
    var scaleZ = 128
    var anInt2831 = 0
    var campaigns: IntArray? = null
    var anInt2833 = -1
    lateinit var options: Array<String?>
    var aByte2836: Byte = 0
    var renderEmote = -1
    var combat = -1
    var aByte2839 = 0.toByte()
    var originalTextureColours: ShortArray? = null
    var translations: Array<IntArray?>? = null
    var aBoolean2843 = false
    var anInt2844 = 256
    var dialogueModels: IntArray? = null
    var lightModifier = 0
    var mapFunction = -1
    var anInt2852 = 256
    var aByte2853: Byte = 0
    var clickable = true
    var mainOptionIndex: Byte = -1
    var anInt2856 = -1
    var aByte2857: Byte = 0
    var scaleXY = 128
    var anInt2859 = -1
    var attackCursor = -1
    var anInt2862 = 0
    var aShort2863: Short = 0
    var anInt2864 = 0
    var modelIds: IntArray? = null
    var aByte2868: Byte = -16
    var aShort2871: Short = 0
    var shadowModifier = 0
    var respawnDirection: Byte = 4
    var modifiedTextureColours: ShortArray? = null
    var animateIdle = true
    var rotation = 32
    var aByte2877: Byte = -96
    var anInt2878 = -1
    var drawMinimapDot = true
    var varbit = -1
    var varp = -1
    var aBoolean2883 = false
    var anInt2886 = -1
    var id = 0

    override fun readValues(opcode: Int, buffer: Reader, member: Boolean) {
        when (opcode) {
            1 -> {
                val length = buffer.readUnsignedByte()
                modelIds = IntArray(length)
                repeat(length) { count ->
                    modelIds!![count] = buffer.readShort()
                    if (modelIds!![count] == 65535) {
                        modelIds!![count] = -1
                    }
                }
            }
            2 -> name = buffer.readString()
            12 -> size = buffer.readUnsignedByte()
            in 30..34 -> options[-30 + opcode] = buffer.readString()
            40 -> {
                val length = buffer.readUnsignedByte()
                modifiedColours = ShortArray(length)
                originalColours = ShortArray(length)
                repeat(length) { count ->
                    originalColours!![count] = buffer.readShort().toShort()
                    modifiedColours!![count] = buffer.readShort().toShort()
                }
            }
            41 -> {
                val length = buffer.readUnsignedByte()
                modifiedTextureColours = ShortArray(length)
                originalTextureColours = ShortArray(length)
                repeat(length) { count ->
                    originalTextureColours!![count] = buffer.readShort().toShort()
                    modifiedTextureColours!![count] = buffer.readShort().toShort()
                }
            }
            42 -> {
                val length = buffer.readUnsignedByte()
                recolourPalette = ByteArray(length)
                repeat(length) { count ->
                    recolourPalette!![count] = buffer.readByte().toByte()
                }
            }
            60 -> {
                val length = buffer.readUnsignedByte()
                dialogueModels = IntArray(length)
                repeat(length) { count ->
                    dialogueModels!![count] = buffer.readShort()
                }
            }
            93 -> drawMinimapDot = false
            95 -> combat = buffer.readShort()
            97 -> scaleXY = buffer.readShort()
            98 -> scaleZ = buffer.readShort()
            99 -> priorityRender = true
            100 -> lightModifier = buffer.readByte()
            101 -> shadowModifier = 5 * buffer.readByte()
            102 -> headIcon = buffer.readShort()
            103 -> rotation = buffer.readShort()
            106, 118 -> {
                varbit = buffer.readShort()
                if (varbit == 65535) {
                    varbit = -1
                }
                varp = buffer.readShort()
                if (varp == 65535) {
                    varp = -1
                }
                var last = -1
                if (opcode == 118) {
                    last = buffer.readShort()
                    if (last == 65535) {
                        last = -1
                    }
                }
                val count = buffer.readUnsignedByte()
                morphs = IntArray(count + 2)
                for(index in 0..count) {
                    morphs!![index] = buffer.readShort()
                    if (morphs!![index] == 65535) {
                        morphs!![index] = -1
                    }
                }
                morphs!![count + 1] = last
            }
            107 -> clickable = false
            109 -> slowWalk = false
            111 -> animateIdle = false
            113 -> {
                aShort2863 = buffer.readShort().toShort()
                aShort2871 = buffer.readShort().toShort()
            }
            114 -> {
                aByte2877 = buffer.readByte().toByte()
                aByte2868 = buffer.readByte().toByte()
            }
            119 -> walkMask = buffer.readByte().toByte()
            121 -> {
                translations = arrayOfNulls(modelIds!!.size)
                val length = buffer.readUnsignedByte()
                repeat(length) {
                    val index = buffer.readUnsignedByte()
                    translations!![index] = IntArray(3)
                    val `is` = translations!![index]
                    `is`!![0] = buffer.readByte()
                    `is`[1] = buffer.readByte()
                    `is`[2] = buffer.readByte()
                }
            }
            122 -> anInt2878 = buffer.readShort()
            123 -> height = buffer.readShort()
            125 -> respawnDirection = buffer.readByte().toByte()
            127 -> renderEmote = buffer.readShort()
            128 -> buffer.readUnsignedByte()
            134 -> {
                anInt2812 = buffer.readShort()
                if (anInt2812 == 65535) {
                    anInt2812 = -1
                }
                anInt2833 = buffer.readShort()
                if (anInt2833 == 65535) {
                    anInt2833 = -1
                }
                anInt2809 = buffer.readShort()
                if (anInt2809 == 65535) {
                    anInt2809 = -1
                }
                anInt2810 = buffer.readShort()
                if (anInt2810 == 65535) {
                    anInt2810 = -1
                }
                anInt2864 = buffer.readUnsignedByte()
            }
            135 -> {
                anInt2815 = buffer.readUnsignedByte()
                anInt2859 = buffer.readShort()
            }
            136 -> {
                anInt2856 = buffer.readUnsignedByte()
                anInt2886 = buffer.readShort()
            }
            137 -> attackCursor = buffer.readShort()
            138 -> armyIcon = buffer.readShort()
            139 -> spriteId = buffer.readShort()
            140 -> anInt2828 = buffer.readUnsignedByte()
            141 -> aBoolean2843 = true
            142 -> mapFunction = buffer.readShort()
            143 -> aBoolean2825 = true
            in 150..154 -> {
                options[opcode - 150] = buffer.readString()
                if (!member) {
                    options[opcode - 150] = null
                }
            }
            155 -> {
                aByte2836 = buffer.readByte().toByte()
                aByte2853 = buffer.readByte().toByte()
                aByte2857 = buffer.readByte().toByte()
                aByte2839 = buffer.readByte().toByte()
            }
            158 -> mainOptionIndex = 1.toByte()
            159 -> mainOptionIndex = 0.toByte()
            160 -> {
                val length = buffer.readUnsignedByte()
                campaigns = IntArray(length)
                repeat(length) { count ->
                    campaigns!![count] = buffer.readShort()
                }
            }
            162 -> aBoolean2883 = true
            163 -> anInt2803 = buffer.readUnsignedByte()
            164 -> {
                anInt2844 = buffer.readShort()
                anInt2852 = buffer.readShort()
            }
            165 -> anInt2831 = buffer.readUnsignedByte()
            168 -> anInt2862 = buffer.readUnsignedByte()
            249 -> {
                val length = buffer.readUnsignedByte()
                if (params == null) {
                    val initialCapacity = calculateCapacity(length)
                    params = HashMap(initialCapacity)
                }
                repeat(length) {
                    val isString = buffer.readUnsignedByte() == 1
                    val id = buffer.readMedium()
                    params!![id.toLong()] = if (isString) {
                        buffer.readString()
                    } else {
                        buffer.readInt()
                    }
                }
            }
        }
    }

    fun changeValues() {
        if (modelIds == null) {
            modelIds = IntArray(0)
        }
        if (mainOptionIndex.toInt() == -1) {
            mainOptionIndex = 1.toByte()
        }
    }
}