package com.example.demo.util;

import java.util.Date;

public class DateConditional {
    public  static Date now = new Date();

    public static boolean createdDateConditional(Date createdDate) {
        if (createdDate.before(now)) {
            return false;
        }
        return true;
    }

    public static boolean endDateConditional(Date startedDate, Date endDate) {
        if (endDate.before(startedDate)) { //startedDate.before(now) ||
            return false;
        }
        return true;
    }

    public static boolean endDateModifiedConditional(Date createdStartedDate, Date startedDate, Date endDate) {
        if (endDate.before(createdStartedDate) || endDate.before(startedDate)) {
            return false;
        }
        return true;
    }

    public static boolean startedDateModifiedConditional(Date createdStartedDate, Date createdStartedModifiedDate ) {
        if (createdStartedModifiedDate.before(createdStartedDate) || createdStartedModifiedDate.before(now)) {
            return false;
        }
        return true;
    }

    public static boolean vailidDate(Date createdDate, Date modifiedDate) {
        if (!createdDateConditional(createdDate) || !endDateConditional(createdDate, modifiedDate)){
            return false;
        }
        return true;
    }
}
