import java.util.Calendar;
import java.util.Date;

public class Multitasking extends Thread {
    private int year, month, day, hour, minute;
    public Multitasking(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public void run() {
        try {
            sleep(timeUntil(year, month, day, hour, minute));
            // 작업 수행
            work();
        } catch (InterruptedException e) { // 인터럽트가 발생하여 대기 중이던 상태가 예상보다 빨리 끝남
            e.printStackTrace();
        }
    }

    public static long timeUntil(int year, int month, int day, int hour, int minute) {
        Date now = new Date();
        Calendar deadline = Calendar.getInstance(); // 추상 클래스이므로 getInstance를 통해 객체 생성
        deadline.set(Calendar.YEAR, year);
        deadline.set(Calendar.MONTH, month);
        deadline.set(Calendar.DATE, day); // DAY_OF_WEEK인가?
        deadline.set(Calendar.HOUR_OF_DAY,hour);
        deadline.set(Calendar.MINUTE, minute);
        deadline.set(Calendar.SECOND, 0);

        Date deadline_date = deadline.getTime(); // 현재 객체를 Date 객체로 변환

        long sleep  = deadline_date.getTime() - now.getTime(); // getTime : Date를 밀리세컨드로 변환해서 long형 데이터로 반환

        return sleep;
    }

    public void work() {
        System.out.println("Working!");
    }
}
