import org.junit.Assert.*
import org.junit.Test

class ChatServiceTest {

    @Test
    fun getFromIndexMessages_getMessages() {
        ChatService.clearEverything()
        ChatService.newMessage(133769, 100,"test")
        val result = ChatService.getFromIndexMessages(100,0,1)
        //because date is random we will bend around it
        assertEquals(result[0].messageText,"test")
        assertEquals(result[0].senderId,133769)
        assertEquals(result[0].read,true)
    }

    @Test (expected = ChatService.ChatNotFoundException::class)
    fun getFromIndexMessages_noChatFound() {
        ChatService.clearEverything()
        ChatService.newMessage(133769, 100,"test")
        ChatService.newMessage(133769, 100,"test2")
        ChatService.getFromIndexMessages(200,0,2)
    }

    @Test
    fun getUnreadChatsCount() {
        ChatService.clearEverything()
        ChatService.newMessage(133769, 100,"test")
        ChatService.newMessage(133769, 200,"test2")
        val result = ChatService.getUnreadChatsCount()
        assertEquals(result,2)
    }

    @Test
    fun editMessage_messageFound(){
        ChatService.clearEverything()
        ChatService.newMessage(133769, 100,"test")
        val result = ChatService.editMessage(100,0,"test2")
        assertTrue(result)
    }

    @Test
    fun editMessage_messageNotFound(){
        ChatService.clearEverything()
        ChatService.newMessage(133769, 100,"test")
        val result = ChatService.editMessage(100,2,"test2")
        assertFalse(result)
    }

    @Test
    fun deleteMessage_justMessage() {
        ChatService.clearEverything()
        ChatService.newMessage(133769, 100,"test")
        ChatService.newMessage(133769, 100,"test2")
        val result = ChatService.deleteMessage(100,1)
        assertTrue(result)
    }

    @Test (expected = ChatService.ChatNotFoundException::class)
    fun deleteMessage_deleteChat() {
        ChatService.clearEverything()
        ChatService.newMessage(133769, 100,"test")
        ChatService.deleteMessage(100,0)
        ChatService.deleteChat(100)
    }

    @Test
    fun deleteMessage_noMessage() {
        ChatService.clearEverything()
        ChatService.newMessage(133769, 100,"test")
        val result = ChatService.deleteMessage(100,2)
        assertFalse(result)
    }

    @Test (expected = ChatService.ChatNotFoundException::class)
    fun deleteMessage_noChat() {
        ChatService.clearEverything()
        ChatService.deleteMessage(1,1)
    }

    @Test
    fun deleteChat_foundChat() {
        ChatService.clearEverything()
        ChatService.newMessage(133769, 100,"test")
        val result = ChatService.deleteChat(100)
        assertTrue(result)
    }

    @Test(expected = ChatService.ChatNotFoundException::class)
    fun deleteChat_noChat() {
        ChatService.clearEverything()
        ChatService.deleteChat(1)
    }
}