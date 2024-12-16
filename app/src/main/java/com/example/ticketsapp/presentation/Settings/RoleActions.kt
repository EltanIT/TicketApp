package com.example.ticketsapp.presentation.Settings


sealed class RoleActions {
    open class EmployeeActions: RoleActions() {
        data object MyRequests: EmployeeActions()
    }

    open class TechSpecActions: RoleActions(){
        data object MyRequests: TechSpecActions()
        data object CompletedRequests: TechSpecActions()
    }

    open class AdminActions: RoleActions(){
        data object MyRequests: AdminActions()
        data object CompletedRequests: AdminActions()
        data object Statistics: AdminActions()
    }
}

