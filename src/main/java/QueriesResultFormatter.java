import data.Buyer;
import data.Shop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QueriesResultFormatter {
    private final BuyersDao dao;

    public QueriesResultFormatter(BuyersDao dao) {
        this.dao = dao;
    }

    public Buyer[] resultToBuyerArray(ResultSet result) throws SQLException {
        ArrayList<Buyer> buyers = new ArrayList<>();
        while (result.next()) {
            buyers.add(resultRowToBuyer(result));
        }
        return buyers.toArray(new Buyer[0]);
    }

    public static String fillingString(int length, char charToFill) {
        return Stream.generate(() -> String.valueOf(charToFill)).limit(length).collect(Collectors.joining());
    }

    public Buyer resultRowToBuyer(ResultSet result) throws SQLException {
        Buyer buyer = new Buyer();

        for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
            var fieldName = result.getMetaData().getColumnName(i);
            try {
                String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method setter = getMethodLike(Buyer.class, setterName);
                setField(buyer, setter,  setter.getParameterTypes()[0], result, i);
            } catch (IllegalAccessException | ParseException | InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException ignored) {
            }
        }
        return buyer;
    }

    private static Method getMethodLike(Class<?> fromClass, String name) throws NoSuchMethodException {
        final Optional<Method> matchedMethod = Arrays.stream(fromClass.getMethods()).filter(method ->
                method.getName().toLowerCase().contains(name.toLowerCase())).findAny();
        if (matchedMethod.isEmpty()) {
            throw new NoSuchMethodException("No method containing: " + name);
        }
        return matchedMethod.get();
    }

    private void setField(Object object, Method setter, Type fieldType, ResultSet result, int index)
            throws ParseException, SQLException, InvocationTargetException, IllegalAccessException {
        try {
            if (fieldType == Integer.class || fieldType == int.class) {
                setter.invoke(object, result.getInt(index));
            } else if (fieldType == Long.class) {
                setter.invoke(object, result.getLong(index));
            } else if (fieldType == Date.class) {
                setter.invoke(object, result.getDate(index));
            } else if (fieldType == Shop.class) {
                var shop = dao.getShop(result.getInt(index));
                shop.getBuyers().add(object);
                setter.invoke(object, shop);
            } else {
                setter.invoke(object, result.getString(index));
            }
        } catch (IllegalArgumentException e){
            System.out.println(object.getClass() + " " + setter.getName());
        }
    }

    public String intArrayToString(int[] shopIds) {
        StringBuilder builder = new StringBuilder();
        builder.append("(").append(shopIds[0]);
        for (int i = 1; i < shopIds.length; ++i) {
            builder.append(", ").append(shopIds[i]);
        }
        builder.append(")");
        return builder.toString();
    }

    public Shop resultToShop(ResultSet result) throws SQLException {
        Shop shop = new Shop();

        for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
            var fieldName = result.getMetaData().getColumnName(i);
            try {
                String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method setter = getMethodLike(Shop.class, setterName);
                setField(shop, setter, setter.getParameterTypes()[0], result, i);
            } catch (IllegalAccessException | ParseException | InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException ignored) {
            }
        }
        return shop;
    }
}
