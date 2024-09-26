class SecurityFilters {

    def filters = {
        //esi simple filtera
        doLogin(controller: '*', action: '*') {
            //esi interceptora
            before = {
                if (!controllerName)
                    return true
                def allowedActions = ['show', 'index', 'login', 'validate']

                if (!session.user && !allowedActions.contains(actionName)) {
                    redirect(controller: 'tekUser', action: 'login',
                            params: ['cName': controllerName,
                                     'aName': actionName])
                    return false
                }
            }
        }
    }
}
