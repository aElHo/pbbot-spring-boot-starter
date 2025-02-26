package net.lz1998.pbbot.utils

import net.lz1998.pbbot.alias.MessageChain
import net.lz1998.pbbot.bot.Bot
import onebot.OnebotBase

open class Msg {
    open var messageChain: MutableList<OnebotBase.Message> = mutableListOf()

    companion object {
        @JvmStatic
        fun builder(): Msg {
            return Msg()
        }
    }

    fun text(text: String): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("text").putAllData(hashMapOf("text" to text)).build()
        )
        return this
    }

    fun image(url: String): Msg {
        messageChain.add(OnebotBase.Message.newBuilder().setType("image").putAllData(mapOf("url" to url)).build())
        return this
    }

    fun record(url: String): Msg {
        messageChain.add(OnebotBase.Message.newBuilder().setType("record").putAllData(mapOf("url" to url)).build())
        return this
    }

    fun flash(url: String): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("image").putAllData(mapOf("url" to url, "type" to "flash")).build()
        )
        return this
    }

    fun show(url: String, effectId: Int = 40000): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("image")
                .putAllData(mapOf("url" to url, "type" to "show", "effect_id" to effectId.toString())).build()
        )
        return this
    }

    fun face(id: Int): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("face").putAllData(mapOf("id" to id.toString())).build()
        )
        return this
    }

    fun at(qq: Long): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("at").putAllData(mapOf("qq" to qq.toString())).build()
        )
        return this
    }

    fun dice(value: Int): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("dice").putAllData(mapOf("value" to value.toString())).build()
        )
        return this
    }

    fun atAll(): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("at").putAllData(mapOf("qq" to "all")).build()
        )
        return this
    }

    fun poke(qq: Long): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("poke").putAllData(mapOf("qq" to qq.toString())).build()
        )
        return this
    }

    fun qqMusic(id: Int): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("music").putAllData(mapOf("type" to "qq", "id" to id.toString()))
                .build()
        )
        return this
    }

    fun neteaseMusic(id: Int): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("music").putAllData(mapOf("type" to "163", "id" to id.toString()))
                .build()
        )
        return this
    }

    fun music(title: String, content: String, url: String, image: String, audio: String): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("music").putAllData(
                mapOf(
                    "type" to "custom",
                    "title" to title,
                    "content" to content,
                    "url" to url,
                    "image" to image,
                    "audio" to audio,
                )
            )
                .build()
        )
        return this
    }

    fun share(url: String, title: String, content: String, image: String): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("share")
                .putAllData(mapOf("url" to url, "title" to title, "content" to content, "image" to image)).build()
        )
        return this
    }

    fun lightApp(content: String): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("light_app")
                .putAllData(mapOf("content" to content)).build()
        )
        return this
    }

    fun xml(id: Int, content: String): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("service")
                .putAllData(mapOf("sub_type" to "xml", "id" to id.toString(), "content" to content)).build()
        )
        return this
    }

    fun json(id: Int, content: String): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("service")
                .putAllData(mapOf("sub_type" to "json", "id" to id.toString(), "content" to content)).build()
        )
        return this
    }

    fun reply(messageId: Int): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("reply")
                .putAllData(mapOf("message_id" to messageId.toString())).build()
        )
        return this
    }

    fun sleep(time: Long): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("sleep")
                .putAllData(mapOf("time" to time.toString())).build()
        )
        return this
    }

    fun tts(text: String): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("tts")
                .putAllData(mapOf("text" to text)).build()
        )
        return this
    }

    fun video(url: String, cover: String, cache: Boolean): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("video")
                .putAllData(mapOf("url" to url, "cover" to cover, "cache" to (if (cache) "1" else "0"))).build()
        )
        return this
    }

    fun gift(qq: Long, id: Int): Msg {
        messageChain.add(
            OnebotBase.Message.newBuilder().setType("gift")
                .putAllData(mapOf("qq" to qq.toString(), "id" to id.toString())).build()
        )
        return this
    }

    fun build(): MessageChain {
        return messageChain
    }

    fun sendToGroup(bot: Bot, groupId: Long): Msg {
        bot.sendGroupMsg(groupId, this)
        return this
    }

    fun sendToFriend(bot: Bot, userId: Long): Msg {
        bot.sendPrivateMsg(userId, this)
        return this
    }
}