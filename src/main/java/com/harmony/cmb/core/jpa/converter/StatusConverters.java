package com.harmony.cmb.core.jpa.converter;

import com.harmony.cmb.core.Status.MyWordStatus;

import javax.persistence.Converter;

/**
 * @author wuxii
 */
public class StatusConverters {

    @Converter(autoApply = true)
    public static class MyWordStatusConverter extends AbstractStatusConverter<MyWordStatus> {

        public MyWordStatusConverter() {
            super(MyWordStatus.class);
        }

    }

}
