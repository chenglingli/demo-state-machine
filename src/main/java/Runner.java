import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.util.encoders.Hex;

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

//        Runner r = new Runner();
//        r.paymentModelRunner();
//        System.out.println("\n hello");

        String password = "Aa123456"; //明文密码
        System.out.println(sha3224(password.getBytes())); //打印哈希后密码
    }

    /**
     * SHA3-224哈希算法
     *
     *  SHA3-224是一种安全的哈希算法，它可以生成一个固定长度的输出值。
     *
     * @param bytes 待哈希的字节数组
     * @return 哈希后的字符串
     */
    public static String sha3224(byte[] bytes) {
        Digest digest = new SHA3Digest(224);
        digest.update(bytes, 0, bytes.length);
        byte[] rsData = new byte[digest.getDigestSize()];
        digest.doFinal(rsData, 0);
        return Hex.toHexString(rsData);
    }


}
