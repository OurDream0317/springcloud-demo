package Test;

import org.junit.Test;

import java.util.*;

public class CollectionDemo {

    public static void method() {
        ArrayList arrayList = new ArrayList();
        LinkedList linkedList = new LinkedList();
        Map<String, List> map = new HashMap<>();
        List list = new ArrayList();
        list.add("郑州");
        list.add("开封");
        list.add("洛阳");
        list.add("周口");
        list.add("驻马店");
        list.add("安阳");
        list.add("南阳");
        List list1 = new ArrayList();
        list1.add("朝阳区");
        list1.add("大兴区");
        list1.add("海淀区");
        list1.add("昌平区");
        list1.add("房山区");
        list1.add("通州区");
        list1.add("顺义区");
        list1.add("东城区");
        list1.add("西城区");
        map.put("河南", list);
        map.put("北京", list1);
        for (Map.Entry entry : map.entrySet()) {
            String key = entry.getKey().toString();
            List<String> list2 = (List) entry.getValue();
            for (String value : list2) {
                System.out.println(key + "====" + value);
            }
        }
    }

    public static void main(String args[]) {
        method();

    }

    @Test
    public void methodDemo() {
        TreeSet set = new TreeSet();
        set.add("ad");
        set.add("d");
        set.add("c");
        for (Object o : set) {
            System.out.println(o);
        }
    }

    @Test
    public void methodDemo1() {
        Set set = new HashSet();
        set.add(null);
        set.add("d");
        set.add("c");
        set.add("c4");
        set.add("c3");
        set.add("c2");
        set.add("c1");
        for (Object o : set) {
            System.out.println(o);

        }
    }

    @Test
    public void methodDemo2() {
        ThreadLocal local = new ThreadLocal();
        local.set("wangweixiang");
        System.out.println(local.get());
    }

    @Test
    public void main1() {
        LinkedList list = new LinkedList();
        System.out.println(new String("114").hashCode());
        list.add("115");
        list.add("114");
        list.add("113");
        list.add("112");
        list.add("111");
        Object[] ints = list.toArray();

        for (Object o : ints) {
            System.out.println(o);
        }
        /* Arrays.sort(ints);*/
       /* for (Object o:ints) {
            System.out.println(o);
        }*/
    }

}
