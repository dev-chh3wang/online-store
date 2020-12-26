package com.store.core;

import java.util.Objects;

public class Customer {

    private String firstName;
    private String lastName;
    private String billingAddress;
    private String shippingAddress;
    private String email;

    public Customer(String firstName, String lastName, String billingAddress, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.billingAddress = billingAddress;
        this.shippingAddress = billingAddress;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return firstName.equals(customer.firstName) &&
                lastName.equals(customer.lastName) &&
                billingAddress.equals(customer.billingAddress) &&
                shippingAddress.equals(customer.shippingAddress) &&
                email.equals(customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, billingAddress, shippingAddress, email);
    }
}
