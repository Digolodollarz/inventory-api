package tech.diggle.labmanapi.notifications

import org.springframework.stereotype.Service

@Service
class NotificationServiceImpl(val notificationRepository: NotificationRepository) : NotificationService {
    override fun findAllByUserId(studentId: Long): List<Notification>
            = this.notificationRepository.findAllByUserId(studentId)

    override fun findAllBySenderId(senderId: Long): List<Notification>
            = this.findAllBySenderId(senderId)

    override fun get(id: Long): Notification
            = this.notificationRepository.findOne(id)

    override fun add(notification: Notification): Notification
            = this.notificationRepository.save(notification)

}