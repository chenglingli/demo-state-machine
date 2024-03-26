import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class Runner {

    private void bloomFilter() {


        // 如果常量池中存在当前字符串, 就会直接返回当前字符串.
        // 如果常量池中没有此字符串, 会将此字符串放入常量池中后, 再返回
        String xx = "2312";
        xx.intern();

        // 布隆过滤器
        int total = 1000000; // 总数量
        BloomFilter<CharSequence> bf =
                BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total);

        // 初始化 1000000 条数据到过滤器中
        for (int i = 0; i < total; i++) {
            bf.put("" + i);
        }

        // 判断值是否存在过滤器中
        int count = 0;
        for (int i = 0; i < total + 10000; i++) {
            if (bf.mightContain("" + i)) {
                count++;
            }
        }

        System.out.println("已匹配数量 " + count);
    }

    private void paymentModelRunner() {
        PaymentModel p = new PaymentModel(null, null);
        p.transferStatusByEvent(PaymentEvent.PAY_CREATE);

        System.out.println("getCurrentStatus: " + p.getCurrentStatus());
        System.out.println("getLastStatus: " + p.getLastStatus());
    }

    public static void main(String[] args) {

        Runner r = new Runner();

        r.paymentModelRunner();

        System.out.println("\n hello");
    }
}
