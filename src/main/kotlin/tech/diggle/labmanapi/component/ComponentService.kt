package tech.diggle.labmanapi.component

interface ComponentService {

    /**
     * Returns the component with ID passed
     * throws exception if component is not found
     * @param id: Component ID to return
     */
    fun get(id: Long): Component

    fun getAll(): List<Component>
    fun getFiltered(queryText: String): List<Component>
    /**
     * Add a new component to the database
     * @param component Component to insert into database. Must be valid or throws exception
     */
    fun add(component: Component): Component
    fun request(id: Long)
    fun returnComponent(id: Long)
}