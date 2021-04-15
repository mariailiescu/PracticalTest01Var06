package ro.pub.cs.systems.eim.practicaltest01var06;

public interface Constants {
    final public static String NUMBER1 = "number1";
    final public static String NUMBER2 = "number2";
    final public static String NUMBER3 = "number3";
    final public static String CHECK_BOX = "check_boxes";
    final public static String GAINED_SUM = "gained_sum";

    final public static String[] actionTypes = {
            "ro.pub.cs.systems.eim.practicaltest01.sum"
    };

    final public static int NUMBER_OF_CLICKS_THRESHOLD = 5;
    final public static int SERVICE_STOPPED = 0;
    final public static int SERVICE_STARTED = 1;

    final public static String PROCESSING_THREAD_TAG = "[Processing Thread]";

    final public static String BROADCAST_RECEIVER_EXTRA = "message";
    final public static String BROADCAST_RECEIVER_TAG = "[Message]";
}
