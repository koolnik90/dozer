package org.dozer;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;


public class CustomMapperListToListTest extends AbstractDozerTest{


    @Test
    public void conversionUsingApiConf(){
        final Address address = new Address("add1", "city1", "postalCode1");
        DozerBeanMapper beanMapper = new DozerBeanMapper();
        CustomConverter customConverter = new AddressToStringConverter();
        beanMapper.setCustomConverters(Collections.singletonList(customConverter));
        Assert.assertEquals(beanMapper.map(address, String.class), "add1:city1:postalCode1");
    }

    @Test
    public void conversionUsingApiConfOtherWay(){
        final Address address = new Address("add1", "city1", "postalCode1");
        DozerBeanMapper beanMapper = new DozerBeanMapper();
        CustomConverter customConverter = new AddressToStringConverter();
        beanMapper.setCustomConverters(Collections.singletonList(customConverter));
        Assert.assertEquals(beanMapper.map(address, String.class), "add1:city1:postalCode1");
    }

    private static class Address{
        private String addressLine;
        private String city;
        private String postalCode;

        private Address(String addressLine, String city, String postalCode) {
            this.addressLine = addressLine;
            this.city = city;
            this.postalCode = postalCode;
        }

    }

    private static class AddressToStringConverter extends DozerConverter<Address, String>{

        public AddressToStringConverter() {
            super(Address.class, String.class);
        }

        @Override
        public String convertTo(Address source, String destination) {
            return source.addressLine +":"+ source.city +":" +source.postalCode;
        }

        @Override
        public Address convertFrom(String source, Address destination) {
            String addressStrArry[] = source.split(":");
            return new Address(addressStrArry[0], addressStrArry[1], addressStrArry[2]);
        }

    }
}
