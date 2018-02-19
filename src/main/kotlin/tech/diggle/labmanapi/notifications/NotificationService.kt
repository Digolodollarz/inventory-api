package tech.diggle.labmanapi.notifications

interface NotificationService{
    fun findAllByUserId(studentId: Long): List<Notification>
    fun findAllBySenderId(senderId: Long): List<Notification>
    fun get(id: Long): Notification
    fun add(notification: Notification): Notification
}