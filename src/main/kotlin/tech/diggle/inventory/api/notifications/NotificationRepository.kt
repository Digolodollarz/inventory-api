package tech.diggle.inventory.api.notifications

import org.springframework.data.repository.CrudRepository

interface NotificationRepository : CrudRepository<Notification, Long> {
    fun findAllByUserId(studentId: Long): List<Notification>
    fun findAllBySenderId(senderId: Long): List<Notification>
}
