package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.domain.Product;
import com.homework.week15.jdbc.domain.ProductLine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.homework.week15.jdbc.constants.DatabaseConstants.CONNECTION_URL;

public class ProductDAOImpl implements ProductDAO {

    private final String connectionURL;

    public ProductDAOImpl(String connectionURL) {  //for testing
        this.connectionURL = connectionURL;
    }

    public ProductDAOImpl() {
        this(CONNECTION_URL);
    }

    @Override
    public Product save(Product product) {
        String query = "INSERT INTO products" +
                "(productCode, " +
                "productName, " +
                "productLine, " +
                "productScale, " +
                "productVendor, " +
                "productDescription, " +
                "quantityInStock, " +
                "buyPrice, " +
                "MSRP) VALUES (?,?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = getPreparedStatement(query);

        int rowsAffected;
        int paramIndex = 1;
        try {
            String productCode = getNextProductCode();
            preparedStatement.setString(paramIndex++, productCode);
            preparedStatement.setString(paramIndex++, product.getProductName());
            preparedStatement.setString(paramIndex++, product.getProductLine().getProductLine());
            preparedStatement.setString(paramIndex++, product.getProductScale());
            preparedStatement.setString(paramIndex++, product.getProductVendor());
            preparedStatement.setString(paramIndex++, product.getProductDescription());
            preparedStatement.setInt(paramIndex++, product.getQuantityInStock());
            preparedStatement.setBigDecimal(paramIndex++, product.getBuyPrice());
            preparedStatement.setBigDecimal(paramIndex, product.getMSRP());

            rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 1) {
                product.setProductCode(productCode);
                return product;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Error while inserting product");
            throw new RuntimeException(e);
        } finally {
            closeConnection(preparedStatement);
        }
    }

    private String getNextProductCode() throws SQLException {
        String query = "SELECT productCode FROM products " +
                "ORDER BY productCode DESC " +
                "LIMIT 1";

        PreparedStatement preparedStatement = getPreparedStatement(query);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String lastCode = resultSet.getString("productCode");
                return generateNextCode(lastCode);
            } else {
                return "S01_0000";
            }
        } finally {
            closeConnection(preparedStatement);
        }

    }

    private String generateNextCode(String lastCode) {
        String[] productCodeArray = lastCode.split("_");

        int number = Integer.parseInt(productCodeArray[1]);
        if (number < 9999) {
            number++;
            String newNumber = String.format("%04d", number);
            return productCodeArray[0] + "_" + newNumber;
        }
        int index = Integer.parseInt(productCodeArray[0].substring(1));
        index++;
        return "S" + index + "_0000";
    }

    @Override
    public Product findByCode(String productCode) {
        String query = "SELECT * FROM products p " +
                "JOIN productlines pl ON p.productLine = pl.productLine " +
                "WHERE p.productCode = ?";

        PreparedStatement preparedStatement = getPreparedStatement(query);

        try {
            preparedStatement.setString(1, productCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractProductFromResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving product by code");
            throw new RuntimeException(e);
        } finally {
            closeConnection(preparedStatement);
        }
    }

    private Product extractProductFromResultSet(ResultSet resultSet) throws SQLException {
        return Product.builder()
                .productCode(resultSet.getString("productCode"))
                .productName(resultSet.getString("productName"))
                .productLine(ProductLine.builder()
                        .productLine(resultSet.getString("productLine"))
                        .textDescription(resultSet.getString("textDescription"))
                        .htmlDescription(resultSet.getString("htmlDescription"))
                        .image(resultSet.getBytes("image"))
                        .build())
                .productScale(resultSet.getString("productScale"))
                .productVendor(resultSet.getString("productVendor"))
                .productDescription(resultSet.getString("productDescription"))
                .quantityInStock(resultSet.getInt("quantityInStock"))
                .buyPrice(resultSet.getBigDecimal("buyPrice"))
                .MSRP(resultSet.getBigDecimal("MSRP"))
                .build();
    }

    @Override
    public List<Product> findByName(String productName) {
        String query = "SELECT * FROM products p " +
                "JOIN productlines p2 ON p.productLine = p2.productLine " +
                "WHERE p.productName LIKE ?";

        PreparedStatement preparedStatement = getPreparedStatement(query);
        List<Product> products = new ArrayList<>();

        try {
            preparedStatement.setString(1, "%" + productName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = extractProductFromResultSet(resultSet);
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving products with name " + productName);
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public boolean update(Product product) {
        String query = "UPDATE products SET " +
                "productName = ?, " +
                "productLine = ?, " +
                "productScale = ?, " +
                "productVendor = ?, " +
                "productDescription = ?, " +
                "quantityInStock = ?, " +
                "buyPrice = ?, " +
                "MSRP = ? " +
                "WHERE productCode = ?";

        PreparedStatement preparedStatement = getPreparedStatement(query);

        int rowsAffected;
        try {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductLine().getProductLine());
            preparedStatement.setString(3, product.getProductScale());
            preparedStatement.setString(4, product.getProductVendor());
            preparedStatement.setString(5, product.getProductDescription());
            preparedStatement.setInt(6, product.getQuantityInStock());
            preparedStatement.setBigDecimal(7, product.getBuyPrice());
            preparedStatement.setBigDecimal(8, product.getMSRP());


            preparedStatement.setString(9, product.getProductCode());
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while updating Product");
            throw new RuntimeException(e);
        } finally {
            closeConnection(preparedStatement);
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean delete(String productCode) {
        String query = "DELETE FROM products WHERE productCode = ?";
        PreparedStatement preparedStatement = getPreparedStatement(query);
        int rowsAffected;
        try {
            preparedStatement.setString(1, productCode);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while removing product");
            throw new RuntimeException(e);
        }

        return rowsAffected > 0;
    }

    private PreparedStatement getPreparedStatement(String query) {
        try {
            Connection connection = DriverManager.getConnection(connectionURL, "siit", "siit");
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            System.out.println("Error while getting connection");
            throw new RuntimeException("Error while getting connection: ", e);
        }
    }

    private void closeConnection(PreparedStatement preparedStatement) {
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Error while closing connection");
        }
    }
}
