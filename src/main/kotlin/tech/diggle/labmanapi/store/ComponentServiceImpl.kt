package tech.diggle.labmanapi.store

import com.sun.javaws.exceptions.InvalidArgumentException
import org.springframework.stereotype.Service
import tech.diggle.labmanapi.manufacturer.ManufacturerRepository

@Service
class ComponentServiceImpl(val componentRepo: ComponentRepository,
                           val manufacturerRepository: ManufacturerRepository) : ComponentService {
    override fun get(id: Long): Component {
        return componentRepo.findOne(id)
    }

    override fun getAll(): List<Component> {
        return componentRepo.findAll() as List<Component>
    }

    override fun add(component: Component): Component {
        if (component.manufacturer == null)
            throw NullPointerException("Manufacturer Id")

        manufacturerRepository.findOne(component.manufacturer?.id)
                ?: throw IllegalArgumentException("Manufacturer not found")

        return componentRepo.save(component)
    }


    override fun request(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun returnComponent(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}