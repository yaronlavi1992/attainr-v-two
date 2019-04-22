package attainrvtwo

import java.time.LocalTime

class Approval {

    boolean approved
    def date = new Date().format('dd MM yyyy')
    LocalTime time = LocalTime.now()

    static constraints = {
    }

    @Override
    String toString() {
        return approved ? 'אושרה ' + date + ' ' + time : 'נדחתה ' + date + ' ' + time
    }
}
