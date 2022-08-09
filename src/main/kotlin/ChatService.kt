object ChatService {
    val ownerId: Int = 133769 //The ID of the user using the chat System

    val chatList = mutableMapOf<Int, Chat>() //The int is the ID of the user this user is chatting with

    fun newMessage(senderId: Int, receiverId: Int, messageText: String) {
        val userId =
            if (senderId == ownerId) receiverId else senderId //we find who of the receiver-sender pair is not the user of this system
        chatList.getOrPut(userId) { Chat(userId) }.messageList += Message(messageText, senderId)
    }

    fun getFromIndexMessages(userId: Int, fromIndex: Int, amountOfMessages: Int): List<Message> {
        val chat = chatList[userId] ?: throw ChatNotFoundException("no chat with $userId")
        return chat.messageList.asSequence()
            .filterIndexed { index, it -> index >= fromIndex }
            .take(amountOfMessages)
            .onEach { it.read = true }
            .toList()
            .ifEmpty { listOf(Message("No messages in this chat", -1, read = true, date = 0)) }
    }

    fun getChats(): List<Chat> {
        return chatList.values.toMutableList()
    }

    fun getUnreadChatsCount(): Int {
        return  chatList.values.asSequence()
            .filter{ it.messageList.any{ !it.read} }
            .count()
    }

    fun deleteMessage(userId: Int, messageIndex: Int): Boolean {
        try {
            chatList[userId]?.messageList?.removeAt(messageIndex) ?: throw ChatNotFoundException("no chat with $userId")
        } catch (e: IndexOutOfBoundsException) {
            println("no message with index $messageIndex")
            return false
        }
        //if this was the last message in chat the chat gets deleted
        if (chatList[userId]?.messageList?.isEmpty() == true) {
            deleteChat(userId)
        }
        return true
    }

    fun editMessage(userId: Int, messageIndex: Int, newMesage: String): Boolean {
        try {
            chatList[userId]?.messageList?.get(messageIndex)?.messageText = newMesage
        } catch (e: IndexOutOfBoundsException) {
            println("no message with index $messageIndex")
            return false
        }
        return true
    }

    fun deleteChat(userId: Int): Boolean {
        chatList.remove(userId) ?: throw ChatNotFoundException("no chat with $userId")
        return true
    }

    fun clearEverything() { //FOR TESTING PURPOSES
        chatList.clear()
    }

    class ChatNotFoundException(message: String) : RuntimeException(message)
}