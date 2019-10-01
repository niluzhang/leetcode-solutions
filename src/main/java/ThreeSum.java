import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ThreeSum {
    /**
     * 题15：三数之和
     * 对于每个数字n，判断其他数字里存不存在两数之和等于-n。
     *
     * @param nums 数组
     * @return 和为0的所有三元组
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }

        // 去除出现次数超出3次的超出部分的数字
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= nums.length - 1; i++) {
            Integer times = map.get(nums[i]);
            if (null == times) {
                map.put(nums[i], 1);
            } else {
                times++;
                if (times > 3) times = 3;
                map.put(nums[i], times);
            }
        }

        // 保存结果
        List<List<Integer>> resultList = new ArrayList<>();

        // 保存去除多余数字后的剩余数字
        List<Integer> linkedList = new LinkedList<>();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer number = entry.getKey();
            Integer times = entry.getValue();
            for (int i = 0; i <= times - 1; i++) {
                linkedList.add(number);
            }
        }

        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Integer a = iterator.next();
            iterator.remove();

            // 假设存在和为0的三元组包含该整数n，从剩余的整数中找和为-n的另外两个整数。
            Set<Integer> tmpSet = new HashSet<>();

            Iterator<Integer> tmpIterator = linkedList.iterator();
            while (tmpIterator.hasNext()) {
                Integer b = tmpIterator.next();
                int c = -a - b;
                if (tmpSet.contains(c)) {
                    List<Integer> result = new ArrayList<>(3);
                    result.add(a);
                    result.add(b);
                    result.add(c);
                    result.sort((o1, o2) -> {
                        if (o1.equals(o2)) return 0;
                        return o1 - o2;
                    });
                    resultList.add(result);
                }
                tmpSet.add(b);
            }
        }

        // 对结果List去重
        Set<List<Integer>> filterSet = new LinkedHashSet<>(resultList);

        return new ArrayList<>(filterSet);
    }
}
