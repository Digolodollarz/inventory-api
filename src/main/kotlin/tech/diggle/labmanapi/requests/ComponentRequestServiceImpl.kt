package tech.diggle.labmanapi.requests

import org.springframework.stereotype.Service
import tech.diggle.labmanapi.stock.ItemRepository
import tech.diggle.labmanapi.student.StudentRepository
import tech.diggle.labmanapi.studentcomponents.StudentComponent
import tech.diggle.labmanapi.studentcomponents.StudentComponentRepository

@Service
class ComponentRequestServiceImpl(val componentRequests: ComponentRequestRepository,
                                  val items: ItemRepository,
                                  val studentComponents: StudentComponentRepository) : ComponentRequestService {
    override fun getByStudentId(id: Long): List<ComponentRequest> {
        return this.componentRequests.getByStudentId(id)
    }

    override fun getByComponentId(id: Long): List<ComponentRequest> {
        return this.componentRequests.getByComponentId(id)
    }

    override fun add(request: ComponentRequest): ComponentRequest {
        val item = items.getByComponentId(request.componentId)
        if (item.available <= 0) throw IndexOutOfBoundsException(item.component.title)

        val studentComponents = studentComponents.getByStudentId(request.studentId)
        val studentItemCount = studentComponents.count { it.component.id == request.componentId }
        if (item.userLimits in 1..studentItemCount) throw IndexOutOfBoundsException("Student exceeds limits")
        //TODO: Implement date limits;

        item.available--
//        if (item.component.consumable) item.borrowed++ else item.stock--
        items.save(item)



        request.status = RequestStatus.OPEN

        return if (item.isRestricted)
            this.componentRequests.save(request)
        else
            this.approve(this.componentRequests.save(request))
    }

    override fun approve(request: ComponentRequest): ComponentRequest {
        val item = items.getByComponentId(request.componentId)
        if (item.available <= 0) throw IndexOutOfBoundsException(item.component.title)

        if (item.component.consumable) item.borrowed++ else item.stock--
        items.save(item)

        val grantedStudentComponents = studentComponents.getByStudentId(request.studentId)
        val studentItemCount = grantedStudentComponents.count { it.component.id == request.componentId }
        if (item.userLimits in 1..studentItemCount) throw IndexOutOfBoundsException("Student exceeds limits")

        studentComponents.save(StudentComponent().apply {
            component = item.component
            requestId = request.id
            studentId = request.studentId
        })

        request.status = RequestStatus.GRANTED
        return componentRequests.save(request)
    }

    override fun deny(request: ComponentRequest): ComponentRequest {
        val item = items.getByComponentId(request.componentId)
        if (item.available + 1 <= item.stock) item.available++
        items.save(item)

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