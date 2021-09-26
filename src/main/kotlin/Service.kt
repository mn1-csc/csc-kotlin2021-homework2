interface MessagingService {
/* ... */

    val serviceVisitorsStats: ServiceVisitorsStats?
    val loggingService: LoggingService?

    fun handleRequest(clientId: String) {
        if (loggingService != null) {
            loggingService!!.log("Request from $clientId")
        }

        if (serviceVisitorsStats != null) {
            serviceVisitorsStats!!.registerVisit(clientId)

            if (loggingService != null && serviceVisitorsStats!!.visitorsCounter != null) {
                loggingService!!.log(
                    "Visitors count: ${serviceVisitorsStats!!.visitorsCounter!!.uniqueVisitorsCount}"
                )
            }
        }
    }
}

interface ServiceVisitorsStats {
/* ... */

    val visitorsCounter: VisitorsCounter?

    fun registerVisit(clientId: String) {
        if (visitorsCounter != null) {
            visitorsCounter!!.registerVisit(clientId)
        }
    }
}

interface LoggingService {
    fun log(logMessage: String)
}

class VisitorsCounter {
    var uniqueVisitorsCount: Int = 0
        private set

    fun registerVisit(clientId: String) {
// Пока что считаем всех посетителей уникальными
        uniqueVisitorsCount++
    }
}