package tech.diggle.labmanapi.dashboard

import org.springframework.stereotype.Service
import tech.diggle.labmanapi.component.ComponentRepository
import tech.diggle.labmanapi.requests.ComponentRequestRepository
import tech.diggle.labmanapi.requests.RequestStatus
import tech.diggle.labmanapi.security.model.AuthorityName
import tech.diggle.labmanapi.security.repository.UserRepository


@Service
class DashboardServiceImpl(val userRepository: UserRepository,
                           val componentRepository: ComponentRepository,
                           val componentRequestRepository: ComponentRequestRepository) : DashboardService {
    override fun getHeaderCards(username: String): List<AdminHeaderCard> {
        val currentUser = userRepository.findByUsername(username)
        val cards: MutableList<AdminHeaderCard> = mutableListOf()
        if (currentUser.authorities!!.filter { it.name == AuthorityName.ROLE_ADMIN }.any()) {
            cards.add(AdminHeaderCard(
                    "Pending requests",
                    componentRequestRepository.getByStatus(RequestStatus.OPEN).count(),
                    "developer_board"
            ))
            cards.add(AdminHeaderCard(
                    "Approved Requests",
                    componentRequestRepository.getByStatus(RequestStatus.GRANTED).count(),
                    "developer_board"
            ))
            cards.add(AdminHeaderCard(
                    "Denied Requests",
                    componentRequestRepository.getByStatus(RequestStatus.DENIED).count(),
                    "developer_board"
            ))
            cards.add(AdminHeaderCard(
                    "Most Wanted Components",
                    componentRequestRepository.getByStatus(RequestStatus.OPEN).count(),
                    "developer_board"
            ))
        }
        if (currentUser.authorities!!.filter { it.name == AuthorityName.ROLE_STOREMAN }.any()) {
        }
        if (currentUser.authorities!!.filter { it.name == AuthorityName.ROLE_LECTURER }.any()) {
        }
        if (currentUser.authorities!!.filter { it.name == AuthorityName.ROLE_REP }.any()) {
        }
        if (currentUser.authorities!!.filter { it.name == AuthorityName.ROLE_STUDENT }.any()) {
            cards.add(AdminHeaderCard(
                    "My Components",
                    componentRequestRepository.getByStatus(RequestStatus.GRANTED).count(),
                    "developer_board"
            ))
            cards.add(AdminHeaderCard(
                    "My Pending requests",
                    componentRequestRepository.getByStatus(RequestStatus.OPEN).count(),
                    "developer_board"
            ))
            cards.add(AdminHeaderCard(
                    "My Denied requests",
                    componentRequestRepository.getByStatus(RequestStatus.DENIED).count(),
                    "developer_board"
            ))
        }
        return cards
    }

}