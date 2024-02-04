package mlearning.datatools;


final class DataOps {

    public static Object convert(String data, Class<?> dtype) {
        if (dtype == int.class) {
            return Integer.valueOf(data);
        } else if (dtype == double.class) {
            if (data.contains(",")) {
                data = data.replace(",", ".");
            }
            return Double.valueOf(data);
        } else if (dtype == boolean.class) {
            return Boolean.parseBoolean(data);
        } else {
            return data;
        }
    }

    public static Class<?> resolveType(String literal) {
        if (isInt(literal)) {
            return int.class;
        } else if (isDouble(literal)) {
            return double.class;
        } else if (literal.equalsIgnoreCase("true") || literal.equalsIgnoreCase("false")) {
            return boolean.class;
        } else {
            return String.class;
        }
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String s) {
        s = s.replace(',', '.');
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Double toDouble(Object value) {
        return ((Number) value).doubleValue();
    }
}
