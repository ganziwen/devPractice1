// package utils;
//
// import com.google.common.collect.Lists;
//
// import java.util.Iterator;
// import java.util.List;
// import java.util.concurrent.*;
//
// /**
//  * @author steven01.gan
//  * @version 1.0
//  * @date 2022/2/16-12:32
//  */
// public class ExecutorUtils {
//     public static int threadCount = 10;
//     public static final ExecutorService WORKER;
//     private static List<Future> futures;
//
//     public ExecutorUtils() {
//     }
//
//     public static void wait(FutureTask... futureTasks) {
//         FutureTask[] var1 = futureTasks;
//         int var2 = futureTasks.length;
//
//         for(int var3 = 0; var3 < var2; ++var3) {
//             FutureTask futureTask = var1[var3];
//
//             try {
//                 if (futureTask != null) {
//                     futureTask.get();
//                 }
//             } catch (ExecutionException | InterruptedException var6) {
//                 var6.printStackTrace();
//             }
//         }
//
//     }
//
//     public static void submit(Runnable task) {
//         futures.add(WORKER.submit(task));
//     }
//
//     public static boolean isFinished() {
//         Iterator var0 = futures.iterator();
//
//         while(var0.hasNext()) {
//             Future future = (Future)var0.next();
//             if (!future.isDone()) {
//                 try {
//                     future.get();
//                 } catch (ExecutionException | InterruptedException var3) {
//                     var3.printStackTrace();
//                 }
//             }
//         }
//
//         clear();
//         return true;
//     }
//
//     public static void clear() {
//         futures.clear();
//     }
//
//     static {
//         WORKER = new ThreadPoolExecutor(1, 1, 60000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(threadCount));
//         futures = Lists.newCopyOnWriteArrayList();
//     }
// }
//
