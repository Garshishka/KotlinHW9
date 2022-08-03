fun main() {
    val ourUserId = 133769
    val chatPerson1Id = 459224
    val chatPerson2Id = 992334

    ChatService.newMessage(ourUserId, chatPerson1Id,"hi, that one guy")
    ChatService.newMessage(chatPerson1Id, ourUserId,"hi, the person reading this message")

    ChatService.newMessage(ourUserId, chatPerson2Id,"this is a second chat")
    ChatService.newMessage(chatPerson2Id, ourUserId,"for me its the first")
    ChatService.newMessage(ourUserId, chatPerson2Id,"sucks to be you")

    println( ChatService.getChats())

    println("Showing 2 messages from first chat")
    println( ChatService.getFromIndexMessages(chatPerson1Id,0,2))

    println("unread chats: "+ChatService.getUnreadChatsCount())

    println("Showing 2 messages from second chat")
    println( ChatService.getFromIndexMessages(chatPerson2Id,0,2))
    println("unread chats: "+ChatService.getUnreadChatsCount())
    println("Showing all messages from first chat")
    println( ChatService.getFromIndexMessages(chatPerson2Id,0,3))
    println("unread chats: "+ChatService.getUnreadChatsCount())
}