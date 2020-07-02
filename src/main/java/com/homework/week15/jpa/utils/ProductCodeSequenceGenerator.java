package com.homework.week15.jpa.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * https://www.baeldung.com/hibernate-identifiers#5-custom-generator
 */

public class ProductCodeSequenceGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String selectLastProductCode = "SELECT productCode FROM Product " +
                "ORDER BY productCode DESC ";

        String lastCode = session.createQuery(selectLastProductCode, String.class).setMaxResults(1)
                .stream()
                .findFirst()
                .orElse("S01_0000");

        return generateNextCode(lastCode);
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
}
