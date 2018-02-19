package tech.diggle.labmanapi.requests

import org.springframework.stereotype.Service
import tech.diggle.labmanapi.component.ComponentRepository
import tech.diggle.labmanapi.notifications.Notification
import tech.diggle.labmanapi.notifications.NotificationService
import tech.diggle.labmanapi.stock.ItemRepository
import tech.diggle.labmanapi.student.StudentRepository
import tech.diggle.labmanapi.studentcomponents.StudentComponent
import tech.diggle.labmanapi.studentcomponents.StudentComponentRepository
import java.util.*

@Service
class ComponentRequestServiceImpl(val componentRequests: ComponentRequestRepository,
                                  val components: ComponentRepository,
                                  val studentComponents: StudentComponentRepository,
                                  val notificationService: NotificationService) : ComponentRequestService {
    override fun getByStudentId(id: Long): List<ComponentRequest> {
        return this.componentRequests.getByStudentId(id)
    }

    override fun getByComponentId(id: Long): List<ComponentRequest> {
        return this.componentRequests.getByComponentId(id)
    }

    override fun add(request: ComponentRequest): ComponentRequest {
        var component = components.findOne(request.component.id)
        if (component.available <= 0) throw IndexOutOfBoundsException(component.title + "Out of stock")

        val studentComponents = studentComponents.getByStudentId(request.student.id)
        val studentItemCount = studentComponents.count { it.component.id == request.component.id }
        if (component.limits in 1..studentItemCount) throw IndexOutOfBoundsException("Student exceeds limits")
        //TODO: Implement date limits;

        component.available--
        component = components.save(component)

        request.status = RequestStatus.OPEN
        return if (component.restricted)
            this.componentRequests.save(request)
        else
            this.approve(this.componentRequests.save(request))
    }

    override fun approve(request: ComponentRequest): ComponentRequest {
        val component = components.findOne(request.component.id)
        if (component.available < 0) throw IndexOutOfBoundsException(component.title)

        if (component.consumable) component.stock-- else component.borrowed++
        components.save(component)

        val grantedStudentComponents = studentComponents.getByStudentId(request.student.id)
        val studentItemCount = grantedStudentComponents.count { it.component.id == request.component.id }
        if (component.limits in 1..studentItemCount) {
            this.deny(request, "Limits for component exceeded. " +
                    "You may contact stores for other arrangements")
        }
        val studentComponent = StudentComponent()
        studentComponent.component = component
        studentComponent.requestId = request.id
        studentComponent.studentId = request.student.id

        studentComponents.save(studentComponent)

        request.status = RequestStatus.GRANTED
        request.dateUpdated = Date()

        val notification = Notification()
        notification.userId = request.student.user.id!!
        notification.subject = "Request Granted"
        notification.body = "Hi ${request.student.user.firstname}." +
                "\nYour request for component ${component.title} " +
                "has been granted. You may continue to collect the component." +
                "\n Regards"
        notificationService.add(notification)
        return componentRequests.save(request)
    }

    override fun deny(request: ComponentRequest, reason: String): ComponentRequest {
        val component = components.findOne(request.component.id)
        if (component.stock >= component.available + 1) component.available++

        val notification = Notification()
        notification.userId = request.student.user.id!!
        notification.subject = "Request Denied"
        notification.body = "Hi ${request.student.user.firstname}." +
                "\nYour request for component ${component.title} " +
                "has been denied. The request has been denied because $reason ." +
                "\n Regards"
        notificationService.add(notification)

        request.status = RequestStatus.DENIED
        request.dateUpdated = Date()
        return componentRequests.save(request)
    }

    override fun get(id: Long): ComponentRequest {
        return this.componentRequests.findOne(id)
    }

    @Suppress("UNCHECKED_CAST")
    override fun getAll(): List<ComponentRequest> {
        return this.componentRequests.findAll() as List<ComponentRequest>
    }

    @Suppress("UNCHECKED_CAST")
    override fun getAllPending(): List<ComponentRequest> {
        val requests = this.componentRequests.getByStatus(RequestStatus.OPEN)
        return requests
    }

    override fun cancel(requestId: Long): Boolean {
        val request = componentRequests.findOne(requestId)
        if (request.status != RequestStatus.OPEN) throw IllegalStateException("Component state does no allow deletion")
        this.componentRequests.delete(request)
        return true
    }

    override fun getByUserAndComponent(userId: Long, componentId: Long): List<ComponentRequest> {
        return this.componentRequests.getByComponentIdAndStudentUserId(componentId, userId)
    }
}