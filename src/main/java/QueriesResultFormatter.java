import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QueriesResultFormatter {
    private final char charToFill;
    private final int columnWidth;
    private final String columnSeparator;

    public QueriesResultFormatter(int columnWidth, String columnSeparator, char charToFill) {
        this.columnWidth = columnWidth;
        this.charToFill = charToFill;
        this.columnSeparator = columnSeparator;
    }

    public String[] resultToStringArray(ResultSet result) throws SQLException {
        ArrayList<String> strings = new ArrayList<>();
        var header = getHeader(result);
        strings.add(header);
        strings.add(fillingString(header.length()));
        while (result.next()) {
            strings.add(resultRowToString(result));
        }
        return strings.toArray(new String[0]);
    }

    public String fillingString(int length) {
        return Stream.generate(() -> String.valueOf(charToFill)).limit(length).collect(Collectors.joining());
    }

    public String getHeader(ResultSet result) throws SQLException {
        var headerRow = new StringBuilder();
        for (int i = 1; i <= result.getMetaData().getColumnCount(); ++i) {
            headerRow.append(String.format("%1$" + columnWidth + "s",
                    result.getMetaData().getColumnName(i))).append(columnSeparator);
        }
        return headerRow.toString();
    }

    public String resultRowToString(ResultSet result) throws SQLException {
        StringBuilder row = new StringBuilder();
        for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
            row.append(String.format("%1$" + columnWidth + "s",
                    result.getString(i))).append(columnSeparator);
        }
        return row.toString();
    }

    public String intArrayToString(int[] shopIds) {
        StringBuilder builder = new StringBuilder();
        builder.append("(").append(shopIds[0]);
        for (int  i = 1; i < shopIds.length; ++i){
            builder.append(", ").append(shopIds[i]);
        }
        builder.append(")");
        return builder.toString();
    }
}
