object ChatService {
    val ownerId: Int = 133769 //The ID of the user using the chat System

    val chatList = mutableMapOf<Int, Chat>() //The int is the ID of the user this user is chatting with

    fun newMessage(senderId: Int, receiverId: Int, messageText: String) {
        val userId =
            if (senderId == ownerId) receiverId else senderId //we find who of the receiver-sender pair is not the user of this system
        chatList.getOrPut(userId) { Chat(userId/*, "chat with $userId") */)}.messageList += Message(messageText, senderId)
    }

    fun getFromIndexMessages(userId: Int, fromIndex: Int, amountOfMessages: Int): List<Message> {
        val chat = chatList[userId] ?: throw ChatNotFoundException("no chat with $userId")
        return chat.messageList.subList(fromIndex, fromIndex + amountOfMessages).onEach { it.read = true }
    }

    fun getChats(): List<Chat> {
        return chatList.values.toMutableList()
    }

    fun getUnreadChatsCount(): Int {
        val c: MutableList<Chat> = chatList.values.toMutableList()
        return c.count{it.messageList.any { !it.read }}
    }

    fun deleteMessage(userId: Int, messageIndex: Int): Boolean {
        try {
            chatList[userId]?.messageList?.removeAt(messageIndex) ?: throw ChatNotFoundException("no chat with $userId")
        } catch (e: MessageNotFoundException) {
            println("no message with index $messageIndex")
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
        } catch (e: MessageNotFoundException) {
            println("no message with index $messageIndex")
        }
        return true
    }

    fun deleteChat(userId: Int): Boolean {
        chatList.remove(userId) ?: throw ChatNotFoundException("no chat with $userId")
        return true
    }

    fun clearEverything(){ //FOR TESTING PURPOSES
        chatList.clear()
    }

    class ChatNotFoundException(message: String) : RuntimeException(message)
    class MessageNotFoundException(message: String) : RuntimeException(message)
}