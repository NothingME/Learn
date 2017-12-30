import org.apache.zookeeper.KeeperException;
import person.zhan.distribute.DistributeLock;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * User: zhanqian
 * Date: 17/12/2
 * Time: 上午10:21
 * Description: 分布式锁测试类
 */
public class DistributeLockTest {

    private static int threadNum = 150;
    private static CountDownLatch countDownLatch = new CountDownLatch(threadNum);
    private static int serial = 0;

    public static void main(String[] args) {
        TestDistributeLock();
    }

    private static void TestDistributeLock() {

        for (int i = 0; i < threadNum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //为了测试，睡眠1秒
                        Thread.sleep(1000);

                        //发令枪倒计时
                        countDownLatch.countDown();

                        DistributeLock distributeLock = new DistributeLock();
                        distributeLock.lock();
                        System.out.println("zhan main >>>>> i: " + (serial++));
                        distributeLock.unlock();
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }


}
