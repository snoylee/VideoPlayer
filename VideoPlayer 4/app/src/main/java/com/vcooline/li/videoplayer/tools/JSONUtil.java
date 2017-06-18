package com.vcooline.li.videoplayer.tools;

import org.json.JSONObject;

public class JSONUtil {

    // --------------------------------------------------------------------------------
    // Default Values Define
    // --------------------------------------------------------------------------------

    public static final String JSONUTIL_DEFUALT_STRING_VAL = "";
    public static final Integer JSONUTIL_DEFUALT_INTEGER_VAL = 0;
    public static final Boolean JSONUTIL_DEFUALT_BOOLEAN_VAL = false;
    public static final Double JSONUTIL_DEFUALT_DOUBLE_VAL = 0.0;
    public static final Long JSONUTIL_DEFUALT_LONG_VAL = (long)0;

    // --------------------------------------------------------------------------------

    private JSONObject jsonObject = null;

    // --------------------------------------------------------------------------------
    // Constructor
    // --------------------------------------------------------------------------------

    public JSONUtil(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    // --------------------------------------------------------------------------------
    // get Object value
    // --------------------------------------------------------------------------------

    /**
     * Returns the value mapped by name, or {@code null} if no such mapping
     * exists.
     *
     * @param key
     * @return
     */
    public Object opt(String key) {
        return jsonObject.opt(key);
    }

    // --------------------------------------------------------------------------------
    // get String value
    // --------------------------------------------------------------------------------

    /**
     * Returns the value mapped by name, or {@code JSONUTIL_DEFUALT_STRING_VAL}
     * if no such mapping exists.
     *
     * @param key
     * @return
     */
    public String optString(String key) {
        return optString(key, JSONUTIL_DEFUALT_STRING_VAL);
    }

    /**
     * Returns the value mapped by name, or {@code defaultValue} if no such
     * mapping exists.
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public String optString(String key, String defaultValue) {

        try {
            if (jsonObject == null) {
                return defaultValue;
            }
            Object value = jsonObject.opt(key);
            if (value == null) {
                return defaultValue;
            }

            if (jsonObject.isNull(key)) {
                return defaultValue;
            }

            if (value instanceof String) {
                return (String) value;
            }

            if (value instanceof Boolean) {
                return ((Boolean) value).toString();
            }

            if (value instanceof Integer) {
                return ((Integer) value).toString();
            }

            if (value instanceof Double) {
                return ((Double) value).toString();
            }

        } catch (Exception e) {

        }

        return defaultValue;
    }

    // --------------------------------------------------------------------------------
    // get Integer value
    // --------------------------------------------------------------------------------

    /**
     * Returns the value mapped by name, or {@code JSONUTIL_DEFUALT_INTEGER_VAL}
     * if no such mapping exists.
     *
     * @param key
     * @return
     */
    public Integer optInteger(String key) {
        return optInteger(key, JSONUTIL_DEFUALT_INTEGER_VAL);
    }

    /**
     * Returns the value mapped by name, or {@code defaultValue} if no such
     * mapping exists.
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Integer optInteger(String key, Integer defaultValue) {

        try {
            if (jsonObject == null) {
                return defaultValue;
            }

            Object value = jsonObject.opt(key);
            if (value == null) {
                return defaultValue;
            }

            if (jsonObject.isNull(key)) {
                return defaultValue;
            }

            if (value instanceof Integer) {
                return (Integer) value;
            }

            if (value instanceof Double) {
                return ((Double) value).intValue();
            }

            if (value instanceof String) {
                Integer result = defaultValue;
                try {
                    result = Integer.valueOf((String) value);
                } catch (NumberFormatException e) {
                    result = defaultValue;
                }
                return result;
            }

            if (value instanceof Boolean) {
                return (Boolean) value ? 1 : 0;
            }

        } catch (Exception e) {

        }

        return defaultValue;
    }

    // --------------------------------------------------------------------------------
    // get Double value
    // --------------------------------------------------------------------------------

    /**
     * Returns the value mapped by name, or {@code JSONUTIL_DEFUALT_DOUBLE_VAL}
     * if no such mapping exists.
     *
     * @param key
     * @return
     */
    public Double optDouble(String key) {
        return optDouble(key, JSONUTIL_DEFUALT_DOUBLE_VAL);
    }

    /**
     * Returns the value mapped by name, or {@code defaultValue} if no such
     * mapping exists.
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Double optDouble(String key, Double defaultValue) {

        try {
            if (jsonObject == null) {
                return defaultValue;
            }

            Object value = jsonObject.opt(key);
            if (value == null) {
                return defaultValue;
            }

            if (jsonObject.isNull(key)) {
                return defaultValue;
            }

            if (value instanceof Double) {
                return (Double) value;
            }

            if (value instanceof Integer) {
                return ((Integer) value).doubleValue();
            }

            if (value instanceof String) {
                Double result = defaultValue;
                try {
                    result = Double.valueOf((String) value);
                } catch (NumberFormatException e) {
                    result = defaultValue;
                }
                return result;
            }

            if (value instanceof Boolean) {
                return (Boolean) value ? 1.0 : 0.0;
            }

        } catch (Exception e) {

        }

        return defaultValue;
    }

    // --------------------------------------------------------------------------------
    // get Boolean value
    // --------------------------------------------------------------------------------

    /**
     * Returns the value mapped by name, or {@code JSONUTIL_DEFUALT_BOOLEAN_VAL}
     * if no such mapping exists.
     *
     * @param key
     * @return
     */
    public Boolean optBoolean(String key) {
        return optBoolean(key, JSONUTIL_DEFUALT_BOOLEAN_VAL);
    }

    /**
     * Returns the value mapped by name, or {@code defaultValue} if no such
     * mapping exists.
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Boolean optBoolean(String key, Boolean defaultValue) {

        try {
            if (jsonObject == null) {
                return defaultValue;
            }

            Object value = jsonObject.opt(key);
            if (value == null) {
                return defaultValue;
            }

            if (jsonObject.isNull(key)) {
                return defaultValue;
            }

            if (value instanceof Boolean) {
                return (Boolean) value;
            }

            if (value instanceof String) {
                if (((String) value).equals("1")
                        || ((String) value).equalsIgnoreCase("true")) {
                    return true;
                } else {
                    return false;
                }
            }

            if (value instanceof Integer) {
                return ((Integer) value) == 1 ? true : false;
            }

            // if (value instanceof Double) {
            // return ((Double) value) == 1.0 ? true : false;
            // }

        } catch (Exception e) {

        }

        return defaultValue;
    }
    public Long optLong(String key) {

        try {
            if (jsonObject == null) {
                return JSONUTIL_DEFUALT_LONG_VAL;
            }

            Object value = jsonObject.opt(key);
            if (value == null) {
                return JSONUTIL_DEFUALT_LONG_VAL;
            }

            if (jsonObject.isNull(key)) {
                return JSONUTIL_DEFUALT_LONG_VAL;
            }

            if (value instanceof Integer) {
                return (Long) value;
            }

            if (value instanceof String) {
                Long result = (long)JSONUTIL_DEFUALT_LONG_VAL;
                try {
                    result = Long.valueOf((String) value);
                } catch (NumberFormatException e) {
                    result =  (long)JSONUTIL_DEFUALT_LONG_VAL;
                }
                return result;
            }

        } catch (Exception e) {

        }

        return JSONUTIL_DEFUALT_LONG_VAL;
    }

}
