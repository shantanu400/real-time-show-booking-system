package domain;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SeatLockManager {
    private static final SeatLockManager INSTANCE = new SeatLockManager();

    // showId → (seatId → LockInfo)
    private final ConcurrentHashMap<String, Map<String, LockInfo>> lockMap;

    // Lock timeout (example: 5 minutes)
    private static final long LOCK_TIMEOUT_MINUTES = 5;

    private SeatLockManager() {
        this.lockMap = new ConcurrentHashMap<>();
    }

    public static SeatLockManager getInstance() {
        return INSTANCE;
    }

    // ============================
    // LOCK SEATS
    // ============================
    public synchronized boolean lockSeats(String showId,
                                          String bookingId,
                                          List<String> seatIds) {

        lockMap.putIfAbsent(showId, new HashMap<>());
        Map<String, LockInfo> seatLocks = lockMap.get(showId);

        // 1️⃣ Remove expired locks before checking
        removeExpiredLocks(seatLocks);

        // 2️⃣ Check if any seat already locked
        for (String seatId : seatIds) {
            if (seatLocks.containsKey(seatId)) {
                return false;
            }
        }

        // 3️⃣ Lock seats
        for (String seatId : seatIds) {
            seatLocks.put(seatId, new LockInfo(bookingId));
        }

        return true;
    }

    // ============================
    // RELEASE SEATS (payment failed)
    // ============================
    public synchronized void releaseSeats(String showId,
                                          List<String> seatIds) {

        Map<String, LockInfo> seatLocks = lockMap.get(showId);

        if (seatLocks == null) return;

        for (String seatId : seatIds) {
            seatLocks.remove(seatId);
        }
    }

    // ============================
    // CONFIRM SEATS (payment success)
    // ============================
    public synchronized void confirmSeats(String showId,
                                          List<String> seatIds,
                                          String bookingId) {

        Map<String, LockInfo> seatLocks = lockMap.get(showId);

        if (seatLocks == null) return;

        for (String seatId : seatIds) {
            LockInfo lockInfo = seatLocks.get(seatId);

            if (lockInfo != null &&
                    lockInfo.getBookingId().equals(bookingId)) {

                seatLocks.remove(seatId);
            }
        }
    }

    // ============================
    // REMOVE EXPIRED LOCKS
    // ============================
    private void removeExpiredLocks(Map<String, LockInfo> seatLocks) {

        Iterator<Map.Entry<String, LockInfo>> iterator =
                seatLocks.entrySet().iterator();

        while (iterator.hasNext()) {

            Map.Entry<String, LockInfo> entry = iterator.next();

            if (isLockExpired(entry.getValue())) {
                iterator.remove();
            }
        }
    }

    private boolean isLockExpired(LockInfo lockInfo) {

        Duration duration =
                Duration.between(lockInfo.getLockTime(), Instant.now());

        return duration.toMinutes() >= LOCK_TIMEOUT_MINUTES;
    }

    // ============================
    // INNER CLASS: LockInfo
    // ============================
    private static class LockInfo {

        private final String bookingId;
        private final Instant lockTime;

        public LockInfo(String bookingId) {
            this.bookingId = bookingId;
            this.lockTime = Instant.now();
        }

        public String getBookingId() {
            return bookingId;
        }

        public Instant getLockTime() {
            return lockTime;
        }
    }
}



