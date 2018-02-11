package tech.diggle.labmanapi.requests

import org.springframework.stereotype.Service
import tech.diggle.labmanapi.component.ComponentRepository
import tech.diggle.labmanapi.stock.ItemRepository
import tech.diggle.labmanapi.student.StudentRepository
import tech.diggle.labmanapi.studentcomponents.StudentComponent
import tech.diggle.labmanapi.studentcomponents.StudentComponentRepository

@Service
class ComponentRequestServiceImpl(val componentRequests: ComponentRequestRepository,
                                  val components: ComponentRepository,
                                  val studentComponents: StudentComponentRepository) : ComponentRequestService {
    override fun getByStudentId(id: Long): List<ComponentRequest> {
        return this.componentRequests.getByStudentId(id)
    }

    override fun getByComponentId(id: Long): List<ComponentRequest> {
        return this.componentRequests.getByComponentId(id)
    }

    override fun add(request: ComponentRequest): ComponentRequest {
        val component = components.findOne(request.component.id)
        if (component.available <= 0) throw IndexOutOfBoundsException(component.title)

        val studentComponents = studentComponents.getByStudentId(request.student.id)
        val studentItemCount = studentComponents.count { it.component.id == request.component.id }
        if (component.userLimits in 1..studentItemCount) throw IndexOutOfBoundsException("Student exceeds limits")
        //TODO: Implement date limits;

        component.available--
        components.save(component)

        request.status = RequestStatus.OPEN

        return if (component.isRestricted)
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
        if (component.userLimits in 1..studentItemCount) throw IndexOutOfBoundsException("Student exceeds limits")

        studentComponents.save(StudentComponent().apply {
            component
            requestId = request.id
            studentId = request.student.id
        })

        request.status = RequestStatus.GRANTED
        return componentRequests.save(request)
    }

    override fun deny(request: ComponentRequest): ComponentRequest {
        val component = components.findOne(request.component.id)
        if (component.stock >= component.available + 1) component.available++


        request.status = RequestStatus.DENIED
        return componentRequests.save(request)
    }

    override fun get(id: Long): ComponentRequest {
        return this.componentRequests.findOne(id)
    }

    @Suppress("UNCHECKED_CAST")
    override fun getAll(): List<ComponentRequest> {
        return this.componentRequests.findAll() as List<ComponentRequest>
    }

    override fun cancel(requestId: Long): Boolean {
        val request = componentRequests.findOne(requestId)
        if (request.status != RequestStatus.OPEN) throw IllegalStateException("Component state does no allow deletion")
        this.componentRequests.delete(request)
        return true
    }


}