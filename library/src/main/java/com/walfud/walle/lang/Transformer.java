package com.walfud.walle.lang;

import android.text.TextUtils;
import android.util.Pair;

import com.walfud.walle.collection.CollectionUtils;

import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
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

    public static <T> String join(List<T> list, String delimiter) {
        if (CollectionUtils.isEmpty(list)) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            stringBuilder.append(delimiter).append(list.get(i));
        }

        return stringBuilder.toString();
    }
    public static String join(String delimiter, Object... objs) {
        return join(CollectionUtils.newArrayList(objs), delimiter);
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

    /**
     * @Deprecated Use {@link CollectionUtils#newHashMap(Object...)} instead
     * @param objs
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> asMap(Object... objs) {
        return CollectionUtils.newHashMap(objs);
    }

    public interface One2One<F, T> {
        T transform(F f);
    }
    public static <F, T> List<T> foreach(List<F> l, One2One<F, T> transformer) {
        List<T> rtn = new ArrayList<>();
        for (F f : l) {
            rtn.add(transformer.transform(f));
        }
        return rtn;
    }

    // Time
    public static String timeInMillis2String(long millis) {
        return timeInMillis2String(millis, false);
    }
    public static String timeInMillis2String(long millis, boolean timezone) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" + (timezone ? " (z)" : "")).format(calendar.getTime());
    }

    // Number
    /**
     * Safe parse integer value
     * @param s
     * @return `0` if failed
     */
    public static int toInt(String s) {
        return toInt(s, 0);
    }
    public static int toInt(String s, int defaultValue) {
        int i = defaultValue;

        try {
            i = Integer.valueOf(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }

    /**
     * Safe parse double value
     * @param s
     * @return `0.0` if failed
     */
    public static double toDouble(String s) {
        return toDouble(s, 0.0);
    }
    public static double toDouble(String s, double defaultValue) {
        double d = defaultValue;

        try {
            d = Double.valueOf(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return d;
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

    // Http

    /**
     * Translate map parameter to http GET url format. Url-Encoded automatically and Skip `null` value but Keep empty string value.
     * @param params
     * @return
     * @see #map2HttpGetParam(Map, boolean, int)
     */
    public static String map2HttpGetParam(Map<String, String> params) {
        return map2HttpGetParam(params, true);
    }

    public static String map2HttpGetParam(Map<String, String> params, boolean doEncode) {
        return map2HttpGetParam(params, doEncode, MAP2HTTPGETPARAM_SKIP_NULL);
    }

    /**
     * Keep all param even if the value is `null` of empty.
     */
    public static final int MAP2HTTPGETPARAM_KEEP_ALL = 0;
    /**
     * Skip the param if value is `null`.
     */
    public static final int MAP2HTTPGETPARAM_SKIP_NULL = 1;
    /**
     * Skip the param if value is `TextUtils.isEmpty` returns true.
     */
    public static final int MAP2HTTPGETPARAM_SKIP_EMPTY = 2;

    /**
     * Translate map parameter to http GET url format
     * @param params
     * @param doEncode
     * @param skipFlag
     * @see #MAP2HTTPGETPARAM_SKIP_NULL
     * @see #MAP2HTTPGETPARAM_SKIP_EMPTY
     * @return
     */
    public static String map2HttpGetParam(Map<String, String> params, boolean doEncode, int skipFlag) {
        if (params == null) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> kv : params.entrySet()) {
            String k = kv.getKey();
            String v = kv.getValue();

            switch (skipFlag) {
                case MAP2HTTPGETPARAM_SKIP_NULL:
                    if (v == null) {
                        continue;
                    }
                    break;

                case MAP2HTTPGETPARAM_SKIP_EMPTY:
                    if (TextUtils.isEmpty(v)) {
                        continue;
                    }
                    break;

                default:
                    break;
            }

            // Avoid the "null" hole
            if (k == null) {
                k = "";
            }
            if (v == null) {
                v = "";
            }

            if (doEncode) {
                k = URLEncoder.encode(k);
                v = URLEncoder.encode(v);
            }
            stringBuilder.append(String.format("&%s=%s", k, v));
        }

        // Trim the leading '&'
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(0);
        }

        return stringBuilder.toString();
    }
}