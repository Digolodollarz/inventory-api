package tech.diggle.inventory.api.stock.component

import org.springframework.stereotype.Service
import tech.diggle.inventory.api.stock.manufacturer.ManufacturerRepository

@Service
class ComponentServiceImpl(val componentRepo: ComponentRepository,
                           val manufacturerRepository: ManufacturerRepository) : ComponentService {
    override fun get(id: Long): Component {
        return componentRepo.findOne(id)
    }

    @Suppress("UNCHECKED_CAST")
    override fun getAll(): List<Component> {
        return componentRepo.findAll() as List<Component>
    }

    override fun add(component: Component): Component {
        if (component.manufacturer == null)
            throw NullPointerException("Manufacturer Id")

        manufacturerRepository.findOne(component.manufacturer?.id)
                ?: throw IllegalArgumentException("Manufacturer not found")

        component.available = component.stock.toLong()

        return componentRepo.save(component)
    }

    override fun update(component: Component): Component {
        if (component.manufacturer == null)
            throw NullPointerException("Manufacturer Id")

        manufacturerRepository.findOne(component.manufacturer?.id)
                ?: throw IllegalArgumentException("Manufacturer not found")

        return componentRepo.save(component)
    }

    override fun getFiltered(queryText: String): List<Component> {
        val returnComponents: MutableList<Component> = mutableListOf()
        returnComponents.addAll(componentRepo.findByPartNumberContainingIgnoreCase(queryText))
        returnComponents.addAll(componentRepo.findByTitleContainingIgnoreCase(queryText))
        returnComponents.addAll(componentRepo.findByTitleContainingIgnoreCase(queryText))
        returnComponents.addAll(componentRepo.findByDescriptionContainingIgnoreCase(queryText))
        returnComponents.addAll(componentRepo.findByManufacturerTitleContainingIgnoreCase(queryText))
        returnComponents.addAll(componentRepo.findByPartTitleContainingIgnoreCase(queryText))
        return returnComponents.distinct()
    }


    override fun request(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun returnComponent(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}