package com.walfud.walle;

import android.util.Pair;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provide basic transformation between types
 * <br/>
 * Created by walfud on 2015/11/18.
 */
public class Transformer {

    public static final String TAG = "Transformer";

    // Byte

    // Character
    /**
     *
     * @param b `c1`: high 4 bits, `c2`: low 4 bits
     * @return
     */
    public static Pair<Character, Character> byte2Char(byte b) {
        Character c1 = Character.forDigit((b >> 4) & 0x0f, 16);
        Character c2 = Character.forDigit(b & 0x0f, 16);
        return new Pair<>(c1, c2);
    }

    // String
    public static String byte2String(byte b) {
        Pair<Character, Character> couple = byte2Char(b);
        return new StringBuffer().append(couple.first).append(couple.second).toString();
    }
    public static String bytes2String(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (byte b : bytes) {
            sb.append(byte2String(b));
        }

        return sb.toString();
    }

    // Primitive array -> Object array
    /**
     *
     * @param array
     * @return
     */
    public static Integer[] ints2Integers(int[] array) {
        if (array == null) {
            return new Integer[0];
        }

        Integer[] integers = new Integer[array.length];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = array[i];
        }

        return integers;
    }
    public static Long[] longs2Longs(long[] array) {
        if (array == null) {
            return new Long[0];
        }

        Long[] longs = new Long[array.length];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = array[i];
        }

        return longs;
    }
    public static Float[] floats2Floats(float[] array) {
        if (array == null) {
            return new Float[0];
        }

        Float[] floats = new Float[array.length];
        for (int i = 0; i < floats.length; i++) {
            floats[i] = array[i];
        }

        return floats;
    }
    public static Double[] doubles2Doubles(double[] array) {
        if (array == null) {
            return new Double[0];
        }

        Double[] doubles = new Double[array.length];
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = array[i];
        }

        return doubles;
    }

    // Primitive array -> ArrayList
    public static List<Integer> ints2IntegerList(int[] array) {
        List<Integer> list = new ArrayList<>();

        if (array != null) {
            for (int i : array) {
                list.add(i);
            }
        }

        return list;
    }
    public static List<Long> longs2LongList(long[] array) {
        List<Long> list = new ArrayList<>();

        if (array != null) {
            for (long i : array) {
                list.add(i);
            }
        }

        return list;
    }
    public static List<Float> floats2FloatList(float[] array) {
        List<Float> list = new ArrayList<>();

        if (array != null) {
            for (float i : array) {
                list.add(i);
            }
        }

        return list;
    }
    public static List<Double> doubles2DoubleList(double[] array) {
        List<Double> list = new ArrayList<>();

        if (array != null) {
            for (double i : array) {
                list.add(i);
            }
        }

        return list;
    }
    public static List<String> strings2StringList(String[] array) {
        List<String> list = new ArrayList<>();

        if (array != null) {
            for (String i : array) {
                list.add(i);
            }
        }

        return list;
    }

    // Object collection -> primitive array
    public static int[] integerCollection2Ints(Collection<Integer> collection) {
        if (collection == null) {
            return new int[0];
        }

        int[] array = new int[collection.size()];
        int index = 0;
        for (Integer i : collection) {
            array[index++] = i;
        }

        return array;
    }
    public static long[] longCollection2Longs(Collection<Long> collection) {
        if (collection == null) {
            return new long[0];
        }

        long[] array = new long[collection.size()];
        int index = 0;
        for (Long i : collection) {
            array[index++] = i;
        }

        return array;
    }
    public static float[] floatCollection2Floats(Collection<Float> collection) {
        if (collection == null) {
            return new float[0];
        }

        float[] array = new float[collection.size()];
        int index = 0;
        for (Float i : collection) {
            array[index++] = i;
        }

        return array;
    }
    public static double[] doubleCollection2Doubles(Collection<Double> collection) {
        if (collection == null) {
            return new double[0];
        }

        double[] array = new double[collection.size()];
        int index = 0;
        for (Double i : collection) {
            array[index++] = i;
        }

        return array;
    }
    public static String[] stringCollection2Strings(Collection<String> collection) {
        if (collection == null) {
            return new String[0];
        }

        String[] array = new String[collection.size()];
        int index = 0;
        for (String i : collection) {
            array[index++] = i;
        }

        return array;
    }

    // Other
    public static Map<Object, Object> asMap(Object... objs) {
        Map<Object, Object> m = new HashMap<>();
        if (objs == null || objs.length % 2 != 0) {
            return m;
        }

        for (int i = 0; i < objs.length; i += 2) {
            int j = i + 1;

            Object k = objs[i];
            Object v = objs[j];

            m.put(k, v);
        }

        return m;
    }

    // Time
    public static String timeInMillis2String(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss (z)").format(calendar.getTime());
    }

    // File
    public static List<File> stringCollection2FileList(Collection<String> stringCollection) {
        List<File> fileList = new ArrayList<>();

        if (stringCollection != null) {
            for (String string : stringCollection) {
                fileList.add(new File(string));
            }
        }

        return fileList;
    }
}