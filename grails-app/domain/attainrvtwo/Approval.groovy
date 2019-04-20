package attainrvtwo

import java.time.LocalTime

class Approval {

    boolean approved
    LocalTime time = LocalTime.now()

    static constraints = {
    }

    @Override
    String toString() {
        return "$approved, $time"
    }
}
