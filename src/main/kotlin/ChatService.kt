object ChatService {
    val ownerId: Int = 133769 //The ID of the user using the chat System

    val chatList = mutableMapOf<Int, Chat>() //The int is the ID of the user this user is chatting with

    fun newMessage(senderId: Int, receiverId: Int, messageText: String) {
        val userId =
            if (senderId == ownerId) receiverId else senderId //we find who of the receiver-sender pair is not the user of this system
        chatList.getOrPut(userId) { Chat(userId, "chat with $userId") }.messageList += Message(messageText, senderId)
    }

    fun getFromIndexMessages(userId: Int, fromIndex: Int, amountOfMessages: Int): List<Message> {
        val chat = chatList[userId] ?: throw ChatNotFoundException("no chat with $userId")
        return chat.messageList.subList(fromIndex, fromIndex + amountOfMessages).onEach { it.read = true }
    }


    fun getChats(): List<Chat> {
        return chatList.values.toList().onEach {
            val lastMessage = it.messageList.last()
            it.messageList.clear()
            it.messageList += lastMessage
        }
    }

    fun showAllChats() { //FOR TESTS
        for (chat in chatList)
            println(chat)
    }

    class ChatNotFoundException(message: String) : RuntimeException(message)
}