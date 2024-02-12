package net.pitan76.memoryusagetitle;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class MemoryUsageTitle {
    public static final String MOD_ID = "memoryusagetitle";

    public static String CACHE_TITLE = "";

    public static MemoryUsageTitle INSTANCE = new MemoryUsageTitle();

    public static void init() {
        CpuLoadThread cpuLoadThread = new CpuLoadThread();
        cpuLoadThread.start();
    }

    public static String getUsageString() {
        long total = Runtime.getRuntime().totalMemory();
        long usedMB = (total - Runtime.getRuntime().freeMemory()) / 1024 / 1024;
        long totalMB = total / 1024 / 1024;

        String cpuUsageString = "";
        cpuUsageString = ", CPU: " + CACHE_CPU_LOAD + "%";

        return "Memory: " + String.format("%,d", usedMB) + "MB / " + String.format("%,d", totalMB) + "MB" + cpuUsageString;
    }

    public static double CACHE_CPU_LOAD = 0.0;

    public void getCpuLoad() throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = ObjectName.getInstance("java.lang:type=OperatingSystem");
        AttributeList list = mbs.getAttributes(name, new String[]{ "ProcessCpuLoad" });
        if (list.isEmpty()) return;
        Attribute att = (Attribute) list.get(0);
        Double value  = (Double) att.getValue();
        if (value == -1.0) return;

        synchronized(this) {
            CACHE_CPU_LOAD = ((int) (value * 1000) / 10.0);
        }
    }

    public static class CpuLoadThread extends Thread {
        public void run() {
            while (true) {
                try {
                    INSTANCE.getCpuLoad();
                    Thread.sleep(1000);
                } catch (Exception ignored) {

                }
            }
        }
    }
}