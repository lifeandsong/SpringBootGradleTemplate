package org.swmaestro.demo.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Date Utility
 *
 * @since   2022-06-29
 * @author  ywkim
 */
@Slf4j
public class DateUtil {

    /**
     * TimeZone
     */
    private TimeZone timeZone;

    private Calendar calendar;
    private SimpleDateFormat sdf;

    public DateUtil() {
        timeZone = TimeZone.getTimeZone("Asia/Seoul");
        calendar = Calendar.getInstance();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

    /**
     * 2개 시간의 차이를 초 단위로 구한다.
     *
     * @param   startTime   시작 시간
     * @param   endTime     종료 시간
     * @return  long        시작 시간과 종료 시간 사이의 초를 구한다.
     */
    public int getDiffSeconds(Date startTime, Date endTime) {
        int dueSeconds = (int) Math.ceil((endTime.getTime() - startTime.getTime()) / 1000);
        log.info("getDiffSeconds={}", dueSeconds);
        return dueSeconds;
    }

    /**
     * 2개 시간의 차이를 일 단위로 구한다.
     *
     * @param   startTime   시작 시간
     * @param   endTime     종료 시간
     * @return  long        시작 시간과 종료 시간 사이의 일을 구한다.
     */
    public int getDiffDays(Date startTime, Date endTime) {
        log.info("endTime={}, startTime={}", endTime.getTime(), startTime.getTime());
        int diffDays = (int) (Math.ceil((endTime.getTime() - startTime.getTime()) / (1000 * 60 * 60 * 24)) + 1);
        log.info("getDiffDays={}", diffDays);
        return diffDays;
    }

    /**
     * 시작 시간으로부터 현재 시간까지의 소요시간을 구해, 로그를 출력한다.
     *
     * @param   startTime   시작 시간
     */
    public void measureTime(String jobName, Date startTime) {
        Date endTime = new Date();
        int dueSeconds = getDiffSeconds(startTime, endTime);
        log.info("{}: startTime={}, endTime={}, dueSeconds={}", jobName, startTime, endTime, dueSeconds);
    }

}
