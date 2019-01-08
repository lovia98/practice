package com.example.study.builder;

class Person {

    private String firstName;
    private String lastName;
    private String zipCode;
    private String address;
    private String addressDetail;
    private String phoneNumber;

    public Person(String firstName, String lastName, String zipCode, String address, String addressDetail, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.zipCode = zipCode;
        this.address = address;
        this.addressDetail = addressDetail;
        this.phoneNumber = phoneNumber;
    }

    public static class Builder {

        private String firstName;
        private String lastName;
        private String zipCode;
        private String address;
        private String addressDetail;
        private String phoneNumber;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder addressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Person build() {

            if (illegalRequireField()) {
                throw new IllegalStateException();
            }

            return new Person(firstName, lastName, zipCode, address, addressDetail, phoneNumber);
        }

        private boolean illegalRequireField() {
            return firstName == null || lastName == null || zipCode == null
                || address == null || addressDetail == null || phoneNumber == null;
        }
    }
}
