package com.hanaset.onepiececommon.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime entityValue) {
        if (entityValue == null)
            entityValue = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        if (!entityValue.getZone().equals(ZoneId.of("Asia/Seoul"))) {
            entityValue = entityValue.withZoneSameInstant(ZoneId.of("Asia/Seoul"));
        }

        return Timestamp.from(entityValue.toInstant());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp databaseValue) {
        if (databaseValue == null)
            return ZonedDateTime.now(ZoneId.systemDefault()).minusMonths(1);

        LocalDateTime localDateTime = databaseValue.toLocalDateTime();

        return localDateTime.atZone(ZoneId.systemDefault());
    }
}
