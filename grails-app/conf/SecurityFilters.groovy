class SecurityFilters {

    def filters = {
        doLogin(controller: '*', action: '*') {
            before = {
                if (!controllerName) return true

                def allowedActions = ['show', 'index', 'login', 'validate']
                def openApiEndpoints = [
                        [controller: 'person'],
                        //[controller: 'person', action: 'show'],
                        //[controller: 'person', action: 'update'],
                        //[controller: 'person', action: 'delete']
                ]

                def isOpenEndpoint = openApiEndpoints.any { it.controller == controllerName}  //&& it.action == actionName

                if (!session.user && !allowedActions.contains(actionName) && !isOpenEndpoint) {
                    redirect(controller: 'tekUser', action: 'login',
                            params: ['cName': controllerName, 'aName': actionName])
                    return false
                }
                return true
            }
        }
    }
}
