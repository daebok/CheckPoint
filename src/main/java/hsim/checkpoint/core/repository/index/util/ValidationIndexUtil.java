package hsim.checkpoint.core.repository.index.util;

import hsim.checkpoint.core.domain.ValidationData;
import hsim.checkpoint.core.repository.index.idx.ValidationDataIndex;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Validation index util.
 */
public class ValidationIndexUtil {

    /**
     * Add index data.
     *
     * @param data  the data
     * @param index the index
     */
    public static void addIndexData(ValidationData data, ValidationDataIndex index) {
        String key = index.getKey(data);
        List<ValidationData> datas = index.get(key);
        if (datas == null) {
            datas = new ArrayList<>();
        }
        datas.add(data);
        index.getMap().put(key, datas);
    }

    /**
     * Remove index data.
     *
     * @param data  the data
     * @param index the index
     */
    public static void removeIndexData(ValidationData data, ValidationDataIndex index) {

        String key = index.getKey(data);
        List<ValidationData> datas = index.get(key);

        if (!datas.isEmpty()) {
            datas = datas.stream().filter(d -> !d.getId().equals(data.getId())).collect(Collectors.toList());
        }
        if (datas.isEmpty()) {
            index.getMap().remove(key);
        } else {
            index.getMap().put(key, datas);
        }
    }

    /**
     * Make key string.
     *
     * @param keys the keys
     * @return the string
     */
    public static String makeKey(String... keys) {
        StringBuffer keyBuffer = new StringBuffer();
        for (String s : keys) {
            keyBuffer.append(s);
            keyBuffer.append(":");
        }
        return keyBuffer.toString();
    }
}
