package tech.diggle.labmanapi.dashboard

import tech.diggle.labmanapi.security.model.User

interface DashboardService{
    fun getHeaderCards(username: String): List<AdminHeaderCard>
}