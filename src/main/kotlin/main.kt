fun main() {
    val ourUserId = 133769
    val chatPerson1Id = 459224
    val chatPerson2Id = 992334

    ChatService.newMessage(ourUserId, chatPerson1Id,"hi, that one guy")
    ChatService.newMessage(chatPerson1Id, ourUserId,"hi, the person reading this message")

    ChatService.newMessage(ourUserId, chatPerson2Id,"this is a second chat")
    ChatService.newMessage(chatPerson2Id, ourUserId,"for me its the first")
    ChatService.newMessage(ourUserId, chatPerson2Id,"sucks to be you")

    ChatService.showAllChats()
    println( ChatService.getFromIndexMessages(459224,1,1))
    ChatService.showAllChats()
    println( ChatService.getChats())
}