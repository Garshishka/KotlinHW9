# Домашнее задание к занятию «3.3. Лямбды, extension-функции, операторы»

В выполнении работы подразумевалось что это чат принадлежащий одному пользователю, который общается с другими пользователями, поэтому ID этого юзера было заранее указано в системе-сервисе чатов

Файл [Chat.kt](/src/main/kotlin/Chat.kt) содержит два класса:
1. `Chat` содержит Id юзера, с которым общается наш пользователь и список сообщений класса `Message`
2. `Message` содержит текст сообщения, ID того кто его отправил и флаг прочитано оно или нет

Сервис [ChatService.kt](/src/main/kotlin/ChatService.kt) отвечает за работу с нашей системой классов. В нем имеется мапа `chatList` которая содержит чаты класса `Chat` и ID собеседника в качестве ключа. Кроме того тут заранее задано `ownerId` - ID пользователя нашей системы чатов.

В данном сервисе имеются следующие методы:
1. `newMessage` создает новое сообщение класса `message` и либо помещает в список сообщений уже существующего чата, либо создает новый и добавляет его в `chatList`
2. `getFromIndexMessages` выдает список сообщений из чата с ID определенного пользователя с заданного сообщения (каждое выданное сообщения считается прочитанным)
3. `getChats` выдает список чатов с последним сообщением в них (это не считается как чтение сообщения)
4. `getUnreadChatsCount` выдает количество чатов, в которых есть хоть одно непрочитанное сообщение
5. `deleteMessage` - удаляет указанное по номеру сообщение в чате с указанным ID. Если это было последнее сообщение - удаляет весь чат
6. `editMessage` - заменяет текст указанного собщения в чате с указанным ID
7. `deleteChat` - удаляет чат и, соответственно, все сообщения в нем
