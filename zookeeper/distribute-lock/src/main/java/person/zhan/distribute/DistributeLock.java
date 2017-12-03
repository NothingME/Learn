package person.zhan.distribute;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.apache.log4j.Logger;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

/**
 * User: zhanqian
 * Date: 17/12/2
 * Time: 上午10:16
 * Description: 利用顺序临时节点，实现分布式锁.
 */
public class DistributeLock implements Lock, Watcher {

    private Logger logger = Logger.getLogger(DistributeLock.class);

    private static String root = "/distribute";

    private static String child = "/child";

    private String current;

    private String prev;

    private CountDownLatch countDownLatch;

    private ZooKeeper client;

    public DistributeLock() throws KeeperException, InterruptedException, IOException {
        client = new ZooKeeper("127.0.0.1:2181", 3000000, this);
        try {
            Stat stat = client.exists(root, false);
            if (stat == null) {
                client.create(root, "root".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void lock() {
        if (tryLock()) {
            System.out.println("正常获取锁 >>>>>>>> ");
        } else {
            System.out.println("Exception >>>>>>>> ");
        }

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        System.out.println("try get lock >>>>>>> ");
        try {
            current = client.create(root + child, "child".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            List<String> zNodeNames = client.getChildren(root, false);
            Collections.sort(zNodeNames);

            //出现意外，子节点都没了。直接获取锁
            if (zNodeNames.size() == 0) {
                return true;
            }

            //当前创建的节点就是最小节点
            if (zNodeNames.get(0).contains(current)) {
                return true;
            }

            //当前节点的位置
            int index = Collections.binarySearch(zNodeNames, current.substring(current.lastIndexOf("/") +1));

            if (index < 1) {
                return true;
            }
            //不是当前最小节点，就监听离他最近的上一个节点
            prev = root + "/" + zNodeNames.get(index - 1);
            Stat stat = client.exists(prev, true);

            //如果不存在上一个节点，直接获得锁
            if (stat == null) {
                return true;
            }

            //如果不是最小节点，发令枪候着
            countDownLatch = new CountDownLatch(1);
            countDownLatch.await();

            return true;

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        try {
            System.out.println("unlock >>>>> " + current);
            client.delete(current, -1);
            client.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("process >>>> ");
        if(this.countDownLatch != null) {
            this.countDownLatch.countDown();
        }
    }

}
