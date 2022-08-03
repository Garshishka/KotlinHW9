import kotlin.random.Random

data class Chat(
    val userId: Int, //ID for person with whom the user chats
 //   val chatName: String,

    val date: Int = Random.nextInt(1000,9999), //just for the purposes of this homework
    val messageList: MutableList<Message> = mutableListOf() //Storing the IDs of messages in their Message Service
){
    //Showing the the last message from chat
    override fun toString(): String {
        return "(chat with |$userId|: ${messageList.last()})"
    }
}

data class Message(
    val messageText: String,
    val senderId: Int,

    val date: Int = Random.nextInt(1000,9999), //just for the purposes of this homework
    var read: Boolean = false
){
    override fun toString(): String {
        return "|$senderId| at $date: '$messageText' "+if(!read)" - unread" else ""
    }
}